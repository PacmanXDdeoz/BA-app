package Controller;

import Config.Con;
import Model.Cliente;
import Repository.InsertCliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class RegistroCliente {
    public RegistroCliente() {
    }

    public static ArrayList<Cliente> rClientes() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Cliente> registroClientes = new ArrayList<>();
        Connection connection = null;

        try {
            connection = Con.getConn();
            Cliente cliente = new Cliente();
            registroClientes.add(cliente);

            System.out.print("Ingresa tu nombre/nombres: ");
            String nombre = sc.nextLine();
            cliente.setNombre_cliente(nombre);
            
            System.out.print("Ingresa tu apellido paterno: ");
            String apPaterno = sc.nextLine();
            cliente.setApellido_paterno(apPaterno);

            System.out.print("Ingresa tu apellido materno: ");
            String apMaterno = sc.nextLine();
            cliente.setApellido_materno(apMaterno);

            System.out.println("Ingresa tu fecha de nacimiento");
            System.out.print("Ingresa el dia: ");
            int dia = sc.nextInt();
            System.out.print("Ingresa el mes: ");
            int mes = sc.nextInt();
            System.out.print("Ingresa el año: ");
            int año = sc.nextInt();
            sc.nextLine();
            LocalDate fechaNacimiento = null;
            if (dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12 && año < 2007) {
                fechaNacimiento = LocalDate.of(año, mes, dia);
                cliente.setFecha_nacimiento(fechaNacimiento);
            } else {
                if (año >= 2007) {
                    System.out.println("Debes ser mayor de edad.");
                } else {
                    System.out.println("Da una fecha válida!");
                }
                return registroClientes;
            }

            System.out.print("Ingresa tu telefono: ");
            String telefono = sc.nextLine();
            cliente.setTelefono(telefono);

            System.out.print("Ingresa tu calle y numero: ");
            String calle = sc.nextLine();
            System.out.print("Ingresa tu colonia: ");
            String colonia = sc.nextLine();
            System.out.print("Ingresa tu municipio: ");
            String municipio = sc.nextLine();
            System.out.print("Ingresa tu Codigo Postal: ");
            String cp = sc.nextLine();
            String direccion = calle + " " + colonia + " " + municipio + " " + cp;
            cliente.setDireccion(direccion);

            System.out.print("Ingresa tu correo: ");
            String correo = sc.nextLine();
            cliente.setEmail(correo);

            System.out.print("¿Cual es tu ocupacion?: ");
            String ocupacion = sc.nextLine();
            cliente.setOcupacion(ocupacion);

            System.out.print("Ingresa tu RFC: ");
            String rfc = sc.nextLine();
            cliente.setRfc(rfc);

            System.out.print("Ingresa tu CURP: ");
            String curp = sc.nextLine();
            cliente.setCurp(curp);

            System.out.print("Ingresa tu contraseña: ");
            String password = sc.nextLine();
            cliente.setPassword(password);

            InsertCliente.iCliente(connection, cliente);
            System.out.println("Cliente registrado");
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
        return registroClientes;
    }
}
