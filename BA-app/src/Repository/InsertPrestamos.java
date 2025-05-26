package Repository;

import Model.Cliente;
import Model.Prestamos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class InsertPrestamos {
    public static void iPrestamos(Connection connection, Prestamos prestamos, Cliente clientes) throws SQLException{
        
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
            System.out.println("No se encontro a este cliente con la CURP proporcionado");
            return;
        }

        String insert = "insert into banco.prestamos (producto_id, monto, tasa_interes, plazo, pago_mensual, fecha_inicio, fecha_fin, estado_id, cliente_id) " + 
        "values (?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(insert)){
            
            ps.setInt(1, prestamos.getProducto_id());
            ps.setDouble(2, prestamos.getMonto());
            ps.setDouble(3, prestamos.getTasa_interes());
            ps.setInt(4, prestamos.getPlazo_interes());
            ps.setDouble(5, prestamos.getPago_mensual());
            ps.setDate(6, Date.valueOf(prestamos.getFecha_inicio()));
            ps.setDate(7, Date.valueOf(prestamos.getFecha_fin()));
            ps.setInt(8, prestamos.getEstado_id());
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
