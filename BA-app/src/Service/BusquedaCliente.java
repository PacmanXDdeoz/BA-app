package Service;

import Config.Con;
import Model.Cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class BusquedaCliente {

    public static Cliente buscarClientePorCurp(String curp) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try {
            connection = Con.getConn(); // Get connection here
            String query = "SELECT cliente_id, nombre_cliente, apellido_paterno, apellido_materno, fecha_nacimiento, telefono, direccion, email, ocupacion, rfc, curp FROM banco.clientes WHERE curp = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, curp);
            rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setCliente_id(rs.getInt("cliente_id"));
                cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                cliente.setApellido_paterno(rs.getString("apellido_paterno"));
                cliente.setApellido_materno(rs.getString("apellido_materno"));

                cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setEmail(rs.getString("email"));
                cliente.setOcupacion(rs.getString("ocupacion"));
                cliente.setRfc(rs.getString("rfc"));
                cliente.setCurp(rs.getString("curp"));

                System.out.println("Cliente encontrado: " + cliente.getNombre_cliente() + " " + cliente.getApellido_paterno());
            } else {
                System.out.println("No se encontró a este cliente con la CURP proporcionada: " + curp);
            }
        } catch (SQLException e) {
            System.err.println("Error de base de datos al buscar cliente por CURP: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                Con.closeConnetion(connection);
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos en buscarClientePorCurp: " + e.getMessage());
            }
        }
        return cliente;
    }

    public static Map<String, Object> consultaCliente(Connection connection, int clienteId){
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        Map<String, Object> detallesCliente = new HashMap<>();

        try {

            String query = "SELECT " +
                            "c.cliente_id, " +
                            "c.nombre_cliente, " + // Select individual name parts
                            "c.apellido_paterno, " +
                            "c.apellido_materno, " +
                            "c.fecha_nacimiento, " +
                            "c.telefono, " +
                            "c.direccion, " +
                            "c.email, " +
                            "c.ocupacion, " +
                            "c.rfc, " +
                            "c.curp, " +
                            "COALESCE(ca.numero_cuenta, 'N/A') AS numero_cuenta, " +
                            "COALESCE(ca.saldo, 0.00) AS saldo_cuenta, " +
                            "COALESCE(t.num_tarjeta, 'N/A') AS numero_tarjeta, " +
                            "COALESCE(t.saldo_actual, 0.00) AS saldo_tarjeta, " +
                            "COALESCE(p.monto, 0.00) AS monto_prestamo, " +
                            "COALESCE(p.tasa_interes, 0.00) AS tasa_interes_prestamo, " +
                            "COALESCE(i.monto_actual, 0.00) AS monto_inversion, " +
                            "COALESCE(i.tipo_inversion, 'N/A') AS tipo_inversion " +
                            "FROM banco.clientes c " +
                            "LEFT JOIN banco.cuentas ca ON c.cliente_id = ca.cliente_id " +
                            "LEFT JOIN banco.tarjetas t ON c.cliente_id = t.cliente_id " +
                            "LEFT JOIN banco.prestamos p ON c.cliente_id = p.cliente_id " +
                            "LEFT JOIN banco.inversiones i ON c.cliente_id = i.cliente_id " +
                            "WHERE c.cliente_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, clienteId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                detallesCliente.put("cliente_id", resultSet.getInt("cliente_id"));
                // Concatenate name parts here
                detallesCliente.put("nombre_completo", resultSet.getString("nombre_cliente") + " " +
                                                    resultSet.getString("apellido_paterno") + " " +
                                                    resultSet.getString("apellido_materno"));
                detallesCliente.put("fecha_nacimiento", resultSet.getDate("fecha_nacimiento")); // Get as Date
                detallesCliente.put("telefono", resultSet.getString("telefono"));
                detallesCliente.put("direccion", resultSet.getString("direccion"));
                detallesCliente.put("email", resultSet.getString("email"));
                detallesCliente.put("ocupacion", resultSet.getString("ocupacion"));
                detallesCliente.put("rfc", resultSet.getString("rfc"));
                detallesCliente.put("curp", resultSet.getString("curp"));

                // Financial details
                detallesCliente.put("numero_cuenta", resultSet.getString("numero_cuenta"));
                detallesCliente.put("saldo", resultSet.getBigDecimal("saldo_cuenta")); // Use BigDecimal for currency

                detallesCliente.put("monto", resultSet.getBigDecimal("monto_prestamo")); // Use BigDecimal for currency
                detallesCliente.put("tasa_interes", resultSet.getBigDecimal("tasa_interes_prestamo")); // Use BigDecimal

                detallesCliente.put("tipo_inversion", resultSet.getString("tipo_inversion"));
                detallesCliente.put("monto_actual", resultSet.getBigDecimal("monto_inversion")); // Use BigDecimal for currency

                detallesCliente.put("num_tarjeta", resultSet.getString("numero_tarjeta"));
                detallesCliente.put("saldo_actual", resultSet.getBigDecimal("saldo_tarjeta")); // Use BigDecimal for currency

            } else {
                System.out.println("No se encontraron datos para el cliente con ID: " + clienteId);
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar datos del cliente: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos en consultaCliente: " + e.getMessage());
            }
        }
        return detallesCliente;
    }
}