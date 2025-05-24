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

            String query = "select \n" + //
                                "c.cliente_id, \n" + //
                                "c.nombre_cliente, \n" + //
                                "c.apellido_paterno, \n" + //
                                "c.apellido_materno, \n" + //
                                "c.fecha_nacimiento, \n" + //
                                "c.telefono, \n" + //
                                "c.direccion, \n" + //
                                "c.email,\n" + //
                                "c.ocupacion, \n" + //
                                "c.rfc, \n" + //
                                "c.curp, \n" + //
                                "u.cuenta_id, \n" + //
                                "u.numero_cuenta, \n" + //
                                "u.saldo,\n" + //
                                "p.prestamo_id, \n" + //
                                "p.monto,\n" + //
                                "p.pago_mensual,\n" + //
                                "i.inversion_id,\n" + //
                                "i.monto_actual,\n" + //
                                "i.tipo_inversion \n" + //
                                "from banco.clientes c \n" + //
                                "left join banco.cuentas u on c.cliente_id = u.cuenta_id \n" + //
                                "left join banco.prestamos p on c.cliente_id = p.prestamo_id \n" + //
                                "left join banco.inversiones i on c.cliente_id = i.inversion_id\n" + //
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

                detallesCliente.put("cuenta_id", resultSet.getInt("cuenta_id"));
                detallesCliente.put("numero_cuenta", resultSet.getString("numero_cuenta"));
                detallesCliente.put("saldo", resultSet.getDouble("saldo"));

                detallesCliente.put("prestamo_id", resultSet.getInt("prestamo_id"));
                detallesCliente.put("pago_mensual", resultSet.getDouble("pago_mensual"));
                detallesCliente.put("monto", resultSet.getDouble("monto"));

                detallesCliente.put("inversion_id", resultSet.getInt("inversion_id"));
                detallesCliente.put("tipo_inversion", resultSet.getString("tipo_inversion"));
                detallesCliente.put("monto_actual", resultSet.getDouble("monto_actual"));
                
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
