package Repository;

import Config.Con;
import Service.GeneralService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConsultaCliente {

    public static Map<String, Object> consultaCliente(Connection connection, int clienteId){
        GeneralService.cleanScreen();
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        Map<String, Object> detallesCliente = new HashMap<>();

        try {
            connection = Con.getConn();

            String query = "SELECT " +
                            "c.cliente_id, " +
                            "c.nombre_cliente AS nombre_cliente, " +
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
                            "where c.cliente_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, clienteId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                detallesCliente.put("cliente_id", resultSet.getInt("cliente_id"));
                detallesCliente.put("nombre_completo", resultSet.getString("nombre_cliente") + " " + resultSet.getString("apellido_paterno") + " " + resultSet.getString("apellido_materno"));
                detallesCliente.put("telefono", resultSet.getString("telefono"));
                detallesCliente.put("direccion", resultSet.getString("direccion"));
                detallesCliente.put("email", resultSet.getString("email"));
                detallesCliente.put("ocupacion", resultSet.getString("ocupacion"));
                detallesCliente.put("rfc", resultSet.getString("rfc"));
                detallesCliente.put("curp", resultSet.getString("curp"));

                // TODO: cuenta
                detallesCliente.put("numero_cuenta", resultSet.getString("numero_cuenta"));
                detallesCliente.put("saldo", resultSet.getDouble("saldo_cuenta"));

                // TODO: prestamos
                detallesCliente.put("tasa_interes", resultSet.getDouble("tasa_interes_prestamo"));
                detallesCliente.put("monto", resultSet.getDouble("monto_prestamo"));

                // TODO: inversion
                detallesCliente.put("tipo_inversion", resultSet.getString("tipo_inversion"));
                detallesCliente.put("monto_actual", resultSet.getDouble("monto_inversion"));

                // TODO: tarjeta
                detallesCliente.put("num_tarjeta", resultSet.getString("numero_tarjeta"));
                detallesCliente.put("saldo_actual", resultSet.getDouble("saldo_tarjeta"));
                
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
        return detallesCliente;
    }
}