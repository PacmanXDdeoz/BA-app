package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Config.Con;
import Model.Cliente;
import Service.PassClienteService;

public class loginCliente {
    public static Cliente login (String email, String password){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = Con.getConn();

            String query = "select * from banco.cliente where email_empleado = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, email);

            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String storedHash = resultSet.getString("password_empleado");

                String inputHash = PassClienteService.hashPass(password);
                
                if (storedHash.equals(inputHash)) {
                    Cliente cliente = new Cliente();
                    cliente.setCliente_id(resultSet.getInt("cliente_id"));
                    cliente.setNombre_cliente(resultSet.getString("nombre_cliente"));
                    cliente.setApellido_paterno(resultSet.getString("apellido_paterno"));
                    cliente.setApellido_materno(resultSet.getString("apellido_materno"));
                    cliente.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
                    cliente.setTelefono(resultSet.getString("telefono"));
                    cliente.setDireccion(resultSet.getString("direccion"));
                    cliente.setEmail(resultSet.getString("email"));
                    cliente.setRfc(resultSet.getString("curp"));
                    
                    return cliente;
                }
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Con.closeConnetion(connection);
        }
    }
}
