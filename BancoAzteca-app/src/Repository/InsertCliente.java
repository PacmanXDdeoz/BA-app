package Repository;
import Model.Cliente;
import Config.Con;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCliente {

    public static void iCliente(Connection connection, Cliente cliente)throws SQLException{
        Con.getConn();
        PreparedStatement ps = null;
        String insert = "insert into banco.cliente(Nombre_cliente, apellido_paterno, apellido_materno, fecha_nacimiento, telefono, direccion, email, ocupacion, rfc, curp)";

        try {
            ps = connection.prepareStatement(insert);
            ps.setString(1, cliente.getNombre_cliente());
            ps.setString(2, cliente.getApellido_paterno());
            ps.setString(3, cliente.getApellido_materno());
            ps.setDate(4, cliente.getFecha_nacimiento());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getDireccion());
            ps.setString(7, cliente.getEmail());
            ps.setString(8, cliente.getOcupacion());
            ps.setString(9, cliente.getRfc());
            ps.setString(10, cliente.getCurp());

            ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
