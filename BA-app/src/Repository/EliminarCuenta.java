package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Cliente;
import Model.Cuentas;

public class EliminarCuenta {
    public static void eCuenta(Connection connection, Cliente clientes, Cuentas cuentas){

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

        int cuentaId = -1;
        String searchCuenta = "select cuenta_id from banco.cuentas where cliente_id = ?";
            try (PreparedStatement psBusqueda2 = connection.prepareStatement(searchCuenta)){
                psBusqueda2.setInt(1, clienteId);
                try (ResultSet rs = psBusqueda2.executeQuery()){
                    if (rs.next()) {
                        cuentaId = rs.getInt("cuenta_id");
                }
            }
        }
        if (cuentaId == -1) {
            System.out.println("No se encontro a esta cuenta con el id proporcionado");
            return;
        }

        String delete = "delete from banco.cuentas where cuenta_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(delete)){
            
            ps.setInt(1, cuentaId);
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
