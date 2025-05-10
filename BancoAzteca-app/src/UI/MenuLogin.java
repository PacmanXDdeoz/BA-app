package UI;
import Model.Cliente;

import java.util.Scanner;
public class MenuLogin {
    public static void menuLogin(){
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("** 🇲🇽 Bienvenido a Banco azteca 🏦 **");
                System.out.println("1. Empleado ░░░░░░░ 2. Cliente");
                int opt = sc.nextInt();

                switch (opt) {
                    case 1:
                        System.out.println("Bienvenido a tu trabajo");
                        break;
                    case 2:
                        System.out.println("¿Tienes ya una cuenta con nosotros?");
                        System.out.println("1. Si ░░░░░░░░ 2. No");
                        
                
                    default:
                        break;
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
