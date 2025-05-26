package UI;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Map;
import java.util.Scanner;
import Config.Con;
import Controller.EliminarRegistros;
import Controller.RegistroCliente;
import Controller.RegistroEmpleado;
import Controller.RegistroInversiones;
import Controller.RegistroPrestamos;
import Controller.RegistroTarjetas;
import Controller.UpdateRegistros;
import Controller.RegistroCuenta;
import Model.Cliente;
import Model.Empleados;
import Repository.ConsultasEmpleados;
import Repository.ConsultasProductos;
import Service.GeneralService;
import Service.BusquedaCliente;
import Service.ServicioTransacciones;

public class MenuEmpleado {

    private static final Scanner sc = new Scanner(System.in);

    public static void menuEmpleado(Empleados empladoActual) {

        while (true) {
            try {
                GeneralService.cleanScreen();
                System.out.println("██████████████████████████████████████████████");
                System.out.println("Bienvenido a tu puesto " + empladoActual.getNombre_empleado());
                System.out.println("1. Consultas");
                System.out.println("2. Registro");
                System.out.println("3. Cuentas");
                System.out.println("4. Inversiones");
                System.out.println("5. Tarjetas");
                System.out.println("6. Productos financieros");
                System.out.println("7. Prestamos");
                System.out.println("0. Cerrar sesion");
                System.out.println("██████████████████████████████████████████████");
                System.out.print("Elige una opcion: ");
                int opt = sc.nextInt();
                sc.nextLine();

                switch (opt) {
                    case 0:
                        GeneralService.cleanScreen();
                        System.out.println("Fin de tu jornada! hasta luego :D");
                        MenuLogin.menuBienvenida();
                        return;
                    case 1:
                        GeneralService.cleanScreen();
                        System.out.println("███████████████████████████████████████████████");
                        System.out.println("");
                        System.out.println("☰☰☰☰☰☰☰☰☰Menu de Consultas☰☰☰☰☰☰☰☰☰☰☰");
                        System.out.println("𝟭. Tus datos ");
                        System.out.println("𝟮. Busca a un cliente por curp");
                        System.out.println("");
                        System.out.println("██████████████████████████████████████████████");
                        System.out.println("----------------𝟬. Regresar-------------------");
                        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
                        System.out.print("Selecciona una opcion: ");
                        int opConsulta = sc.nextInt();
                        sc.nextLine();
                        switch (opConsulta) {
                            case 1:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                verInfoEmplado(empladoActual.getEmpleado_id());
                                break;
                            case 2:
                                GeneralService.cleanScreen();
                                System.out.print("Ingrese la CURP del cliente: ");
                                String curp = sc.nextLine();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                Cliente foundClient = BusquedaCliente.buscarClientePorCurp(curp);

                                if (foundClient != null) {
                                    verInfoCliente(foundClient.getCliente_id());
                                } else {
                                    System.out.println("Cliente no encontrado con la CURP: " + curp);
                                    System.out.println("\nPresione Enter para continuar...");
                                    sc.nextLine();
                                }
                                break;
                            case 0:
                                GeneralService.cleanScreen();
                                break;
                            default:
                                System.out.println("Selecciona una opcion valida.");
                                System.out.println("\nPresione Enter para continuar...");
                                sc.nextLine();
                                break;
                        }
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        System.out.println("███████████████████████████████████████████████");
                        System.out.println("");
                        System.out.println("☰☰☰☰☰☰☰☰☰ Menu de Registro ☰☰☰☰☰☰☰☰☰☰");
                        System.out.println("𝟭. Nuevo Cliente");
                        System.out.println("𝟮. Nuevo Empleado");
                        System.out.println("");
                        System.out.println("███████████████████████████████████████████████");
                        System.out.println("----------------𝟬. Regresar--------------------");
                        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
                        System.out.print("Selecciona una opcion: ");
                        int opRegistro = sc.nextInt();
                        sc.nextLine();
                        switch (opRegistro) {
                            case 1:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                RegistroCliente.rClientes(sc);
                                System.out.println("\nCliente registrado exitosamente. Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                            case 2:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                RegistroEmpleado.rEmpleado(sc);
                                System.out.println("\nEmpleado registrado exitosamente. Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                            case 0:
                                GeneralService.cleanScreen();
                                break;
                            default:
                                System.out.println("Selecciona una opcion valida.");
                                System.out.print("Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                        }
                        break;
                    case 3:
                        GeneralService.cleanScreen();
                        System.out.println("███████████████████████████████████████████████");
                        System.out.println("");
                        System.out.println("☰☰☰☰☰☰☰☰☰☰ Menu de Cuentas ☰☰☰☰☰☰☰☰☰☰");
                        System.out.println("𝟭. Aperturar una cuenta");
                        System.out.println("𝟮. Eliminar una cuenta");
                        System.out.println("𝟯. Realizar Operación de Saldo (Ingreso/Retiro/Transferencia)");
                        System.out.println("");
                        System.out.println("███████████████████████████████████████████████");
                        System.out.println("----------------𝟬. Regresar--------------------");
                        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
                        System.out.print("Selecciona una opcion: ");
                        int opCuentas = sc.nextInt();
                        sc.nextLine();
                        switch (opCuentas) {
                            case 1:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                RegistroCuenta.rCuentas(sc);
                                System.out.println("\nCuenta aperturada exitosamente. Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                            case 2:
                                GeneralService.cleanScreen();
                                EliminarRegistros.DeleteCuentas(sc);
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                System.out.println("\nOperación de eliminación de cuenta finalizada. Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                            case 3:
                                GeneralService.cleanScreen();
                                System.out.println("███████████████████████████████████████████████");
                                System.out.println("");
                                System.out.println("☰☰☰☰☰☰☰☰☰☰☰ Operaciones ☰☰☰☰☰☰☰☰☰☰☰☰");
                                System.out.println("𝟭. Ingresar Saldo a Cuenta de Cliente");
                                System.out.println("𝟮. Retirar Saldo de Cuenta de Cliente");
                                System.out.println("𝟯. Transferir Saldo entre Cuentas de Clientes");
                                System.out.println("");
                                System.out.println("███████████████████████████████████████████████");
                                System.out.println("-----------------𝟬. Regresar-------------------");
                                System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
                                System.out.print("Selecciona una opción: ");
                                int opSaldo = sc.nextInt();
                                sc.nextLine();

                                String numCuentaOrigen = null;
                                String numCuentaDestino = null;
                                BigDecimal monto = null;
                                String descripcion = "";
                                boolean operacionExitosa = false;

                                switch (opSaldo) {
                                    case 1: // ? Ingreso de Saldo
                                        GeneralService.cleanScreen();
                                        System.out.print("Ingrese el numero de cuenta del cliente a la que desea ingresar saldo: ");
                                        numCuentaDestino = sc.nextLine();
                                        System.out.print("Ingrese el monto a ingresar: ");
                                        monto = sc.nextBigDecimal();
                                        sc.nextLine();
                                        System.out.print("Ingrese una descripción (opcional): ");
                                        descripcion = sc.nextLine();
                                        operacionExitosa = ServicioTransacciones.realizarOperacion("INGRESO", monto, null, numCuentaDestino, descripcion);
                                        System.out.println(operacionExitosa ? "Ingreso realizado exitosamente." : "Fallo al realizar el ingreso.");
                                        break;
                                    case 2: // ? Retiro de Saldo
                                        GeneralService.cleanScreen();
                                        System.out.print("Ingrese el numero de cuenta del cliente de la que desea retirar saldo: ");
                                        numCuentaOrigen = sc.nextLine();
                                        System.out.print("Ingrese el monto a retirar: ");
                                        monto = sc.nextBigDecimal();
                                        sc.nextLine();
                                        System.out.print("Ingrese una descripción (opcional): ");
                                        descripcion = sc.nextLine();
                                        operacionExitosa = ServicioTransacciones.realizarOperacion("RETIRO", monto, numCuentaOrigen, null, descripcion);
                                        System.out.println(operacionExitosa ? "Retiro realizado exitosamente." : "Fallo al realizar el retiro.");
                                        break;
                                    case 3: // ? Transferencia
                                        GeneralService.cleanScreen();
                                        System.out.print("Ingrese el numero de cuenta origen: ");
                                        numCuentaOrigen = sc.nextLine();
                                        System.out.print("Ingrese el numero de cuenta destino: ");
                                        numCuentaDestino = sc.nextLine();
                                        System.out.print("Ingrese el monto a transferir: ");
                                        monto = sc.nextBigDecimal();
                                        sc.nextLine();
                                        System.out.print("Ingrese una descripción (opcional): ");
                                        descripcion = sc.nextLine();
                                        operacionExitosa = ServicioTransacciones.realizarOperacion("TRANSFERENCIA", monto, numCuentaOrigen, numCuentaDestino, descripcion);
                                        System.out.println(operacionExitosa ? "Transferencia realizada exitosamente." : "Fallo al realizar la transferencia.");
                                        break;
                                    case 0:
                                        GeneralService.cleanScreen();
                                        break;
                                    default:
                                        System.out.println("Opción inválida.");
                                        break;
                                }
                                System.out.println("\nPresione Enter para continuar...");
                                sc.nextLine();
                                break;
                            case 0:
                                GeneralService.cleanScreen();
                                break;
                            default:
                                System.out.println("Selecciona una opcion valida.");
                                System.out.print("Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                        }
                        break;
                    case 4:
                        GeneralService.cleanScreen();
                        System.out.println("███████████████████████████████████████████████");
                        System.out.println("");
                        System.out.println("☰☰☰☰☰☰☰☰☰ Menu de Inversiones ☰☰☰☰☰☰☰☰☰");
                        System.out.println("𝟭. Aperturar una inversion");
                        System.out.println("𝟮. Eliminar una inversion");
                        System.out.println("");
                        System.out.println("███████████████████████████████████████████████");
                        System.out.println("-------------------𝟬. Regresar-----------------");
                        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
                        System.out.print("Selecciona una opcion: ");
                        int opInversiones = sc.nextInt();
                        sc.nextLine();
                        switch (opInversiones) {
                            case 1:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                RegistroInversiones.rInversiones(sc);
                                System.out.println("\nInversión registrada exitosamente. Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                            case 2:
                                GeneralService.cleanScreen();
                                EliminarRegistros.DeleteInversiones(sc);
                                System.out.println("\nOperación de eliminación de inversión finalizada. Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                            case 0:
                                GeneralService.cleanScreen();
                                break;
                            default:
                                System.out.println("Selecciona una opcion valida.");
                                System.out.print("Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                        }
                        break;
                    case 5:
                        GeneralService.cleanScreen();
                        System.out.println("███████████████████████████████████████████████");
                        System.out.println("");
                        System.out.println("☰☰☰☰☰☰☰☰☰☰ Menu de Tarjetas ☰☰☰☰☰☰☰☰☰☰");
                        System.out.println("𝟭. Registrar una tarjeta");
                        System.out.println("𝟮. Dar de baja una tarjeta");
                        System.out.println("𝟯. Renovar Tarjeta");
                        System.out.println("");
                        System.out.println("███████████████████████████████████████████████");
                        System.out.println("-------------------𝟬. Regresar-----------------");
                        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
                        System.out.print("Selecciona una opcion: ");
                        int opTarjetas = sc.nextInt();
                        sc.nextLine();
                        switch (opTarjetas) {
                            case 1:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                RegistroTarjetas.rTarjetas(sc);
                                System.out.println("\nTarjeta registrada exitosamente. Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                            case 2:
                                GeneralService.cleanScreen();
                                EliminarRegistros.DeleteTarjetas(sc);
                                System.out.println("\nOperación de baja de tarjeta finalizada. Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                            case 3:
                                GeneralService.cleanScreen();
                                UpdateRegistros.uTarjetas(sc);
                                System.out.println("\nOperación de renovación de tarjeta finalizada. Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                            case 0:
                                GeneralService.cleanScreen();
                                break;
                            default:
                                System.out.println("Selecciona una opcion valida.");
                                System.out.print("Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                        }
                        break;
                    case 6:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        ConsultasProductos.consultaProd(sc);
                        System.out.print("Presione Enter para continuar...");
                        sc.nextLine();
                        break;
                    case 7:
                        GeneralService.cleanScreen();
                        System.out.println("███████████████████████████████████████████████");
                        System.out.println("");
                        System.out.println("☰☰☰☰☰☰☰☰☰☰ Menu Prestamos ☰☰☰☰☰☰☰☰☰☰☰");
                        System.out.println("𝟭. Registrar un prestamo");
                        System.out.println("𝟮. Eliminar un prestamo");
                        System.out.println("");
                        System.out.println("███████████████████████████████████████████████");
                        System.out.println("-----------------𝟬. Regresar-------------------");
                        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
                        System.out.print("Selecciona una opcion: ");
                        int opPresta = sc.nextInt();
                        sc.nextLine();
                        switch (opPresta) {
                            case 1:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                RegistroPrestamos.rPrestamos(sc);
                                System.out.println("\nPréstamo registrado exitosamente. Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                            case 2:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                EliminarRegistros.DeletePrestamos(sc);
                                System.out.println("\nOperación de eliminación de préstamo finalizada. Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                            case 0:
                                GeneralService.cleanScreen();
                                break;
                            default:
                                System.out.println("Selecciona una opcion valida");
                                System.out.println("\nPresione Enter para continuar...");
                                sc.nextLine();
                                break;
                        }
                        break;
                    default:
                        System.out.println("Selecciona una opcion valida.");
                        System.out.print("Presione Enter para continuar...");
                        sc.nextLine();
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                System.err.println("Entrada inválida. Por favor, ingrese un número.");
                sc.nextLine();
                System.out.println("\nPresione Enter para continuar...");
                sc.nextLine();
            } catch (Exception e) {
                System.err.println("Error inesperado en el menú: " + e.getMessage());
                e.printStackTrace();
                System.out.println("\nPresione Enter para continuar...");
                sc.nextLine();
            }
        }
    }

    private static void verInfoEmplado(int empladoId) {
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

    private static void verInfoCliente(int clienteId) {
        System.out.println("☰☰☰☰☰☰ Información del Cliente ☰☰☰☰☰☰");
        Connection connection = null;
        try {
            connection = Con.getConn();

            Map<String, Object> detalles = BusquedaCliente.consultaCliente(connection, clienteId);

            if (detalles != null) {
                System.out.println("");
                System.out.println("ID Cliente: " + detalles.get("cliente_id"));
                System.out.println("Nombre completo: " + detalles.get("nombre_completo"));
                System.out.println("Fecha Nacimiento: " + detalles.get("fecha_nacimiento"));
                System.out.println("Teléfono: " + detalles.get("telefono"));
                System.out.println("Dirección: " + detalles.get("direccion"));
                System.out.println("Email: " + detalles.get("email"));
                System.out.println("Ocupación: " + detalles.get("ocupacion"));
                System.out.println("RFC: " + detalles.get("rfc"));
                System.out.println("CURP: " + detalles.get("curp"));

                System.out.println("");
                System.out.println("--- Información Financiera ---");
                System.out.println("Número de Cuenta: " + detalles.get("numero_cuenta"));
                System.out.println("Saldo de Cuenta: $" + detalles.get("saldo"));
                System.out.println("Número de Tarjeta: " + detalles.get("num_tarjeta"));
                System.out.println("Saldo de Tarjeta: $" + detalles.get("saldo_actual"));
                System.out.println("Monto de Préstamo: $" + detalles.get("monto"));
                System.out.println("Tasa de Interés Préstamo: " + detalles.get("tasa_interes") + "%");
                System.out.println("Tipo de Inversión: " + detalles.get("tipo_inversion"));
                System.out.println("Monto de Inversión: $" + detalles.get("monto_actual"));

            } else {
                System.out.println("No se pudo obtener la información del cliente. Intente nuevamente más tarde.");
            }
        } catch (Exception e) {
            System.err.println("Error al consultar información del cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Con.closeConnetion(connection);
        }

        System.out.println("");
        System.out.println("Presione Enter para volver al menú principal...");
        sc.nextLine();
    }
}