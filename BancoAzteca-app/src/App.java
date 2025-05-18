import java.sql.*;
import Config.Con;
import UI.MenuLogin;
import Controller.registroEmpleado;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Bienvenido");
        Con con = null;
        Connection connection = null;

        MenuLogin.menuBienvenida();

        try {
            connection = con.getConn();
            System.out.println("Conexión correcta");
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        } finally {
            con.closeConnetion(connection);
        }
    }
}