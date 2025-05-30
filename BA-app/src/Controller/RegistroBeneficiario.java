package Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Config.Con;
import Model.Beneficiarios;
import Model.Cliente;
import Repository.InsertBeneficiario;

public class RegistroBeneficiario {
    public static ArrayList<Beneficiarios> rBeneficiarios(Scanner sc){
        ArrayList<Beneficiarios> registroBeneficiarios = new ArrayList<>();
        ArrayList<Cliente> busquedaCliente = new ArrayList<>();
        Connection connection = null;

        try {
            connection = Con.getConn();
            Beneficiarios beneficiarios = new Beneficiarios();
            Cliente cliente = new Cliente();
            registroBeneficiarios.add(beneficiarios);
            busquedaCliente.add(cliente);

            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.print("¿Cual es el CURP del cliente a quien pertenecerá este beneficiario?: ");
            String curp = sc.nextLine();
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            cliente.setCurp(curp);

            System.out.print("Ingresa el nombre/nombres: ");
            String nombre = sc.nextLine();
            beneficiarios.setNombre_beneficiario(nombre);
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");

            System.out.print("Ingresa el apellido paterno: ");
            String apPaterno = sc.nextLine();
            beneficiarios.setApellido_paterno(apPaterno);
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");

            System.out.print("Ingresa el apellido materno: ");
            String apMaterno = sc.nextLine();
            beneficiarios.setApellido_materno(apMaterno);
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");

            System.out.print("¿Que parentesco tienes con el beneficiario?: ");
            String parentesco = sc.nextLine();
            beneficiarios.setParentesco(parentesco);
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");

            System.out.print("Ingresa el telefono del beneficiario: ");
            String telefono = sc.nextLine();
            beneficiarios.setTelefono(telefono);
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");

            System.out.print("Ingresa el correo del beneficiario: ");
            String email = sc.nextLine();
            beneficiarios.setEmail(email);

            InsertBeneficiario.iBeneficiarios(connection, beneficiarios, cliente);
            System.out.println("Beneficiario registrado");
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
        return registroBeneficiarios;
    }
}
