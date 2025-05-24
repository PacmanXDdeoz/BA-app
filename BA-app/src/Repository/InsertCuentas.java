package Repository;

import Model.Cuentas;
import Model.Cliente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertCuentas {

    public static void iCuentas (Connection connection, Cuentas cuentas, Cliente clientes){
        
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

        String insert = "insert into banco.cuentas (sucursal_id, producto_id, numero_cuenta, saldo, estado_id, fecha_apertura, cliente_id) " +
        "values (?,?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(insert)){
            
            ps.setInt(1, cuentas.getSucursal_id());
            ps.setInt(2, cuentas.getProducto_id());
            ps.setString(3, cuentas.getNumero_cuenta());
            ps.setDouble(4, cuentas.getSaldo());
            ps.setInt(5, cuentas.getEstado_id());
            ps.setDate(6, Date.valueOf(cuentas.getFecha_apertura()));
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
