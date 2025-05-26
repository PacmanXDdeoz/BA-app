package Repository;

import Model.Cliente;
import Model.Inversiones;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class InsertInversiones {
    public static void iInversiones(Connection connection, Inversiones inversion, Cliente clientes) throws SQLException{
        
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

        String insert = "insert into banco.inversiones (producto_id, monto_actual, tasa_interes, fecha_inicio, fecha_vencimiento, tipo_inversion, cliente_id) " + 
        "values (?,?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(insert)){
            
            ps.setInt(1, inversion.getProducto_id());
            ps.setDouble(2, inversion.getMonto());
            ps.setDouble(3, inversion.getTasa_interes());
            ps.setDate(4, Date.valueOf(inversion.getFecha_inicio()));
            ps.setDate(5, Date.valueOf(inversion.getFecha_vencimiento()));
            ps.setString(6, inversion.getTipo_inversion());
            ps.setInt(7, clienteId);

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
