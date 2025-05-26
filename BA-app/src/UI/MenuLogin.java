package UI;

import Model.Cliente;
import Model.Empleados;
import Controller.LoginEmpleado;
import Controller.RegistroEmpleado;
import Controller.LoginCliente;
import Service.GeneralService;

import java.sql.*;

import java.util.Scanner;

import Config.Con;

public class MenuLogin {

    private static final Scanner sc = new Scanner(System.in);

    public static void menuBienvenida(){

        while (true) {
            try {
                GeneralService.cleanScreen();
                System.out.println("█████████████████████████████████████");
                System.out.println("");
                System.out.println("** 🏦 Bienvenido a Banco azteca 🏦 **");
                System.out.println("-- 𝟭. Empleado ░░░░░░░░ 𝟮. Cliente --");
                System.out.println("");
                System.out.println("█████████████████████████████████████");
                System.out.println("--------𝟬. Cerrar aplicacion---------");
                int opt = sc.nextInt();
                switch (opt) {
                    case 0:
                        GeneralService.cleanScreen();
                        System.out.println("Esperemos verte pronto de nuevo! :D");
                        System.exit(0);                        
                        break;
                    case 1:
                        GeneralService.cleanScreen();
                        System.out.println("≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡");
                        System.out.println("¿Ya tienes una cuenta con nosotros?");
                        System.out.println("------ 𝟭. Si ░░░░░░░░ 𝟮. No -------");
                        System.out.println("≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡");
                        System.out.print("Selecciona una opcion: ");
                        int op = sc.nextInt();
                        sc.nextLine();
                        switch (op) {
                            case 1:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                iniciarSesion();
                                break;
                            case 2:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                RegistroEmpleado.rEmpleado(sc);
                                iniciarSesion();
                                break;
                            default:
                                System.out.println("Selecciona una opcion valida");
                                break;
                        } break;
                    case 2:
                        GeneralService.cleanScreen();
                        System.out.println("≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡");
                        System.out.println("¿Tienes ya una cuenta con nosotros?");
                        System.out.println("------ 𝟭. Si ░░░░░░░░ 𝟮. No -------");
                        System.out.println("≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡");
                        System.out.print("Selecciona una opcion: ");
                        op = sc.nextInt();
                        sc.nextLine();
                        switch (op) {
                            case 1:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                iniciarSesionCliente();
                                break;
                            case 2:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                System.out.println("🤑 Por favor acuda a una sucursal para aperturar una cuenta 😃");
                                sc.nextLine();
                                break;
                            default:
                                System.out.println("Selecciona una opcion valida");
                                break;
                        } break;
                    default:
                        System.out.println("Selecciona una opcion valida");
                        break;
                }
            } catch (Exception e) {
                System.err.println("Error en el menu: " + e.getMessage());
            }
        }
    }

    public static Empleados iniciarSesion(){
        GeneralService.cleanScreen();
        System.out.println("💲 ▶ INICIAR SESION EMPLEADOS ◀ 💲");
        System.out.print("Ingresa tu correo: ");
        String email = sc.nextLine();
        System.out.print("Ingresa tu contraseña: ");
        String pass = sc.nextLine();

        Connection connection = null;
        Empleados empleadoActual = null;

        try {
            connection = Con.getConn();

            empleadoActual = LoginEmpleado.login(connection, email, pass);

            if (empleadoActual != null) {
                MenuEmpleado.menuEmpleado(empleadoActual);
            } else {
                System.out.println("Email o contraseña invalida");
            }
        } catch (Exception e) {
            System.out.println("Error al iniciar sesion: " + e.getMessage());
            e.printStackTrace();
        }
        return empleadoActual;
    }

    public static Cliente iniciarSesionCliente(){
        GeneralService.cleanScreen();
        System.out.println("💲 ▶ INICIAR SESION CLIENTE ◀ 💲");
        System.out.print("Ingresa tu correo: ");
        String email = sc.nextLine();
        System.out.print("Ingresa tu contraseña: ");
        String pass = sc.nextLine();

        Connection connection = null;
        Cliente clienteActual = null;

        try {
            connection = Con.getConn();

            clienteActual = LoginCliente.login(connection, email, pass);

            if (clienteActual != null) {
                MenuCliente.menuPrincipal(clienteActual);
            } else {
                System.out.println("Email o contraseña invalida");
            }
        } catch (Exception e) {
            System.out.println("Error al iniciar sesion: " + e.getMessage());
            e.printStackTrace();
        }
        return clienteActual;
    }
}
