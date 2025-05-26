package Repository;

import Config.Con;
import Service.GeneralService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConsultaTarjeta {

    public static Map<String, Object> consultaTarjeta(Connection connection, int clienteId){
        GeneralService.cleanScreen();
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        Map<String, Object> detallesTarjeta = new HashMap<>();

        try {
            connection = Con.getConn();
            String query = "SELECT " +
                            "t.num_tarjeta, " +
                            "t.fecha_emision, " +
                            "t.fecha_expiracion, " +
                            "t.saldo_actual, " +
                            "t.cvv, " +
                            "COALESCE(tt.tipo_t, 'Desconocida') AS tipo_tarjeta, " +
                            "COALESCE(pf.nombre_prod, 'Sin Producto') AS nombre_producto, " +
                            "COALESCE(e.estado, 'Estado Desconocido') AS estado_tarjeta, " +
                            "COALESCE(cl.nombre_cliente, 'Cliente Desconocido') AS nombre_cliente " +
                            "FROM banco.tarjetas t " +
                            "LEFT JOIN banco.tipo_tarjeta tt ON t.tipo_tarjeta = tt.tipot_id " +
                            "LEFT JOIN banco.productos_financieros pf ON t.producto_id = pf.producto_id " +
                            "LEFT JOIN banco.estados e ON t.estado_id = e.estado_id " +
                            "LEFT JOIN banco.clientes cl ON t.cliente_id = cl.cliente_id " + 
                            "WHERE cl.cliente_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, clienteId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                detallesTarjeta.put("num_tarjeta", resultSet.getString("num_tarjeta"));
                detallesTarjeta.put("cvv", resultSet.getString("cvv"));
                detallesTarjeta.put("fecha_emision", resultSet.getDate("fecha_emision"));
                detallesTarjeta.put("fecha_expiracion", resultSet.getDate("fecha_expiracion"));
                detallesTarjeta.put("saldo_actual", resultSet.getDouble("saldo_actual"));
                detallesTarjeta.put("estado", resultSet.getString("estado_tarjeta"));
                detallesTarjeta.put("tipo_tarjeta", resultSet.getString("tipo_tarjeta"));
                detallesTarjeta.put("nombre_producto", resultSet.getString("nombre_producto"));
                detallesTarjeta.put("nombre_cliente", resultSet.getString("nombre_cliente"));
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
        return detallesTarjeta;
    }
}
