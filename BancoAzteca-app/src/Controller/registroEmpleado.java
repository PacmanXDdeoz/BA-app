package Controller;
import Model.Empleados;
import Repository.InsertEmpleados;
import Config.Con;
import Service.PassEmpleadoService;

import java.util.Scanner;
import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.Connection;

public class registroEmpleado {
    public static ArrayList<Empleados> rEmpleado(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Empleados> registroEmpleados = new ArrayList<>();

        Connection connection = null;

            try {
                connection = Con.getConn();
                Empleados empleados = new Empleados();
                registroEmpleados.add(empleados);
                int id = registroEmpleados.size()+1;
                registroEmpleados.get(registroEmpleados.size()-1).setEmpleados_id(id);

                System.out.println("¿A que sucursal perteneces?");
                System.out.println("1. Mega del moral");
                System.out.println("2. Ekt Leyes de reforma");
                System.out.println("3. Mega agricola oriental");
                System.out.println("4. Ekt mercado pantitlan");
                System.out.println("5. Mega Raul Romero");
                int sucursal = sc.nextInt();
                sc.nextLine();
                empleados.setSucursal_id(sucursal);

                System.out.println("¿En que departamento te encuentras?");
                System.out.println("1. Asesoria financiera");
                System.out.println("2. Creditos");
                System.out.println("3. Seguros");
                System.out.println("4. Servicio al cliente");
                System.out.println("5. TPremia");
                System.out.println("6. Area administrativa");
                System.out.println("7. Cajeros Automaticos");
                int departamento = sc.nextInt();
                sc.nextLine();
                empleados.setDepartamento_id(departamento);

                System.out.println("Dame tu nombre/nombres");
                String nombre = sc.nextLine();
                empleados.setNombre_empleado(nombre);

                System.out.println("Dame tu apellido paterno");
                String apPaterno = sc.nextLine();
                empleados.setApellido_paterno(apPaterno);

                System.out.println("Dame tu apellido materno");
                String apMaterno = sc.nextLine();
                empleados.setApellido_materno(apMaterno);

                System.out.println("¿En que puesto te contraron?");
                String puesto = sc.nextLine();
                empleados.setPuesto(puesto);

                System.out.println("¿Cual será el salario otorgado?");
                Double salario = sc.nextDouble();
                empleados.setSalario(salario);
                sc.nextLine();

                System.out.println("Ingresa tu telefono");
                String telefono = sc.nextLine();
                empleados.setTelefono_empleado(telefono);

                System.out.println("Ingresa tu correo");
                String correo = sc.nextLine();
                empleados.setEmail_empleado(correo);

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
                            empleados.setPassword_empleado(hashedPass);
                            passVal = true;
                        } else {
                            System.out.println("Las contraseñas no coinciden. Intenta de nuevo");
                        }
                    } else {
                        System.out.println("La contraseña no cumple con los requisitos. Intenta de nuevo");
                    }
                    
                }

                InsertEmpleados.insertEmpleados(connection, empleados);
                System.out.println("Empleado registrado");
                
            } catch(SQLException e) {
                System.out.println("Error al insertar en la base de datos: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error al registrar: " + e.getMessage());
                e.printStackTrace();
            } finally {
                Con.closeConnetion(connection);
                sc.close();
            }
            return registroEmpleados;
        }
    }
