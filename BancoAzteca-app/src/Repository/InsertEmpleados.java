package Repository;

import Model.Empleados;
import Config.Con;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertEmpleados {

    public static void iEmpleados(Connection connection, Empleados empleados) throws SQLException{
        Con.getConn();
        PreparedStatement ps = null;
        String insert = "insert into banco.empleados (sucursal_id, departamento_id, nombre_empleado, apellido_paterno, apellido_materno, telefono_empleado, email_empleado, password_empleado, puesto, salario) values (?,?,?,?,?,?,?,crypt(?, gen_salt('bf',10)),?,?)";

        try{
            ps = connection.prepareStatement(insert);

            ps.setInt(1, empleados.getSucursal_id());
            ps.setInt(2, empleados.getDepartamento_id());
            ps.setString(3, empleados.getNombre_empleado());
            ps.setString(4, empleados.getApellido_paterno());
            ps.setString(5, empleados.getApellido_materno());
            ps.setString(6, empleados.getTelefono_empleado());
            ps.setString(7, empleados.getEmail_empleado());
            ps.setString(9, empleados.getPassword_empleado());
            ps.setInt(9, empleados.getPuesto_id());
            ps.setDouble(10, empleados.getSalario());

            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}