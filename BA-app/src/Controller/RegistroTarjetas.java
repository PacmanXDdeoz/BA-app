package Controller;

import Config.Con;
import Model.Tarjetas;
import Model.Cliente;
import Repository.InsertCuentas;
import Repository.InsertTarjetas;
import Service.GeneradorRandom;
import Service.HoraActualService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class RegistroTarjetas {

    public static ArrayList<Tarjetas> rTarjetas(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Tarjetas> registroTarjetas = new ArrayList<>();
        ArrayList<Cliente> busquedaCliente = new ArrayList<>();
        Connection connection = null;

        try {
            connection = Con.getConn();
            Tarjetas tarjeta = new Tarjetas();
            Cliente cliente = new Cliente();
            registroTarjetas.add(tarjeta);
            busquedaCliente.add(cliente);

            int producto = 0;
            int op = 0;
            do {
                System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
                System.out.println("¿Que tipo de tarjeta necesitas?");
                System.out.println("1. Credito");
                System.out.println("2. Debito");
                System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
                System.out.print("Selecciona una opcion: ");
                op = sc.nextInt();
                sc.nextLine();
                
                if (op == 1) {
                    System.out.println("1. Prestamos");
                    System.out.println("2. Creditos");
                    System.out.print("Selecciona una opcion: ");
                    producto = sc.nextInt();
                    do {
                    if (producto == 1) {
                        tarjeta.setProducto_id(producto);
                    } if (producto == 2) {
                        tarjeta.setProducto_id(producto);
                    } else {
                        System.out.println("Selecciona una opcion valida");
                    }}while(producto > 2);
                } if (op == 2) {
                    System.out.println("1. Guadadito");
                    System.out.println("2. Nomina Azteca");
                    System.out.println("3. Debito Negocio");
                    System.out.print("Selecciona una opcion: ");
                    producto = sc.nextInt();
                    do {
                    if (producto == 1) {
                        tarjeta.setProducto_id(producto);
                    } if (producto == 2) {
                        tarjeta.setProducto_id(producto);
                    } if (producto == 3) {
                        tarjeta.setProducto_id(producto);
                    } else {
                        System.out.println("Selecciona una opcion valida");
                    }}while(producto > 3);
                } else {
                    System.out.println("selecciona una opcion valida");
                }
            } while(op > 2);

            long numRandom = GeneradorRandom.generarTarjeta();
            String numTarjeta = "" + numRandom;
            tarjeta.setNum_tarjeta(numTarjeta);
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.println("Tu numero de tarjeta será: " + numTarjeta + "💳");
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.print("Oprime enter para continuar");
            sc.nextLine();

            LocalDate exp = HoraActualService.FechaExpiracion();
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.println("Tu tarjeta expirará el: " + exp);
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.print("Oprime enter para continuar");
            sc.nextLine();
            tarjeta.setFecha_emision(exp);

            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.print("¿Cuanto dinero deseas ingresar a la tarjeta?: ");
            double saldo = sc.nextDouble();
            sc.nextLine();
            tarjeta.setSaldo_actual(saldo);

            int estado = 6;
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.println("Actualmente tu cuenta se encuentra en proceso ⚠️");
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.print("Oprime enter para continuar");
            sc.nextLine();
            tarjeta.setEstado_id(estado);

            LocalDate hoy = HoraActualService.FechaActual();
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.println("Tu cuenta se está registrando el día de hoy: " + hoy);
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.print("Oprime enter para continuar");
            sc.nextLine();
            tarjeta.setFecha_emision(hoy);

            int numRandomCvv = GeneradorRandom.generarCvv();
            String numCvv = "" + numRandomCvv;
            tarjeta.setCvv(numCvv);
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.println("Tu numero de CVV será: " + numCvv + "💳");
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.print("Oprime enter para continuar");
            sc.nextLine();
            
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.print("¿Cual es la curp del cliente a quien pertenecerá esta cuenta?: ");
            String curp = sc.nextLine();
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            cliente.setCurp(curp);

            InsertTarjetas.iTarjeta(connection, tarjeta, cliente);
            System.out.println("Tarjeta registrada");
            System.out.println("Dale enter para iniciar sesion");
            sc.nextLine();
        } catch (SQLException e) {
            System.out.println("Error al insertar en la base de datos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error al registrar: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Con.closeConnetion(connection);
            sc.close();
        }
        return registroTarjetas;
    }
}
