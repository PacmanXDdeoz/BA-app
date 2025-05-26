package Repository;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

import Config.Con;

import java.sql.ResultSet;

public class ConsultasProductos {
    public static void consultaProd(Scanner sc){
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select * from banco.productos_financieros";

        try {
            connection = Con.getConn();
            st = connection.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
                System.out.print("ID: " + rs.getInt("producto_id") + " ");
                System.out.print("Producto: " + rs.getString("nombre_prod") + " ");
                System.out.print("Tasa de interes: " + rs.getString("tasa_interes") + " ");
                System.out.println("Comision: " + rs.getDouble("comision"));
                System.out.println("Beneficios: " + rs.getString("beneficios"));
            }
        } catch (Exception e) {
            System.err.println("Conexion fallida: " + e.getMessage());
        }
        System.out.println("");
        System.out.println("Presione Enter para volver al menú principal...");
        sc.nextLine();
    }
}
