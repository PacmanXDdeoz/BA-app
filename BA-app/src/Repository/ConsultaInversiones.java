package Repository;

import Config.Con;
import Service.GeneralService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConsultaInversiones {

    public static Map<String, Object> consultaInversion (Connection connection, int clienteId){
        GeneralService.cleanScreen();
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        Map<String, Object> detallesInversiones = new HashMap<>();

        try {
            connection = Con.getConn();

            String query = "SELECT " +
                            "i.monto_actual, " +
                            "i.tasa_interes, " +
                            "i.fecha_inicio, " +
                            "i.fecha_vencimiento, " +
                            "i.tipo_inversion, " +
                            "COALESCE(pf.nombre_prod, 'Sin Producto') AS nombre_producto, " +
                            "COALESCE(cl.nombre_cliente, 'Cliente Desconocido') AS nombre_cliente " +
                            "FROM banco.inversiones i " +
                            "LEFT JOIN banco.productos_financieros pf ON i.producto_id = pf.producto_id " +
                            "LEFT JOIN banco.clientes cl ON i.cliente_id = cl.cliente_id " +
                            "WHERE cl.cliente_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, clienteId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                detallesInversiones.put("monto_actual", resultSet.getDouble("monto_actual"));
                detallesInversiones.put("tasa_interes", resultSet.getDouble("tasa_interes"));
                detallesInversiones.put("fecha_inicio", resultSet.getDate("fecha_inicio"));
                detallesInversiones.put("fecha_vencimiento", resultSet.getDate("fecha_vencimiento"));
                detallesInversiones.put("tipo_inversion", resultSet.getString("tipo_inversion"));
                detallesInversiones.put("nombre_producto", resultSet.getString("nombre_producto"));
                detallesInversiones.put("nombre_cliente", resultSet.getString("nombre_cliente"));
                
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
        return detallesInversiones;
    }
}
