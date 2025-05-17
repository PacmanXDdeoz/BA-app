package Repository;

import Model.Empleados;
import Config.Con;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertEmpleados {

    public static void insertEmpleados(Connection connection, Empleados empleados) throws SQLException{
        Con.getConn();
        PreparedStatement ps = null;
        String insert = "insert into banco.empleados(sucursal_id, departamento_id, nombre_empleado, apellido_paterno, apellido_materno, puesto, salario, telefono_empleado, email_empleado, password_empleado) values (?,?,?,?,?,?,?,?,?)";

        try {
            ps = connection.prepareStatement(insert);
            ps.setInt(1, empleados.getSucursal_id());
            ps.setInt(2, empleados.getDepartamento_id());
            ps.setString(3, empleados.getNombre_empleado());
            ps.setString(4, empleados.getApellido_paterno());
            ps.setString(5, empleados.getApellido_materno());
            ps.setString(6, empleados.getPuesto());
            ps.setDouble(7, empleados.getSalario());
            ps.setString(8, empleados.getTelefono_empleado());
            ps.setString(9, empleados.getEmail_empleado());
            ps.setString(10, empleados.getPassword_empleado());

            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}