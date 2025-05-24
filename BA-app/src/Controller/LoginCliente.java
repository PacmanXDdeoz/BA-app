package Controller;
import Model.Cliente;
import Config.Con;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class LoginCliente {
    public static Cliente login (Connection connection, String email, String password){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Cliente cliente = null;

        try {
            connection = Con.getConn();

            String query = "select * from banco.clientes where email = ? " +
                            "and password = crypt(?, password)";
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            resultSet = ps.executeQuery();

            if (resultSet.next()) {                
                cliente = new Cliente();
                cliente.setCliente_id(resultSet.getInt("cliente_id"));
                cliente.setNombre_cliente(resultSet.getString("nombre_cliente"));
                cliente.setApellido_paterno(resultSet.getString("apellido_paterno"));
                cliente.setApellido_materno(resultSet.getString("apellido_materno"));
                Date sqlDate = resultSet.getDate("fecha_nacimiento");
                if (sqlDate != null) {
                    cliente.setFecha_nacimiento(sqlDate.toLocalDate());
                }
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setOcupacion(resultSet.getString("ocupacion"));
                cliente.setRfc(resultSet.getString("rfc"));
                cliente.setCurp(resultSet.getString("curp"));

                System.out.println("Login exitoso!" + " " + cliente.getNombre_cliente());
            } else {
                System.out.println("Credenciales incorrectas");
            }
            } catch (SQLException e) {
                System.err.println("Error al verificar credenciales: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // ? Cerrar recursos
                try {
                    if (resultSet != null) resultSet.close();
                    if (ps != null) ps.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar recursos: " + e.getMessage());
                }
                Con.closeConnetion(connection);
            }
            return cliente;
        }
}
