package Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Cliente;
import Model.Tarjetas;

public class ActualizarRegistros {

    public static void actTarjeta(Connection connection, Cliente clientes, Tarjetas tarjetas){

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

        int tarjetasId = -1;
        String searchTarjeta = "select tarjeta_id from banco.tarjetas where cliente_id = ?";
            try (PreparedStatement psBusqueda2 = connection.prepareStatement(searchTarjeta)){
                psBusqueda2.setInt(1, clienteId);
                try (ResultSet rs = psBusqueda2.executeQuery()){
                    if (rs.next()) {
                        tarjetasId = rs.getInt("tarjeta_id");
                }
            }
        }
        if (tarjetasId == -1) {
            System.out.println("No se encontro a esta tarjeta con el id proporcionado");
            return;
        }

        String delete = "UPDATE banco.tarjetas SET num_tarjeta = ?, fecha_expiracion = ?, estado_id = ?, fecha_emision = ?, cvv = ?" + 
                        "WHERE tarjeta_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(delete)){
            
            ps.setString(1, tarjetas.getNum_tarjeta());
            ps.setDate(2, Date.valueOf(tarjetas.getFecha_expiracion()));
            ps.setInt(3, tarjetas.getEstado_id());
            ps.setDate(4, Date.valueOf(tarjetas.getFecha_emision()));
            ps.setString(5, tarjetas.getCvv());
            ps.setInt(6, tarjetasId);

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
