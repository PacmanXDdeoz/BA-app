package UI;
import Controller.registroEmpleado;
import Model.Cliente;
import Model.Empleados;
import Controller.LoginEmpleado;
import Controller.loginCliente;
import Service.GeneralService;

import java.util.Scanner;

public class MenuLogin {

    public static void menuBienvenida(){
        Scanner sc = new Scanner(System.in);
        boolean tryAgain = false;

        while (true) {
            GeneralService.cleanScreen();
            try {
                System.out.println("** 🏦 Bienvenido a Banco azteca 🏦 **");
                System.out.println("1. Empleado ░░░░░░░ 2. Cliente");
                int opt = sc.nextInt();

                if(tryAgain){
                    System.out.println("⛔ Elige una opcion valida ⛔");
                }
                
                switch (opt) {
                    case 0:
                        GeneralService.cleanScreen();
                        System.out.println("🏦 Gracias por usar Banco Azteca 🏦");
                        return;
                    case 1:
                        GeneralService.cleanScreen();
                        System.out.println("¿Tienes ya una cuenta con nosotros?");
                        System.out.println("1. Si ░░░░░░░░ 2. No");
                        int op = sc.nextInt();
                        if(op == 2){
                            GeneralService.showLoading();
                            GeneralService.cleanScreen();
                            registroEmpleado.rEmpleado();
                            MenuEmpleado.menuEmpleado();
                        } if(op == 1) {
                            loginEmpleado();
                            MenuEmpleado.menuEmpleado();
                        }
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        System.out.println("¿Tienes ya una cuenta con nosotros?");
                        System.out.println("1. Si ░░░░░░░░ 2. No");
                        op = sc.nextInt();
                        if(op == 2){
                            System.out.println("Acude a una sucursal, un empleado te ayudará abrir tu cuenta :D");
                            return;
                        } if (op == 1) {
                            GeneralService.cleanScreen();
                            loginCliente();
                            MenuCliente.menuPrincipal();
                        }
                    default:
                        tryAgain = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
            sc.close();
        }
    }

    public static void loginEmpleado(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n██████INICIO DE SESION██████");
        System.out.println("Correo: ");
        String email = sc.nextLine();

        System.out.println("Contraseña: ");
        String password = sc.nextLine();
        
        Empleados empleados = LoginEmpleado.login(email, password);

        if(empleados != null) {
            System.out.println("Bienvenido: " + empleados.getNombre_empleado() + " " + empleados.getApellido_paterno() + " " + empleados.getApellido_materno());
            System.out.println("Puesto: " + empleados.getPuesto_id());
        } else {
            System.out.println("Lo que ingresaste es incorrecto, intenta de nuevo");
        }
        sc.close();
    }

    public static void loginCliente(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n██████INICIO DE SESION██████");
        System.out.println("Correo: ");
        String email = sc.nextLine();

        System.out.println("Contraseña: ");
        String password = sc.nextLine();
        
        Cliente cliente = loginCliente.login(email, password);

        if(cliente != null) {
            System.out.println("Bienvenido: " + cliente.getNombre_cliente() + " " + cliente.getApellido_paterno() + " " + cliente.getApellido_materno());
        } else {
            System.out.println("Lo que ingresaste es incorrecto, intenta de nuevo");
        }
        sc.close();
    }

}
