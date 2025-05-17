package Controller;

import Model.Cliente;
import Config.Con;
import Repository.InsertCliente;
import Service.PassEmpleadoService;

import java.util.Scanner;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;


public class registroCliente {

    public static ArrayList<Cliente> rCliente(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Cliente> regClientes = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Con.getConn();
            Cliente cliente = new Cliente();

            int id = regClientes.size()+1;
            regClientes.get(regClientes.size()-1).setCliente_id(id);

            System.out.println("Ingresa tu nombre / nombres: ");
            String nombre = sc.nextLine();
            cliente.setNombre_cliente(nombre);

            System.out.println("Ingresa tu apellido paterno: ");
            String apPaterno = sc.nextLine();
            cliente.setApellido_paterno(apPaterno);

            System.out.println("Ingresa tu apellido materno: ");
            String apMaterno = sc.nextLine();
            cliente.setApellido_materno(apMaterno);

            boolean fechaValida = false;
            while (!fechaValida) { // ? Valida la contraseña que tenga el formato correcto
                System.out.println("Ingresa tu fecha de nacimiento (formato: DD/MM/AAAA)");
                String fechaVal = sc.nextLine();
                
                try {
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/mm/yyyy");
                    formatoFecha.setLenient(false);
                    java.util.Date utilDate = formatoFecha.parse(fechaVal);
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                    java.util.Date hoy = new java.util.Date();
                    if (utilDate.after(hoy)){ // ? utilizando el objeto de la biblioteca utilDate despues de "hoy"
                        System.out.println("Error: pon una fecha valida, intenta de nuevo");
                        continue;
                    }

                    cliente.setFecha_nacimiento(sqlDate);
                    fechaValida = true;
                } catch (ParseException e) { // ? parse 
                    System.out.println("Error: Formato incorrecto, usa el formato DD/MM/AAAA");
                }
            }

            System.out.println("Ingresa tu telefono: ");
            String telefono = sc.nextLine();
            cliente.setTelefono(telefono);

            System.out.println("Ingresa tu direccion: (Calle, numero, colonia, delegacion y codigo postal)");
            String direccion = sc.nextLine();
            cliente.setDireccion(direccion);

            System.out.println("Ingresa tu correo: ");
            String correo = sc.nextLine();
            cliente.setEmail(correo);

            boolean passVal = false;
                while (passVal) {
                    System.out.println("Ingresa tu contraseña: ");
                    String password_empleado = sc.nextLine();

                    if (PassEmpleadoService.validarPass(password_empleado)) {
                        System.out.println("Confirma tu contraseña: ");
                        String confirmarPass = sc.nextLine();

                        if (password_empleado.equals(confirmarPass)) {
                            // ? Hashear la contraseña antes de guardarla
                            String hashedPass = PassEmpleadoService.hashPass(password_empleado);
                            cliente.setPassword(hashedPass);
                            passVal = true;
                        } else {
                            System.out.println("Las contraseñas no coinciden. Intenta de nuevo");
                        }
                    } else {
                        System.out.println("La contraseña no cumple con los requisitos. Intenta de nuevo");
                    }
                    
                }

            System.out.println("¿Cual es tu ocupacion?: ");
            String ocupacion = sc.nextLine();
            cliente.setOcupacion(ocupacion);

            System.out.println("Ingresa tu RFC: ");
            String rfc = sc.nextLine();
            cliente.setRfc(rfc);

            System.out.println("Ingresar tu CURP: ");
            String curp = sc.nextLine();
            cliente.setCurp(curp);

            InsertCliente.iCliente(connection, cliente);
            System.out.println("Cliente registrado: ");

        } catch (SQLException e) {
            System.out.println("Error al insertar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error al registrar empleado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Con.closeConnetion(connection);
            sc.close();
        }
        return regClientes;
    }
}
