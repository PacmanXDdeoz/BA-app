package UI;
import Controller.registroEmpleado;
import Controller.registroCliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuLogin {

    public static void menuBienvenida(){
        Scanner sc = new Scanner(System.in);
        boolean tryAgain = false;

        while (true) {
            try {
                System.out.println("** 🏦 Bienvenido a Banco azteca 🏦 **");
                System.out.println("1. Empleado ░░░░░░░ 2. Cliente");
                int opt = sc.nextInt();

                if(tryAgain){
                    System.out.println("⛔ Elige una opcion valida ⛔");
                }
                
                switch (opt) {
                    case 1:
                        System.out.println("¿Tienes ya una cuenta con nosotros?");
                        System.out.println("1. Si ░░░░░░░░ 2. No");
                        int op = sc.nextInt();
                        if(op == 2){
                            registroEmpleado.rEmpleado();
                        } if(op == 1) {
                            loginEmpleado();
                        }
                        break;
                    case 2:
                        System.out.println("¿Tienes ya una cuenta con nosotros?");
                        System.out.println("1. Si ░░░░░░░░ 2. No");
                        op = sc.nextInt();
                        if(op == 2){
                            System.out.println("Acude a una sucursal, un empleado te ayudará abrir tu cuenta :D");
                        } if (op == 1) {
                            // ! Funcion para loginCliente
                        }
                    default:
                        tryAgain = true;
                        break;
                }
            } catch (Exception e) {
            }
        }
    }

    public static void loginEmpleado(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n██████INICIO DE SESION██████");
        System.out.println("Correo: ");
        String email = sc.nextLine();

        System.out.println("Contraseña: ");
        String password = sc.nextLine();

        
        sc.close();
    }

}
