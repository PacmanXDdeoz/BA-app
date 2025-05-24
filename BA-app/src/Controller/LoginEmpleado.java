package Controller;
import Model.Empleados;
import Config.Con;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginEmpleado {
    public static Empleados login (Connection connection, String email, String password){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Empleados empleado = null;

        try {
            connection = Con.getConn();

            String query = "select * from banco.empleados where email_empleado = ? " +
                            "and password_empleado = crypt(?, password_empleado)";
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            resultSet = ps.executeQuery();

            if (resultSet.next()) {                
                empleado = new Empleados();
                empleado.setEmpleado_id(resultSet.getInt("empleado_id"));
                empleado.setSucursal_id(resultSet.getInt("sucursal_id"));
                empleado.setDepartamento_id(resultSet.getInt("departamento_id"));
                empleado.setNombre_empleado(resultSet.getString("nombre_empleado"));                    empleado.setApellido_paterno(resultSet.getString("apellido_paterno"));
                empleado.setApellido_materno(resultSet.getString("apellido_materno"));
                empleado.setPuesto_id(resultSet.getInt("puesto_id"));
                empleado.setSalario(resultSet.getDouble("salario"));
                empleado.setTelefono_empleado(resultSet.getString("telefono_empleado"));
                empleado.setEmail_empleado(resultSet.getString("email_empleado"));                    

                System.out.println("Login exitoso!" + " " + empleado.getNombre_empleado());
            } else {
                System.out.println("Credenciales incorrectas");
            }
            } catch (SQLException e) {
                System.out.println("Error al verificar credenciales: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // ? Cerrar recursos
                try {
                    if (resultSet != null) resultSet.close();
                    if (ps != null) ps.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar recursos: " + e.getMessage());
                }
                Con.closeConnetion(connection);
            }
            return empleado;
        }
}
