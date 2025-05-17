package Controller;
import Model.Empleados;
import Config.Con;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginEmpleado {
    public static Empleados login (String email, String password){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = Con.getConn();

            String query = "select * from banco.empleados where email_empleado = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, email);

            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String storedHash = resultSet.getString("password_empleado");

                String inputHash = hashPassword(password);
                
            }
            return null;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
