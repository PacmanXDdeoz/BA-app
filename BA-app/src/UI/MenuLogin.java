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

    public static void menuBienvenida(){
        Scanner sc = new Scanner(System.in);
        

        while (true) {
            try {
                System.out.println("█████████████████████████████████████");
                System.out.println("");
                System.out.println("** 🏦 Bienvenido a Banco azteca 🏦 **");
                System.out.println("-- 1. Empleado ░░░░░░░░ 2. Cliente --");
                System.out.println("");
                System.out.println("█████████████████████████████████████");
                System.out.println("--------0. Cerrar aplicacion---------");
                int opt = sc.nextInt();

                switch (opt) {
                    case 0:
                    GeneralService.cleanScreen();
                    System.out.println("Esperemos verte pronto de nuevo! :D");
                    break;
                    case 1:
                    GeneralService.cleanScreen();
                    System.out.println("¿Ya tienes una cuenta con nosotros?");
                    System.out.println("------ 1. Si ░░░░░░░░ 2. No -------");
                    int op = sc.nextInt();
                    do {
                    if (op == 1) {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        iniciarSesion();
                    } if (op == 2) {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        RegistroEmpleado.rEmpleado();
                        iniciarSesion();
                    } else {
                        System.out.println("Selecciona una opcion valida");
                    }} while (op > 2);
                    break;
                    case 2:
                    GeneralService.cleanScreen();
                    System.out.println("¿Tienes ya una cuenta con nosotros?");
                    System.out.println("------ 1. Si ░░░░░░░░ 2. No -------");
                    op = sc.nextInt();
                    do {
                    if (op == 1) {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        iniciarSesionCliente();
                    } if (op == 2) {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.showLoading();
                        System.out.println("Por favor acuda a una sucursal para aperturar una cuenta ");
                        sc.nextLine();
                        MenuLogin.menuBienvenida();
                    } else {
                        System.out.println("Selecciona una opcion valida");
                    }} while (op > 2);

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
        System.out.println("▶ INICIAR SESION ◀");
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
        Scanner sc = new Scanner(System.in);
        System.out.println("▶ INICIAR SESION ◀");
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
