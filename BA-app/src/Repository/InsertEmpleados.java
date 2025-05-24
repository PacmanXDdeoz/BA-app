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

        String encryptPasswordSQL = "SELECT crypt(?, gen_salt('bf', 10)) as password_hash from banco.empleados";
        String passwordHash = null;
        try (PreparedStatement psEncrypt = connection.prepareStatement(encryptPasswordSQL)){
            psEncrypt.setString(1, empleados.getPassword_empleado());
        try (var rs = psEncrypt.executeQuery()) {
            if (rs.next()) {
                passwordHash = rs.getString("password_hash");
            }
        }

        String insert = "insert into banco.empleados (sucursal_id, departamento_id, nombre_empleado, apellido_paterno, apellido_materno, telefono_empleado, email_empleado, password_empleado, puesto_id, salario)" + 
        "values (?,?,?,?,?,?,?,?,?,?)";

        try{
            ps = connection.prepareStatement(insert);

            ps.setInt(1, empleados.getSucursal_id());
            ps.setInt(2, empleados.getDepartamento_id());
            ps.setString(3, empleados.getNombre_empleado());
            ps.setString(4, empleados.getApellido_paterno());
            ps.setString(5, empleados.getApellido_materno());
            ps.setString(6, empleados.getTelefono_empleado());
            ps.setString(7, empleados.getEmail_empleado());
            ps.setString(8, passwordHash);
            ps.setInt(9, empleados.getPuesto_id());
            ps.setDouble(10, empleados.getSalario());

            int update = ps.executeUpdate();
            if (update>0) {
                System.out.println("Datos insertados correctamente");
            } else {
                System.out.println("Datos no insertados");
            }
        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}
}