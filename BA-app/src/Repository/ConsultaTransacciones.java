package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Config.Con;
import Service.GeneralService;

public class ConsultaTransacciones {
    public static Map<String, Object> consultaTransacciones(Connection connection, int clienteId){
        GeneralService.cleanScreen();
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        Map<String, Object> detallesTransacciones = new HashMap<>();

        try {
            connection = Con.getConn();

            String query = "SELECT " +
                            "t.transaccion_id, " +
                            "t.tipo_transaccion, " +
                            "t.monto, " +
                            "t.fecha_transaccion, " +
                            "t.descripcion, " +
                            "t.estado, " +
                            "COALESCE(co.numero_cuenta, 'N/A') AS numero_cuenta_origen, " +
                            "COALESCE(cl_origen.nombre_cliente, 'N/A') AS nombre_cliente_origen, " +
                            "COALESCE(cd.numero_cuenta, 'N/A') AS numero_cuenta_destino, " +
                            "COALESCE(cl_destino.nombre_cliente, 'N/A') AS nombre_cliente_destino " +
                            "FROM banco.transacciones t " +
                            "LEFT JOIN banco.cuentas co ON t.cuenta_origen_id = co.cuenta_id " +
                            "LEFT JOIN banco.clientes cl_origen ON co.cliente_id = cl_origen.cliente_id " +
                            "LEFT JOIN banco.cuentas cd ON t.cuenta_destino_id = cd.cuenta_id " +
                            "LEFT JOIN banco.clientes cl_destino ON cd.cliente_id = cl_destino.cliente_id " +
                            "WHERE (cl_origen.cliente_id = ? OR cl_destino.cliente_id = ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, clienteId);
            ps.setInt(2, clienteId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                detallesTransacciones.put("transaccion_id", resultSet.getInt("transaccion_id"));
                detallesTransacciones.put("tipo_transaccion", resultSet.getString("tipo_transaccion"));
                detallesTransacciones.put("monto", resultSet.getDouble("monto"));
                detallesTransacciones.put("fecha_transaccion", resultSet.getDate("fecha_transaccion"));
                detallesTransacciones.put("descripcion", resultSet.getString("descripcion"));
                detallesTransacciones.put("estado", resultSet.getString("estado"));
                detallesTransacciones.put("numero_cuenta_origen", resultSet.getString("numero_cuenta_origen"));
                detallesTransacciones.put("nombre_cliente_origen", resultSet.getString("nombre_cliente_origen"));
                detallesTransacciones.put("numero_cuenta", resultSet.getString("numero_cuenta_destino"));
                detallesTransacciones.put("nombre_cliente_destino", resultSet.getString("nombre_cliente_destino"));
                
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
        return detallesTransacciones;
    }
}
