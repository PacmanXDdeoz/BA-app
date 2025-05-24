package Repository;

import Model.Cliente;
import Model.Tarjetas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertTarjetas {

    public static void iTarjeta(Connection connection, Tarjetas tarjeta, Cliente clientes) throws SQLException{
        
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

        String insert = "insert into banco.tarjetas (producto_id, num_tarjeta, fecha_expiracion, tipo_tarjeta, saldo_actual, estado_id, fecha_emision, cvv, cliente_id) " + 
        "values (?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(insert)){
            
            ps.setInt(1, tarjeta.getProducto_id());
            ps.setString(2, tarjeta.getNum_tarjeta());
            ps.setDate(3, Date.valueOf(tarjeta.getFecha_expiracion()));
            ps.setInt(4, tarjeta.getTipo_tarjeta());
            ps.setDouble(5, tarjeta.getSaldo_actual());
            ps.setInt(6, tarjeta.getEstado_id());
            ps.setDate(7, Date.valueOf(tarjeta.getFecha_emision()));
            ps.setString(8, tarjeta.getCvv());
            ps.setInt(9, clienteId);

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
