package Repository;

import Model.Cliente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertCliente {

    public static void iCliente(Connection connection, Cliente cliente) throws SQLException{
        PreparedStatement ps = null;

        String passwordHash1 = null;
        String encryptPasswordSQL = "SELECT crypt(?, gen_salt('bf', 10)) as password_hash";
        
        try (PreparedStatement psEncrypt = connection.prepareStatement(encryptPasswordSQL)){
            psEncrypt.setString(1, cliente.getPassword());
        try (ResultSet rs = psEncrypt.executeQuery()) {
            if (rs.next()) {
                passwordHash1 = rs.getString("password_hash");
            }
        }

        String insert = "insert into banco.clientes (nombre_cliente, apellido_paterno, apellido_materno, fecha_nacimiento, telefono, direccion, email, ocupacion, rfc, curp, password)" + 
        "values (?,?,?,?,?,?,?,?,?,?,?)";

        try{
            ps = connection.prepareStatement(insert);

            ps.setString(1, cliente.getNombre_cliente());
            ps.setString(2, cliente.getApellido_paterno());
            ps.setString(3, cliente.getApellido_materno());
            ps.setDate(4, Date.valueOf(cliente.getFecha_nacimiento()));
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getDireccion());
            ps.setString(7, cliente.getEmail());
            ps.setString(8, cliente.getOcupacion());
            ps.setString(9, cliente.getRfc());
            ps.setString(10, cliente.getCurp());
            ps.setString(11, passwordHash1);

            int update = ps.executeUpdate();
            if (update>0) {
                System.out.println("Datos insertados correctamente");
            } else {
                System.out.println("Datos no insertados");
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}
    public static void cuentaCliente (Connection connection, Cliente cliente){
        PreparedStatement ps = null;

        String insert = "insert into banco.clientes (cuenta_id) values (?)";

        try {
            ps = connection.prepareStatement(insert);
            ps.setInt(1, cliente.getCuenta_id());
            int update = ps.executeUpdate();
            if (update>0) {
                System.out.println("Datos insertados correctamente");
            } else {
                System.out.println("Datos no insertados");
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}