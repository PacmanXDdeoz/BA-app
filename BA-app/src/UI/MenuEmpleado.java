package UI;
import java.sql.Connection;
import java.util.Map;
import java.util.Scanner;

import Config.Con;
import Controller.RegistroCliente;
import Controller.RegistroEmpleado;
import Controller.RegistroTarjetas;
import Model.Empleados;
import Repository.ConsultasEmpleados;
import Service.GeneralService;
import Controller.RegistroCuenta;

public class MenuEmpleado {

    public static void menuEmpleado(Empleados empladoActual){
        GeneralService.cleanScreen();

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("████████████████████████████████");
            System.out.println("███ Bienvenido a tu puesto " + empladoActual.getNombre_empleado() + " ███");
            System.out.println("1. Consulta de información");
            System.out.println("2. Registro");
            System.out.println("3. Cuentas");
            System.out.println("4. Inversiones");
            System.out.println("5. Tarjetas");
            System.out.println("6. Productos financieros");
            System.out.println("0. Cerrar sesion");
            System.out.println("████████████████████████████████");
            System.out.print("Elige una opcion: ");
            int opt = sc.nextInt();
            switch (opt) {
                case 0:
                GeneralService.cleanScreen();
                System.out.println("Fin de tu jornada! hasta luego :D");
                MenuLogin.menuBienvenida();
                case 1:
                GeneralService.cleanScreen();
                System.out.println("】Menu de Consultas【");
                System.out.println("1. Tus datos");
                System.out.println("2. Busca a un cliente por curp");
                System.out.println("0. Regresar");
                System.out.print("Selecciona una opcion: ");
                int op = sc.nextInt();
                do {
                    if (op == 1) {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        verInfoEmplado(empladoActual.getEmpleado_id());
                        menuEmpleado(empladoActual);
                    } if (op == 2) {
                        GeneralService.cleanScreen();
                        menuEmpleado(empladoActual);
                    } if (op == 0) {
                        menuEmpleado(empladoActual);
                    } else {
                        System.out.println("Selecciona una opcion valida");
                    }
                } while (op > 2);
                break;
                case 2:
                GeneralService.cleanScreen();
                System.out.println("】Menu de Registro【");
                System.out.println("1. Nuevo Cliente");
                System.out.println("2. Nuevo Empleado");
                System.out.println("0. Regresar");
                System.out.print("Selecciona una opcion: ");
                op = sc.nextInt();
                do {
                    if (op == 1) {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        RegistroCliente.rClientes();
                        MenuEmpleado.menuEmpleado(empladoActual);
                        
                    } if (op == 2) {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        RegistroEmpleado.rEmpleado();
                    } if (op == 0) {
                        GeneralService.cleanScreen();
                        MenuEmpleado.menuEmpleado(empladoActual);
                    } else {
                        System.out.println("Selecciona una opcion valida");
                    }
                } while (op > 2);
                case 3:
                GeneralService.cleanScreen();
                System.out.println("】Menu de Cuentas【");
                System.out.println("1. Aperturar una cuenta");
                System.out.println("2. Eliminar una cuenta");
                System.out.println("3. Actualizar una cuenta");
                System.out.println("0. Regresar");
                System.out.print("Selecciona una opcion: ");
                op = sc.nextInt();
                do {
                    if (op == 1) {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        RegistroCuenta.rCuentas();
                        MenuEmpleado.menuEmpleado(empladoActual);
                        break;
                    } if (op == 2) {
                        GeneralService.cleanScreen();
                        // ! Eliminar cuenta
                    } if (op == 3) {
                        GeneralService.cleanScreen();
                        // ! Actualizar cuenta
                    } if (op == 0) {
                        GeneralService.cleanScreen();
                        menuEmpleado(empladoActual);
                    } else {
                        System.out.println("Selecciona una opcion valida");
                    }
                } while (op > 3);
                break;
                case 4:
                GeneralService.cleanScreen();
                System.out.println("】Menu de Inversiones【");
                System.out.println("1. Aperturar una inversion");
                System.out.println("2. Eliminar una inversion");
                System.out.println("3. Actualizar una inversion");
                System.out.println("0. Regresar");
                System.out.print("Selecciona una opcion: ");
                op = sc.nextInt();
                do {
                    if (op == 1) {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        // ! Registrar
                        MenuEmpleado.menuEmpleado(empladoActual);
                        break;
                    } if (op == 2) {
                        GeneralService.cleanScreen();
                        // ! Eliminar cuenta
                    } if (op == 3) {
                        GeneralService.cleanScreen();
                        // ! Actualizar cuenta
                    } if (op == 0) {
                        GeneralService.cleanScreen();
                        menuEmpleado(empladoActual);
                    } else {
                        System.out.println("Selecciona una opcion valida");
                    }
                } while (op > 3);
                break;
                case 5:
                GeneralService.cleanScreen();
                System.out.println("】Menu de Tarjetas【");
                System.out.println("1. Registrar una tarjeta");
                System.out.println("2. Dar de baja una tarjeta");
                System.out.println("3. Renovar Tarjeta");
                System.out.println("0. Regresar");
                System.out.print("Selecciona una opcion: ");
                op = sc.nextInt();
                do {
                    if (op == 1) {
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        RegistroTarjetas.rTarjetas();
                        MenuEmpleado.menuEmpleado(empladoActual);
                    } if (op == 2) {
                        GeneralService.cleanScreen();
                        // ! Eliminar cuenta
                        menuEmpleado(empladoActual);
                    } if (op == 3) {
                        GeneralService.cleanScreen();
                        // ! Actualizar cuenta
                        menuEmpleado(empladoActual);
                    } if (op == 0) {
                        GeneralService.cleanScreen();
                        menuEmpleado(empladoActual);
                    } else {
                        System.out.println("Selecciona una opcion valida");
                    }
                } while (op > 3);
                break;
                case 6:
                GeneralService.cleanScreen();
                GeneralService.cleanScreen();
                GeneralService.showLoading();
                GeneralService.cleanScreen();
                // ! Consultar
                MenuEmpleado.menuEmpleado(empladoActual);
                break;
                default:
                System.out.println("Selecciona una opcion valida");
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
                System.out.println("Email: " + detalles.get("correo"));
                System.out.println("Teléfono: " + detalles.get("telefono"));
                System.out.println("");
                System.out.println("Sucursal: " + detalles.get("sucursal"));
                System.out.println("Departamento: " + detalles.get("departamento"));
                System.out.println("Puesto: " + detalles.get("puesto"));
                System.out.println("Salario: $" + detalles.get("salario"));
                
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