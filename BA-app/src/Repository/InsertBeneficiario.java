package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Beneficiarios;
import Model.Cliente;

public class InsertBeneficiario {
    public static void iBeneficiarios(Connection connection, Beneficiarios beneficiario, Cliente clientes) throws SQLException{
        
        int clienteId = -1;
        String searchCliente = "select cliente_id from banco.clientes where curp = ?";
        try{
            try (PreparedStatement psBusqueda = connection.prepareStatement(searchCliente)){
                psBusqueda.setString(1, clientes.getCurp());
                try (ResultSet rs = psBusqueda.executeQuery()){
                    if (rs.next()) {
                        clienteId = rs.getInt("cliente_id");
                }
            }
        }
        if (clienteId == -1) {
            System.out.println("No se encontro a este cliente con la CURP proporcionada");
            return;
        }

        String insert = "insert into banco.beneficiarios (cliente_id, nombre_beneficiario, apellido_paterno, apellido_materno, parentesco, telefono, email) " + 
        "values (?,?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(insert)){
            
            ps.setInt(1, clienteId);
            ps.setString(2, beneficiario.getNombre_beneficiario());
            ps.setString(3, beneficiario.getApellido_paterno());
            ps.setString(4, beneficiario.getApellido_materno());
            ps.setString(5, beneficiario.getParentesco());
            ps.setString(6, beneficiario.getTelefono());
            ps.setString(7, beneficiario.getEmail());

            int update = ps.executeUpdate();
            if (update>0) {
                System.out.println("Datos insertados correctamente");
            } else {
                System.out.println("Datos no insertados");
            }
        }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
