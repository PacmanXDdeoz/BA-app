package Repository;

import Config.Con;
import Service.GeneralService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConsultaPrestamos {
    public static Map<String, Object> consultaPrestamos(Connection connection, int clienteId){
        GeneralService.cleanScreen();
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        Map<String, Object> detallesPrestamo = new HashMap<>();

        try {
            connection = Con.getConn();

            String query = "SELECT " +
                            "p.monto, " +
                            "p.tasa_interes, " +
                            "p.plazo, " +
                            "p.pago_mensual, " +
                            "p.fecha_inicio, " +
                            "p.fecha_fin, " +
                            "COALESCE(pf.nombre_prod, 'Sin Producto') AS nombre_producto, " +
                            "COALESCE(e.estado, 'N/A') AS estado, " +
                            "COALESCE(cl.nombre_cliente, 'Cliente Desconocido') AS nombre_cliente " +
                            "FROM banco.prestamos p " +
                            "LEFT JOIN banco.productos_financieros pf ON p.producto_id = pf.producto_id " +
                            "LEFT JOIN banco.estados e ON p.estado_id = e.estado_id " +
                            "LEFT JOIN banco.clientes cl ON p.cliente_id = cl.cliente_id " +
                            "WHERE cl.cliente_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, clienteId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                detallesPrestamo.put("monto", resultSet.getDouble("monto"));
                detallesPrestamo.put("tasa_interes", resultSet.getDouble("tasa_interes"));
                detallesPrestamo.put("plazo", resultSet.getDouble("plazo"));
                detallesPrestamo.put("pago_mensual", resultSet.getDouble("pago_mensual"));
                detallesPrestamo.put("fecha_inicio", resultSet.getDate("fecha_inicio"));
                detallesPrestamo.put("fecha_fin", resultSet.getDate("fecha_fin"));
                detallesPrestamo.put("nombre_producto", resultSet.getString("nombre_producto"));
                detallesPrestamo.put("estado", resultSet.getString("estado"));
                detallesPrestamo.put("nombre_cliente", resultSet.getString("nombre_cliente"));
                
            } else {
                System.out.println("No se encontraron datos de este ID: " + clienteId);
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return detallesPrestamo;
    }

    public static Map<String, Object> searchCuenta(Connection connection, int cuentaId){
        GeneralService.cleanScreen();
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        Map<String, Object> detallesCuenta = new HashMap<>();

        try {
            connection = Con.getConn();

            String query = "SELECT cuenta_id from banco.cuentas where numero_cuenta = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, cuentaId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                detallesCuenta.put("numero_cuenta", resultSet.getString("numero_cuenta"));
            } else {
                System.out.println("No se encontraron datos de este ID: " + cuentaId);
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return detallesCuenta;
    }
}
