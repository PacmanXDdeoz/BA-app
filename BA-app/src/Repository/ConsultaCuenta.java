package Repository;

import Config.Con;
import Service.GeneralService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConsultaCuenta {
    public static Map<String, Object> consultaCuenta(Connection connection, int clienteId){
        GeneralService.cleanScreen();
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        Map<String, Object> detallesCuenta = new HashMap<>();

        try {
            connection = Con.getConn();

            String query = "SELECT " +
                            "c.numero_cuenta, " +
                            "c.saldo, " + 
                            "c.fecha_apertura, " + 
                            "COALESCE(s.nombre_sucursal, 'Desconocida') AS nombre_sucursal, " + 
                            "COALESCE(pf.nombre_prod, 'Sin Producto') AS nombre_producto, " + 
                            "COALESCE(e.estado, 'Estado Desconocido') AS estado_cuenta, " +
                            "COALESCE(cl.nombre_cliente, 'Cliente Desconocido') AS nombre_cliente " +
                            "FROM banco.cuentas c " +
                            "LEFT JOIN " + 
                            "banco.sucursales s ON c.sucursal_id = s.sucursal_id " +
                            "LEFT JOIN banco.productos_financieros pf ON c.producto_id = pf.producto_id " +
                            "LEFT JOIN banco.estados e ON c.estado_id = e.estado_id " +
                            "LEFT JOIN banco.clientes cl ON c.cliente_id = cl.cliente_id " +
                            "WHERE cl.cliente_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, clienteId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                detallesCuenta.put("numero_cuenta", resultSet.getLong("numero_cuenta"));
                detallesCuenta.put("saldo", resultSet.getDouble("saldo"));
                detallesCuenta.put("fecha_apertura", resultSet.getDate("fecha_apertura"));
                detallesCuenta.put("nombre_sucursal", resultSet.getString("nombre_sucursal"));
                detallesCuenta.put("nombre_producto", resultSet.getString("nombre_producto"));
                detallesCuenta.put("estado_cuenta", resultSet.getString("estado_cuenta"));
                detallesCuenta.put("nombre_cliente", resultSet.getString("nombre_cliente"));
                
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
        return detallesCuenta;
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
