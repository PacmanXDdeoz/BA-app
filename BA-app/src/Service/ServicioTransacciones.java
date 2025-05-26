package Service;

import Config.Con;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ServicioTransacciones {

    public static boolean realizarOperacion(
            String tipoTransaccion,
            BigDecimal monto,
            String numeroCuentaOrigen,
            String numeroCuentaDestino,
            String descripcion) {

        // --- 1. Validaciones iniciales ---
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            System.err.println("Error: El monto de la transacción debe ser positivo.");
            return false;
        }
        if (tipoTransaccion == null || tipoTransaccion.trim().isEmpty()) {
            System.err.println("Error: El tipo de transacción no puede ser nulo o vacío.");
            return false;
        }

        Connection connection = null;
        boolean exito = false;

        try {
            connection = Con.getConn();
            connection.setAutoCommit(false); // INICIA LA TRANSACCIÓN: Deshabilita el auto-commit

            BigDecimal saldoOrigen = null;
            BigDecimal saldoDestino = null;

            // Variables para los IDs internos de las cuentas
            Integer cuentaOrigenId = null;
            Integer cuentaDestinoId = null;

            // --- Obtener IDs internos a partir de los números de cuenta ---
            if (numeroCuentaOrigen != null && !numeroCuentaOrigen.trim().isEmpty()) {
                cuentaOrigenId = obtenerCuentaIdPorNumero(connection, numeroCuentaOrigen);
                if (cuentaOrigenId == null) {
                    System.err.println("Error: El número de cuenta de origen '" + numeroCuentaOrigen + "' no fue encontrado.");
                    connection.rollback();
                    return false;
                }
            }

            if (numeroCuentaDestino != null && !numeroCuentaDestino.trim().isEmpty()) {
                cuentaDestinoId = obtenerCuentaIdPorNumero(connection, numeroCuentaDestino);
                if (cuentaDestinoId == null) {
                    System.err.println("Error: El número de cuenta de destino '" + numeroCuentaDestino + "' no fue encontrado.");
                    connection.rollback();
                    return false;
                }
            }

            // --- 2. Lógica según el tipo de transacción ---
            switch (tipoTransaccion.toUpperCase()) {
                case "INGRESO":
                    if (cuentaDestinoId == null || cuentaDestinoId <= 0) {
                        System.err.println("Error: Para un INGRESO, se requiere un número de cuenta de destino válido.");
                        return false;
                    }
                    saldoDestino = obtenerSaldoCuenta(connection, cuentaDestinoId);
                    if (saldoDestino == null) {
                        System.err.println("Error: Cuenta de destino con ID " + cuentaDestinoId + " no encontrada (posible inconsistencia).");
                        connection.rollback();
                        return false;
                    }
                    if (!actualizarSaldoCuenta(connection, cuentaDestinoId, monto, true)) {
                        connection.rollback();
                        return false;
                    }
                    System.out.println("Ingreso de $" + monto + " a la cuenta " + numeroCuentaDestino + " realizado.");
                    break;

                case "RETIRO":
                    if (cuentaOrigenId == null || cuentaOrigenId <= 0) {
                        System.err.println("Error: Para un RETIRO, se requiere un número de cuenta de origen válido.");
                        return false;
                    }
                    saldoOrigen = obtenerSaldoCuenta(connection, cuentaOrigenId);
                    if (saldoOrigen == null) {
                        System.err.println("Error: Cuenta de origen con ID " + cuentaOrigenId + " no encontrada (posible inconsistencia).");
                        connection.rollback();
                        return false;
                    }
                    if (saldoOrigen.compareTo(monto) < 0) {
                        System.err.println("Error: Saldo insuficiente en la cuenta " + numeroCuentaOrigen + " para el retiro. Saldo actual: $" + saldoOrigen);
                        connection.rollback();
                        return false;
                    }
                    if (!actualizarSaldoCuenta(connection, cuentaOrigenId, monto, false)) {
                        connection.rollback();
                        return false;
                    }
                    System.out.println("Retiro de $" + monto + " de la cuenta " + numeroCuentaOrigen + " realizado.");
                    break;

                case "TRANSFERENCIA":
                    if (cuentaOrigenId == null || cuentaOrigenId <= 0 || cuentaDestinoId == null || cuentaDestinoId <= 0) {
                        System.err.println("Error: Para una TRANSFERENCIA, se requieren números de cuenta de origen y destino válidos.");
                        return false;
                    }
                    if (cuentaOrigenId.equals(cuentaDestinoId)) {
                        System.err.println("Error: No se puede transferir a la misma cuenta.");
                        return false;
                    }

                    saldoOrigen = obtenerSaldoCuenta(connection, cuentaOrigenId);
                    saldoDestino = obtenerSaldoCuenta(connection, cuentaDestinoId);

                    if (saldoOrigen == null) {
                        System.err.println("Error: Cuenta de origen " + numeroCuentaOrigen + " no encontrada (posible inconsistencia).");
                        connection.rollback();
                        return false;
                    }
                    if (saldoDestino == null) {
                        System.err.println("Error: Cuenta de destino " + numeroCuentaDestino + " no encontrada (posible inconsistencia).");
                        connection.rollback();
                        return false;
                    }
                    if (saldoOrigen.compareTo(monto) < 0) {
                        System.err.println("Error: Saldo insuficiente en la cuenta de origen " + numeroCuentaOrigen + " para la transferencia. Saldo actual: $" + saldoOrigen);
                        connection.rollback();
                        return false;
                    }

                    if (!actualizarSaldoCuenta(connection, cuentaOrigenId, monto, false)) {
                        connection.rollback();
                        return false;
                    }
                    if (!actualizarSaldoCuenta(connection, cuentaDestinoId, monto, true)) {
                        connection.rollback();
                        return false;
                    }
                    System.out.println("Transferencia de $" + monto + " de la cuenta " + numeroCuentaOrigen + " a la cuenta " + numeroCuentaDestino + " realizada.");
                    break;

                default:
                    System.err.println("Error: Tipo de transacción no válido: " + tipoTransaccion);
                    return false;
            }

            // --- 3. Registrar la transacción ---
            if (registrarTransaccion(connection, tipoTransaccion, monto, cuentaOrigenId, cuentaDestinoId, descripcion, "COMPLETADA")) {
                connection.commit();
                exito = true;
            } else {
                connection.rollback();
                System.err.println("Error: Falló el registro de la transacción en la tabla de transacciones.");
            }

        } catch (SQLException e) {
            System.err.println("Error de SQL durante la operación bancaria: " + e.getMessage());
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                    System.err.println("Transacción revertida debido a un error.");
                }
            } catch (SQLException rollbackEx) {
                System.err.println("Error al intentar hacer rollback: " + rollbackEx.getMessage());
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    Con.closeConnetion(connection);
                }
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return exito;
    }

    private static Integer obtenerCuentaIdPorNumero(Connection connection, String numeroCuenta) throws SQLException {
        String sql = "SELECT cuenta_id FROM banco.cuentas WHERE numero_cuenta = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, numeroCuenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cuenta_id");
                }
            }
        }
        return null; // Retorna null si no se encuentra la cuenta
    }

    private static BigDecimal obtenerSaldoCuenta(Connection connection, int cuentaId) throws SQLException {
        String sql = "SELECT saldo FROM banco.cuentas WHERE cuenta_id = ? FOR UPDATE";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cuentaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("saldo");
                }
            }
        }
        return null;
    }

    private static boolean actualizarSaldoCuenta(Connection connection, int cuentaId, BigDecimal monto, boolean sumar) throws SQLException {
        String sql = "UPDATE banco.cuentas SET saldo = saldo " + (sumar ? "+" : "-") + " ? WHERE cuenta_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBigDecimal(1, monto);
            ps.setInt(2, cuentaId);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }
    }

    private static boolean registrarTransaccion(
            Connection connection,
            String tipoTransaccion,
            BigDecimal monto,
            Integer cuentaOrigenId,
            Integer cuentaDestinoId,
            String descripcion,
            String estado) throws SQLException {

        String sql = "INSERT INTO banco.transacciones (" +
                    "cuenta_origen_id, cuenta_destino_id, tipo_transaccion, monto, fecha_transaccion, descripcion, estado) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (cuentaOrigenId != null && cuentaOrigenId > 0) {
                ps.setInt(1, cuentaOrigenId);
            } else {
                ps.setNull(1, java.sql.Types.INTEGER);
            }

            if (cuentaDestinoId != null && cuentaDestinoId > 0) {
                ps.setInt(2, cuentaDestinoId);
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            ps.setString(3, tipoTransaccion);
            ps.setBigDecimal(4, monto);
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, descripcion);
            ps.setString(7, estado);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }
    }
}