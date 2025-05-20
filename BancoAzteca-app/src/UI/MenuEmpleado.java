package UI;
import java.sql.Connection;
import java.util.Map;
import java.util.Scanner;

import Config.Con;
import Controller.registroCliente;
import Controller.RegistroEmpleado;
import Model.Empleados;
import Repository.ConsultasEmpleados;
import Service.GeneralService;

public class MenuEmpleado {

    public static void menuEmpleado(){
        GeneralService.cleanScreen();
        Connection connection;
        Empleados empleado = new Empleados();
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("███ Bienvenido a tu puesto " + empleado.getNombre_empleado() + " ███");
            System.out.println("Elige una opcion");
            System.out.println("1. Consultas");
            System.out.println("2. Registro");
            System.out.println("3. Cuentas");
            System.out.println("4. Inversiones");
            System.out.println("5. Tarjetas");
            System.out.println("6. Productos financieros");
            System.out.println("7. ");
            int opt = sc.nextInt();
            switch (opt) {
                case 1:
                    GeneralService.cleanScreen();
                    GeneralService.showLoading();
                    verInfoEmplado(empleado.getEmpleados_id());

                    break;
            
                default:
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error en el menu: " + e.getMessage());
        }
    }

    private static void verInfoEmplado(int empladoId){
        Scanner sc = new Scanner(System.in);
        System.out.println("☰☰☰ Mi información ☰☰☰");
        Connection connection = null;
        try {
            connection = Con.getConn();

            Map<String, Object> detalles = ConsultasEmpleados.consultaEmpleados(connection, empladoId);
            if (detalles != null) {

                System.out.println("ID: " + detalles.get("empleado_id"));
                System.out.println("Nombre completo: " + detalles.get("nombre_completo"));
                System.out.println("Email: " + detalles.get("email"));
                System.out.println("Teléfono: " + detalles.get("telefono"));
                System.out.println("");
                System.out.println("Sucursal: " + detalles.get("sucursal"));
                System.out.println("Departamento: " + detalles.get("departamento"));
                System.out.println("Puesto: " + detalles.get("puesto"));
                System.out.println("Salario: $" + String.format("%.2f", detalles.get("salario")));
                
            } else {
                System.out.println("No se pudo obtener la información. Intente nuevamente más tarde.");
            }
        } catch (Exception e) {
            System.err.println("Error al consultar informacion: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Con.closeConnetion(connection);
        }

        System.out.println("");
        System.out.println("Presione Enter para volver al menú principal...");
        sc.nextLine();
    }
}