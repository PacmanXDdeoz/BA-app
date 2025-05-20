package UI;

import Model.Empleados;
import Controller.LoginEmpleado;
import Controller.RegistroEmpleado;
import Controller.loginCliente;
import Service.GeneralService;

import java.sql.*;

import java.util.Scanner;

import Config.Con;

public class MenuLogin {

    public static void menuBienvenida(){
        Scanner sc = new Scanner(System.in);
        Connection connection = null;
        

        while (true) {
            try {
                System.out.println("** 🏦 Bienvenido a Banco azteca 🏦 **");
                System.out.println("-- 1. Empleado ░░░░░░░░ 2. Cliente --");
                int opt = sc.nextInt();

                switch (opt) {
                    case 1:
                    GeneralService.cleanScreen();
                    System.out.println("¿Ya tienes una cuenta con nosotros?");
                    System.out.println("1. Si ░░░░░░░░ 2. No");
                    int op = sc.nextInt();
                    if (op == 1) {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        iniciarSesion();
                        MenuEmpleado.menuEmpleado();
                    } else {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        RegistroEmpleado.rEmpleado();
                        MenuEmpleado.menuEmpleado();
                    }
                    
                    break;
                    case 2:
                        System.out.println("¿Tienes ya una cuenta con nosotros?");
                        System.out.println("1. Si ░░░░░░░░ 2. No");
                        
                
                    default:
                        break;
                }
            } catch (Exception e) {
            }
        }
    }

    public static Empleados iniciarSesion(){
        GeneralService.cleanScreen();
        Scanner sc = new Scanner(System.in);
        System.out.println("▶INICIAR SESION◀");
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
                System.out.println("Email o contraseña invalida");
            }
        } catch (Exception e) {
            System.out.println("Error al iniciar sesion: " + e.getMessage());
            e.printStackTrace();
        }
        return empleadoActual;
    }
}
