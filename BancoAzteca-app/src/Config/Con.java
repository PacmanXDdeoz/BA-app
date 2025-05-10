package Config;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Con {
    private final static String URL = "jdbc:postgresql://localhost:5432/banco_azteca";
    private final static String USER = "admin";
    private final static String PASS = "123456";

    public static Connection getConn() throws SQLException {
    try{
        return DriverManager.getConnection(URL, USER, PASS);
    } catch(SQLException e){
        throw new SQLException("Driver no encontrado", e);
    }
}

public static void closeConnetion(Connection connection){
    if(connection != null){
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
}