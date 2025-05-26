package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Inversiones;
import Model.Cliente;

public class EliminarInversion {

    public static void eInversion(Connection connection, Cliente clientes, Inversiones inversiones){

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

        int inversionId = -1;
        String searchInversion = "select inversion_id from banco.inversiones where cliente_id = ?";
            try (PreparedStatement psBusqueda2 = connection.prepareStatement(searchInversion)){
                psBusqueda2.setInt(1, clienteId);
                try (ResultSet rs = psBusqueda2.executeQuery()){
                    if (rs.next()) {
                        inversionId = rs.getInt("inversion_id");
                }
            }
        }
        if (inversionId == -1) {
            System.out.println("No se encontro a esta inversion con el id proporcionado");
            return;
        }

        String delete = "delete from banco.inversiones where inversion_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(delete)){
            
            ps.setInt(1, inversionId);
            int update = ps.executeUpdate();
            if (update>0) {
                System.out.println("Datos eliminados correctamente");
            } else {
                System.out.println("Datos no eliminados");
            }
        }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}