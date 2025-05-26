package Controller;

import Config.Con;
import Model.Cliente;
import Model.Cuentas;
import Model.Inversiones;
import Model.Prestamos;
import Model.Tarjetas;
import Repository.EliminarCuenta;
import Repository.EliminarInversion;
import Repository.EliminarPrestamo;
import Repository.EliminarTarjeta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class EliminarRegistros {
    
    public static ArrayList<Inversiones> DeleteInversiones(Scanner sc){
        ArrayList<Inversiones> deleteInversiones = new ArrayList<>();
        ArrayList<Cliente> busquedaCliente = new ArrayList<>();
        Connection connection = null;

        try {
            connection = Con.getConn();
            Inversiones inversion = new Inversiones();
            Cliente cliente = new Cliente();
            deleteInversiones.add(inversion);
            busquedaCliente.add(cliente);

            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.print("¿Cual es la curp del cliente a quien se eliminará la inversion?: ");
            String curp = sc.nextLine();
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            cliente.setCurp(curp);
            
            EliminarInversion.eInversion(connection, cliente, inversion);
            System.out.println("Inversion eliminada");
            System.out.println("Dale enter para continuar");
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
        return deleteInversiones;
    }

    public static ArrayList<Cuentas> DeleteCuentas(Scanner sc){
        ArrayList<Cuentas> deleteCuentas = new ArrayList<>();
        ArrayList<Cliente> busquedaCliente = new ArrayList<>();
        Connection connection = null;

        try {
            connection = Con.getConn();
            Cuentas cuentas = new Cuentas();
            Cliente cliente = new Cliente();
            deleteCuentas.add(cuentas);
            busquedaCliente.add(cliente);

            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.print("¿Cual es la curp del cliente a quien se le eliminará la cuenta?: ");
            String curp = sc.nextLine();
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            cliente.setCurp(curp);
            
            EliminarCuenta.eCuenta(connection, cliente, cuentas);
            System.out.println("Cuenta eliminada");
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
        return deleteCuentas;
    }

    public static ArrayList<Tarjetas> DeleteTarjetas(Scanner sc){
        ArrayList<Tarjetas> deleteTarjetas = new ArrayList<>();
        ArrayList<Cliente> busquedaCliente = new ArrayList<>();
        Connection connection = null;

        try {
            connection = Con.getConn();
            Tarjetas tarjetas = new Tarjetas();
            Cliente cliente = new Cliente();
            deleteTarjetas.add(tarjetas);
            busquedaCliente.add(cliente);

            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.print("¿Cual es la curp del cliente a quien se le eliminará la cuenta?: ");
            String curp = sc.nextLine();
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            cliente.setCurp(curp);
            
            EliminarTarjeta.eTarjeta(connection, cliente, tarjetas);
            System.out.println("Tarjeta eliminada");
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
        return deleteTarjetas;
    }

    public static ArrayList<Prestamos> DeletePrestamos(Scanner sc){
        ArrayList<Prestamos> deletePrestamos = new ArrayList<>();
        ArrayList<Cliente> busquedaCliente = new ArrayList<>();
        Connection connection = null;

        try {
            connection = Con.getConn();
            Prestamos prestamos = new Prestamos();
            Cliente cliente = new Cliente();
            deletePrestamos.add(prestamos);
            busquedaCliente.add(cliente);

            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.print("¿Cual es el rfc del cliente a quien se le eliminará el prestamo?: ");
            String rfc = sc.nextLine();
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            cliente.setCurp(rfc);
            
            EliminarPrestamo.ePrestamo(connection, cliente, prestamos);
            System.out.println("Prestamo eliminada");
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
        return deletePrestamos;
    }
}