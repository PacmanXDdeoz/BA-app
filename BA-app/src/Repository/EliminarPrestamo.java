package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Cliente;
import Model.Prestamos;

public class EliminarPrestamo {
    public static void ePrestamo(Connection connection, Cliente clientes, Prestamos prestamos){

        int clienteId = -1;
        String searchCliente = "select cliente_id from banco.clientes where rfc = ?";
        try{
            try (PreparedStatement psBusqueda = connection.prepareStatement(searchCliente)){
                psBusqueda.setString(1, clientes.getRfc());
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

        int prestamoId = -1;
        String searchTarjeta = "select prestamo_id from banco.tarjetas where cliente_id = ?";
            try (PreparedStatement psBusqueda2 = connection.prepareStatement(searchTarjeta)){
                psBusqueda2.setInt(1, clienteId);
                try (ResultSet rs = psBusqueda2.executeQuery()){
                    if (rs.next()) {
                        prestamoId = rs.getInt("prestamo_id");
                }
            }
        }
        if (prestamoId == -1) {
            System.out.println("No se encontro a esta tarjeta con el id proporcionado");
            return;
        }

        String delete = "delete from banco.prestamos where prestmo_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(delete)){
            
            ps.setInt(1, prestamoId);
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
