package UI;

import java.util.Scanner;
import java.util.Map;

import java.sql.Connection;

import Config.Con;
import Model.Cliente;
import Service.GeneralService;
import Repository.ConsultaCliente;

public class MenuCliente {
    
    public static void menuPrincipal(Cliente clienteActual){
        Scanner sc = new Scanner(System.in);


        while (true) {
            
        try {
            GeneralService.cleanScreen();
            GeneralService.showLoading();
            GeneralService.cleanScreen();
            System.out.println("████████████████████████████████");
            System.out.println("Bienvenido " + clienteActual.getNombre_cliente() + "!");
            System.out.println("Menu principal: ");
            System.out.println("1. Tus datos");
            System.out.println("2. Cuentas");
            System.out.println("3. Inversiones");
            System.out.println("4. Prestamos");
            System.out.println("5. Servicios");
            System.out.println("6. Productos disponibles");
            System.out.println("7. Beneficiarios");
            System.out.println("0. Cerrar sesion");
            System.out.println("████████████████████████████████");
            System.out.print("Selecciona una opcion: ");
            int opt = sc.nextInt();

            switch (opt) {
                case 0:
                GeneralService.cleanScreen();
                System.out.println("Gracias por usar Banco Azteca!");
                MenuLogin.menuBienvenida();

                case 1:
                GeneralService.cleanScreen();
                GeneralService.showLoading();
                GeneralService.cleanScreen();
                verInfoCliente(clienteActual.getCliente_id());
                menuPrincipal(clienteActual);
                break;
                case 2:
                GeneralService.cleanScreen();
                System.out.println("】Menu Cuentas【");
                System.out.println("1. Tus cuentas");
                System.out.println("2. Historial de transacciones");
                System.out.println("3. Realizar una transaccion");
                System.out.println("0. Regresar");
                System.out.print("Selecciona una opcion: ");
                int op = sc.nextInt();
                do {
                    if (op == 1) {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        // ! consulta
                        MenuCliente.menuPrincipal(clienteActual);
                    } if (op == 2) {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        // ! consulta
                        MenuCliente.menuPrincipal(clienteActual);
                    } if (op == 3) {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        // ! consulta
                        MenuCliente.menuPrincipal(clienteActual);
                    } if (op == 0) {
                        GeneralService.cleanScreen();
                        MenuCliente.menuPrincipal(clienteActual);
                    }
                } while (op > 3);
                MenuCliente.menuPrincipal(clienteActual);
                break;
                case 3:
                GeneralService.cleanScreen();
                // ! Consulta inversiones
                case 4:
                GeneralService.cleanScreen();
                // ! Consulta prestamos
                default:
                System.out.println("Selecciona una opcion valida");
                break;
            }

        } catch (Exception e) {
            System.out.println("Error" + e);
    }
    }
}

private static void verInfoCliente(int clienteId){
        Scanner sc = new Scanner(System.in);
        System.out.println("☰☰☰ Mi información ☰☰☰");
        Connection connection = null;
        try {
            connection = Con.getConn();

            Map<String, Object> detalles = ConsultaCliente.consultaCliente(connection, clienteId);

            if (detalles != null) {
                System.out.println("ID: " + detalles.get("cliente_id"));
                System.out.println("Nombre completo: " + detalles.get("nombre_completo"));
                System.out.println("Email: " + detalles.get("email"));
                System.out.println("Teléfono: " + detalles.get("telefono"));
                System.out.println("Direccion: " + detalles.get("direccion"));
                System.out.println("RFC: " + detalles.get("rfc"));
                System.out.println("CURP: " + detalles.get("curp"));

                Object cuenta = detalles.get("cuenta_id");
                if (cuenta != null) {
                    System.out.println("Info de cuenta: ");
                    System.out.println("Numero de cuenta: " + detalles.get("numero_cuenta"));
                    System.out.println("Saldo actual:" + detalles.get("monto"));
                } else {
                    System.out.println("No tienes una cuenta activa");
                    System.out.println("Acude a una sucursal para aperturarla");
                    System.out.println("Hazlo desde $50 mxn pesos");
                }

                if (detalles.get("prestamo_id") != null) {
                    System.out.println("Info de prestamo: ");
                    System.out.println("Pago mensual: " + detalles.get("pago_mensual"));
                    System.out.println("Monto solicitado: " + detalles.get("monto"));
                } else {
                    System.out.println("No tienes un prestamo activo");
                    System.out.println("Acude a una sucursal para solicitarlo");
                }

                if (detalles.get("inversion_id") != null) {
                    System.out.println("Info de Inversion: ");
                    System.out.println("Tipo de inversion: " + detalles.get("tipo_inversion"));
                    System.out.println("Monto actual: " + detalles.get("monto_actual"));
                } else {
                    System.out.println("No tienes una inversion activa");
                    System.out.println("Acude a sucursal para activarlo, puedes hacerlo desde $1000 mxn pesos");
                }
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
