package Repository;
import Config.Con;
import Service.GeneralService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConsultasEmpleados {

    public static Map<String, Object> consultaEmpleados(Connection connection, int empleadoId){
        GeneralService.cleanScreen();
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        Map<String, Object> detallesEmp = new HashMap<>();

        try {
            connection = Con.getConn();

            String query = "select empleado_id, nombre_empleado, apellido_paterno, apellido_materno, telefono_empleado, email_empleado, salario, nombre_sucursal, nombre_dept, puesto_nom from banco.empleados e left join banco.sucursales s on e.sucursal_id = s.sucursal_id left join banco.departamentos d on e.departamento_id = d.departamento_id left join banco.puesto p on e.puesto_id = p.puesto_id where e.empleado_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, empleadoId);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                detallesEmp.put("empleado_id", resultSet.getInt("empleado_id"));
                detallesEmp.put("nombre_completo", resultSet.getString("nombre_empleado") + " " + resultSet.getString("apellido_paterno") + " " + resultSet.getString("apellido_materno"));
                detallesEmp.put("correo", resultSet.getString("email_empleado"));
                detallesEmp.put("telefono", resultSet.getString("telefono_empleado"));
                detallesEmp.put("sucursal", resultSet.getString("nombre_sucursal"));
                detallesEmp.put("departamento", resultSet.getString("nombre_dept"));
                detallesEmp.put("puesto", resultSet.getString("puesto_nom"));
                detallesEmp.put("salario", resultSet.getDouble("salario"));
            } else {
                System.out.println("No se encontraron datos de este ID: " + empleadoId);
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return detallesEmp;
    }
}
