package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Config.Con;
import Service.GeneralService;

public class ConsultaBeneficiarios {
    public static Map<String, Object> consultaBeneficiario(Connection connection, int clienteId){
        GeneralService.cleanScreen();
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        Map<String, Object> detallesBeneficiario = new HashMap<>();

        try {
            connection = Con.getConn();
            String query = "SELECT " +
                            "b.nombre_beneficiario, " +
                            "b.apellido_paterno, " +
                            "b.apellido_materno, " +
                            "b.parentesco, " +
                            "b.telefono, " +
                            "b.email, " +
                            "COALESCE(s.nombre_cliente, 'Desconocida') AS nombre_cliente " +
                            "FROM banco.beneficiarios b " +
                            "LEFT JOIN banco.clientes s ON b.cliente_id = s.cliente_id " +
                            "WHERE s.cliente_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, clienteId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                detallesBeneficiario.put("nombre_completo", resultSet.getString("nombre_beneficiario") + " " + resultSet.getString("apellido_paterno") + " " + resultSet.getString("apellido_materno"));
                detallesBeneficiario.put("parentesco", resultSet.getString("parentesco"));
                detallesBeneficiario.put("telefono", resultSet.getString("telefono"));
                detallesBeneficiario.put("email", resultSet.getString("email"));
                detallesBeneficiario.put("nombre_cliente", resultSet.getString("nombre_cliente"));
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
        return detallesBeneficiario;
    }
}