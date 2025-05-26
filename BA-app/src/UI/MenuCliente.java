package UI;

import java.util.Scanner;
import java.util.Map;
import java.math.BigDecimal;
import java.sql.Connection;

import Config.Con;
import Controller.RegistroBeneficiario;
import Model.Cliente;
import Service.GeneralService;
import Service.ServicioTransacciones;
import Repository.ConsultaTarjeta;
import Repository.ConsultaTransacciones;
import Repository.ConsultaBeneficiarios;
import Repository.ConsultaCliente;
import Repository.ConsultaCuenta;
import Repository.ConsultaInversiones;
import Repository.ConsultaPrestamos;
import Repository.ConsultasProductos;

public class MenuCliente {

    private static final Scanner sc = new Scanner(System.in);

    public static void menuPrincipal(Cliente clienteActual) {

        while (true) {
            try {
                GeneralService.cleanScreen();
                System.out.println("████████████████████████████████████████████████████");
                System.out.println("🏦 Bienvenido a Banco Azteca " + clienteActual.getNombre_cliente() + "! 🏦");
                System.out.println("------------------Menu principal--------------------");
                System.out.println("");
                System.out.println("𝟭. Tus datos ░░░░░░░░░░░░░░░░░░░░░░░░░░░░ Cuentas .𝟮");
                System.out.println("𝟯. Inversiones ░░░░░░░░░░░░░░░░░░░░░░░░ Prestamos .𝟰");
                System.out.println("𝟱. Productos disponibles ░░░░░░░░░░ Beneficiarios .𝟲");
                System.out.println("");
                System.out.println("░░░░░░░░░░░░░░░░░ 𝟬. Cerrar sesion ░░░░░░░░░░░░░░░░░");
                System.out.println("");
                System.out.println("████████████████████████████████████████████████████");
                System.out.print("Selecciona una opcion: ");
                int opt = sc.nextInt();
                sc.nextLine();

                switch (opt) {
                    case 0:
                        GeneralService.cleanScreen();
                        System.out.println("Gracias por usar Banco Azteca! 🏦");
                        MenuLogin.menuBienvenida();
                        return;
                    case 1:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        verInfoCliente(clienteActual.getCliente_id());
                        break;
                    case 2:
                        GeneralService.cleanScreen();
                        System.out.println("██████████████████████████████");
                        System.out.println("☰☰☰☰☰☰Menu Cuentas☰☰☰☰☰☰");
                        System.out.println("");
                        System.out.println("𝟭. Tus cuentas");
                        System.out.println("𝟮. Tarjetas");
                        System.out.println("𝟯. Historial de transacciones");
                        System.out.println("𝟰. Realizar una transaccion");
                        System.out.println("");
                        System.out.println("██████████████████████████████");
                        System.out.println("---------𝟬. Regresar----------");
                        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
                        System.out.print("Selecciona una opcion: ");
                        int op = sc.nextInt();
                        sc.nextLine(); // Consumir el salto de línea

                        switch (op) {
                            case 1:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                verInfoCuenta(clienteActual.getCliente_id());
                                break;
                            case 2:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                verInfoTarjeta(clienteActual.getCliente_id());
                                break;
                            case 3:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                verInfoTransacciones(clienteActual.getCliente_id());
                                System.out.println("\nPresione Enter para continuar...");
                                sc.nextLine();
                                break;
                            case 4:
                                GeneralService.cleanScreen();
                                System.out.println("██████████████████████████████████████");
                                System.out.println("☰☰☰☰☰☰ Menu Transacciones ☰☰☰☰☰☰");
                                System.out.println("");
                                System.out.println("𝟭. Agregar Saldo");
                                System.out.println("𝟮. Retirar Saldo");
                                System.out.println("𝟯. Generar una transferencia");
                                System.out.println("");
                                System.out.println("██████████████████████████████████████");
                                System.out.println("-------------𝟬. Regresar--------------");
                                System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
                                System.out.print("Selecciona una opcion: ");
                                int opSaldo = sc.nextInt();
                                sc.nextLine();

                                String numCuentaOrigen = null;
                                String numCuentaDestino = null;
                                BigDecimal monto = null;
                                String descripcion = "";
                                boolean operacionExitosa = false;

                                switch (opSaldo) {
                                    case 1:// ? Deposito
                                        GeneralService.cleanScreen();
                                        System.out.print(
                                                "Ingrese tu NUMERO de cuenta del cliente a la que desea ingresar saldo: ");
                                        numCuentaDestino = sc.nextLine();
                                        System.out.print("Ingrese el monto a ingresar: ");
                                        monto = sc.nextBigDecimal();
                                        sc.nextLine();
                                        System.out.print("Ingrese una descripción (opcional): ");
                                        descripcion = sc.nextLine();
                                        operacionExitosa = ServicioTransacciones.realizarOperacion("INGRESO", monto,
                                                null, numCuentaDestino, descripcion);
                                        System.out.println(operacionExitosa ? "Ingreso realizado exitosamente."
                                                : "Fallo al realizar el ingreso.");
                                        System.out.println("Oprime enter para regresar");
                                        sc.nextLine();
                                        break;
                                    case 2: // ? Retiro
                                        GeneralService.cleanScreen();
                                        System.out.print(
                                                "Ingrese el NUMERO de cuenta del cliente de la que desea retirar saldo: ");
                                        numCuentaOrigen = sc.nextLine();
                                        System.out.print("Ingrese el monto a retirar: ");
                                        monto = sc.nextBigDecimal();
                                        sc.nextLine();
                                        System.out.print("Ingrese una descripción (opcional): ");
                                        descripcion = sc.nextLine();
                                        operacionExitosa = ServicioTransacciones.realizarOperacion("RETIRO", monto,
                                                numCuentaOrigen, null, descripcion);
                                        System.out.println(operacionExitosa ? "Retiro realizado exitosamente."
                                                : "Fallo al realizar el retiro.");
                                        System.out.println("Oprime enter para regresar");
                                        sc.nextLine();
                                        break;
                                    case 3: // ? Transferencia
                                        GeneralService.cleanScreen();
                                        System.out.print("Ingrese el NUMERO de cuenta ORIGEN: ");
                                        numCuentaOrigen = sc.nextLine();
                                        System.out.print("Ingrese el NUMERO de cuenta DESTINO: ");
                                        numCuentaDestino = sc.nextLine();
                                        System.out.print("Ingrese el monto a transferir: ");
                                        monto = sc.nextBigDecimal();
                                        sc.nextLine();
                                        System.out.print("Ingrese una descripción (opcional): ");
                                        descripcion = sc.nextLine();
                                        operacionExitosa = ServicioTransacciones.realizarOperacion("TRANSFERENCIA",
                                                monto, numCuentaOrigen, numCuentaDestino, descripcion);
                                        System.out.println(operacionExitosa ? "Transferencia realizada exitosamente."
                                                : "Fallo al realizar la transferencia.");
                                        System.out.println("Oprime enter para regresar");
                                        sc.nextLine();
                                        break;
                                    case 0:
                                        GeneralService.cleanScreen();
                                        break;
                                    default:
                                        System.out.println("Opción inválida.");
                                        System.out.println("\nPresione Enter para continuar...");
                                        sc.nextLine();
                                        break;
                                }
                                break;
                            case 0:
                                GeneralService.cleanScreen();
                                break;
                            default:
                                GeneralService.cleanScreen();
                                System.out.println("Selecciona una opción válida.");
                                System.out.println("\nPresione Enter para continuar...");
                                sc.nextLine();
                                break;
                        }
                        break;
                    case 3:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        verInfoInversiones(clienteActual.getCliente_id());
                        break;
                    case 4:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        verInfoPrestamos(clienteActual.getCliente_id());
                        break;
                    case 5:
                        GeneralService.cleanScreen();
                        GeneralService.showLoading();
                        GeneralService.cleanScreen();
                        ConsultasProductos.consultaProd(sc);
                        System.out.println("\nPresione Enter para continuar...");
                        sc.nextLine();
                        break;
                    case 6:
                        GeneralService.cleanScreen();
                        System.out.println("██████████████████████████████████████");
                        System.out.println("");
                        System.out.println("☰☰☰☰☰☰ Menu Beneficiarios ☰☰☰☰☰☰");
                        System.out.println("𝟭. Consultar tus beneficiarios");
                        System.out.println("𝟮. Registrar un beneficiario nuevo");
                        System.out.println("");
                        System.out.println("██████████████████████████████████████");
                        System.out.println("-------------𝟬. Regresar--------------");
                        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
                        System.out.print("Selecciona una opcion: ");
                        op = sc.nextInt();
                        sc.nextLine();
                        switch (op) {
                            case 1:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                verInfoBeneficiario(clienteActual.getCliente_id());
                                break;
                            case 2:
                                GeneralService.cleanScreen();
                                GeneralService.showLoading();
                                GeneralService.cleanScreen();
                                RegistroBeneficiario.rBeneficiarios(sc);
                                System.out.println(
                                        "\nBeneficiario registrado exitosamente. Presione Enter para continuar...");
                                sc.nextLine();
                                break;
                            case 0:
                                GeneralService.cleanScreen();
                                break;
                            default:
                                System.out.println("Selecciona una opción válida.");
                                System.out.println("\nPresione Enter para continuar...");
                                sc.nextLine();
                                break;
                        }
                        break;
                    default:
                        System.out.println("Selecciona una opción válida");
                        System.out.println("\nPresione Enter para continuar...");
                        sc.nextLine();
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                System.err.println("Entrada inválida. Por favor, ingrese un número. " + e.getMessage());
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

    private static void verInfoCliente(int clienteId) {
        System.out.println("☰☰☰ Mi información ☰☰☰");
        Connection connection = null;
        try {
            connection = Con.getConn();

            Map<String, Object> detalles = ConsultaCliente.consultaCliente(connection, clienteId);

            if (detalles != null) {
                System.out.println("");
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
                System.out.println("Información general:");
                System.out.println("ID: " + detalles.get("cliente_id"));
                System.out.println("Nombre completo: " + detalles.get("nombre_completo"));
                System.out.println("Email: " + detalles.get("email"));
                System.out.println("Teléfono: " + detalles.get("telefono"));
                System.out.println("Direccion: " + detalles.get("direccion"));
                System.out.println("RFC: " + detalles.get("rfc"));
                System.out.println("CURP: " + detalles.get("curp"));

                System.out.println("");
                System.out.println("Info de cuenta: ");
                System.out.println("Numero de cuenta: " + detalles.get("numero_cuenta"));
                System.out.println("Saldo actual:" + detalles.get("saldo"));

                System.out.println("");
                System.out.println("Info de prestamo: ");
                System.out.println("Tasa interes: " + detalles.get("tasa_interes"));
                System.out.println("Monto solicitado: " + detalles.get("monto"));

                System.out.println("");
                System.out.println("Info de Inversion: ");
                System.out.println("Tipo de inversion: " + detalles.get("tipo_inversion"));
                System.out.println("Monto actual: " + detalles.get("monto_actual"));

                System.out.println("");
                System.out.println("Info de Tarjetas: ");
                System.out.println("Numero de tarjeta: " + detalles.get("num_tarjeta"));
                System.out.println("Saldo actual: " + detalles.get("saldo_actual"));
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");

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

    private static void verInfoCuenta(int clienteId) {
        System.out.println("☰☰☰ Mi información ☰☰☰");
        Connection connection = null;
        try {
            connection = Con.getConn();

            Map<String, Object> detalles = ConsultaCuenta.consultaCuenta(connection, clienteId);

            if (detalles != null) {
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
                System.out.println("Información de tus cuentas: ");
                System.out.println("Cliente: " + detalles.get("nombre_cliente"));
                System.out.println("Numero de cuenta: " + detalles.get("numero_cuenta"));
                System.out.println("Saldo actual:" + detalles.get("saldo"));
                System.out.println("Sucursal donde se creó: " + detalles.get("nombre_sucursal"));
                System.out.println("Producto: " + detalles.get("nombre_producto"));
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");

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

    private static void verInfoTarjeta(int clienteId) {
        System.out.println("☰☰☰ Mi información ☰☰☰");
        Connection connection = null;
        try {
            connection = Con.getConn();

            Map<String, Object> detalles = ConsultaTarjeta.consultaTarjeta(connection, clienteId);

            if (detalles != null) {
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
                System.out.println("Información de tus tarjetas: ");
                System.out.println("Cliente: " + detalles.get("nombre_cliente"));
                System.out.println("Numero de tarjeta: " + detalles.get("num_tarjeta"));
                System.out.println("CVV: " + detalles.get("cvv"));
                System.out.println("Fecha de emision: " + detalles.get("fecha_emision"));
                System.out.println("Fecha de expiracion: " + detalles.get("fecha_expiracion"));
                System.out.println("Saldo actual:" + detalles.get("saldo_actual"));
                System.out.println("Estado: " + detalles.get("estado"));
                System.out.println("Tarjeta: " + detalles.get("tipo_tarjeta"));
                System.out.println("Producto: " + detalles.get("nombre_producto"));
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
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

    private static void verInfoTransacciones(int clienteId) {
        System.out.println("☰☰☰ Mi información ☰☰☰");
        Connection connection = null;
        try {
            connection = Con.getConn();
            Map<String, Object> detalles = ConsultaTransacciones.consultaTransacciones(connection, clienteId);
            if (detalles != null) {
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
                System.out.println("Información de tus transacciones: ");
                System.out.println("ID: " + detalles.get("transaccion_id"));
                System.out.println("Tipo transaccion: " + detalles.get("tipo_transaccion"));
                System.out.println("Monto: " + detalles.get("monto"));
                System.out.println("Fecha de la transaccion: " + detalles.get("fecha_transaccion"));
                System.out.println("Descripcion: " + detalles.get("descripcion"));
                System.out.println("Estado: " + detalles.get("estado"));
                System.out.println("Numero de cuenta origen: " + detalles.get("numero_cuenta_origen"));
                System.out.println("Nombre origen: " + detalles.get("nombre_cliente_origen"));
                System.out.println("Numero cuenta destino: " + detalles.get("numero_cuenta"));
                System.out.println("Nombre destino: " + detalles.get("nombre_cliente_destino"));
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
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

    private static void verInfoInversiones(int clienteId) {
        System.out.println("☰☰☰ Información de Inversiones ☰☰☰");
        Connection connection = null;
        try {
            connection = Con.getConn();

            Map<String, Object> detalles = ConsultaInversiones.consultaInversion(connection, clienteId);

            if (detalles != null) {
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
                System.out.println("Monto actual: " + detalles.get("monto_actual"));
                System.out.println("Tasa de interes: " + detalles.get("tasa_interes"));
                System.out.println("Fecha de inicio: " + detalles.get("fecha_inicio"));
                System.out.println("Fecha fin: " + detalles.get("fecha_vencimiento"));
                System.out.println("Tipo de inversion: " + detalles.get("tipo_inversion"));
                System.out.println("Producto: " + detalles.get("nombre_producto"));
                System.out.println("Cliente: " + detalles.get("nombre_cliente"));
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
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

    private static void verInfoPrestamos(int clienteId) {
        System.out.println("☰☰☰ Información de Préstamos ☰☰☰");
        Connection connection = null;
        try {
            connection = Con.getConn();

            Map<String, Object> detalles = ConsultaPrestamos.consultaPrestamos(connection, clienteId);

            if (detalles != null) {
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
                System.out.println("Monto: " + detalles.get("monto"));
                System.out.println("Tasa de interes: " + detalles.get("tasa_interes"));
                System.out.println("Plazo_interes: " + detalles.get("plazo"));
                System.out.println("Fecha inicio: " + detalles.get("fecha_inicio"));
                System.out.println("Fecha fin: " + detalles.get("fecha_fin"));
                System.out.println("Producto: " + detalles.get("nombre_producto"));
                System.out.println("Estado: " + detalles.get("estado"));
                System.out.println("Cliente: " + detalles.get("nombre_cliente"));
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
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

    private static void verInfoBeneficiario(int clienteId) {
        System.out.println("☰☰☰ Información de Beneficiarios ☰☰☰");
        Connection connection = null;
        try {
            connection = Con.getConn();

            Map<String, Object> detalles = ConsultaBeneficiarios.consultaBeneficiario(connection, clienteId);

            if (detalles != null) {
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
                System.out.println("Nombre del beneficiario: " + detalles.get("nombre_completo"));
                System.out.println("Parentesco: " + detalles.get("parentesco"));
                System.out.println("Telefono: " + detalles.get("telefono"));
                System.out.println("Correo: " + detalles.get("email"));
                System.out.println("Cliente: " + detalles.get("nombre_cliente"));
                System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
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