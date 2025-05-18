package Controller;
import Model.Empleados;
import Repository.InsertEmpleados;
import Config.Con;

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

                int sucursal = 0;
                do {
                System.out.println("¿A que sucursal perteneces?");
                System.out.println("1. Mega del moral");
                System.out.println("2. Ekt Leyes de reforma");
                System.out.println("3. Mega agricola oriental");
                System.out.println("4. Ekt mercado pantitlan");
                System.out.println("5. Mega Raul Romero");
                sucursal = sc.nextInt();
                sc.nextLine();
                if (sucursal >= 1 && sucursal <= 5) {
                    empleados.setSucursal_id(sucursal);
                    break;
                } else {
                    System.out.println("selecciona una opcion valida");
                    continue;
                }} while (sucursal >= 5);

                int departamento = 0;
                do {
                System.out.println("¿En que departamento te encuentras?");
                System.out.println("1. Asesoria financiera");
                System.out.println("2. Creditos");
                System.out.println("3. Seguros");
                System.out.println("4. Servicio al cliente");
                System.out.println("5. TPremia");
                System.out.println("6. Area administrativa");
                System.out.println("7. Cajeros Automaticos");
                departamento = sc.nextInt();
                sc.nextLine();
                if(departamento >= 1 && departamento <= 7){
                    empleados.setDepartamento_id(departamento);
                    break;
                } else {
                    System.out.println("Selecciona una opcion valida");
                    continue;
                }
                } while (departamento > 7);

                System.out.println("Ingresa tu nombre/nombres");
                String nombre = sc.nextLine();
                empleados.setNombre_empleado(nombre);

                System.out.println("Ingresa tu apellido paterno");
                String apPaterno = sc.nextLine();
                empleados.setApellido_paterno(apPaterno);

                System.out.println("Ingresa tu apellido materno");
                String apMaterno = sc.nextLine();
                empleados.setApellido_materno(apMaterno);

                System.out.println("Ingresa tu telefono");
                String telefono = sc.nextLine();
                empleados.setTelefono_empleado(telefono);

                System.out.println("Ingresa tu correo");
                String correo = sc.nextLine();
                empleados.setEmail_empleado(correo);

                System.out.println("Ingresa tu contraseña: ");
                String password_empleado = sc.nextLine();
                empleados.setPassword_empleado(password_empleado);

                int puesto = 0;
                do{
                System.out.println("¿En que puesto te contrataron?");
                System.out.println(" 1. Ejecutivo");
                System.out.println(" 2. Asesor");
                System.out.println(" 3. Atencion a clientes");
                System.out.println(" 4. Gerente");
                puesto = sc.nextInt();
                sc.nextLine();
                if(puesto >= 1 && puesto <= 4){
                    empleados.setPuesto(puesto);
                    break;
                } else {
                    System.out.println("Selecciona una opcion valida");
                }} while (puesto > 4);

                if(puesto == 1) {
                    empleados.setSalario(8000);
                    System.out.println("Tu salario será de $8000 pesos 💸");
                } if (puesto == 2) {
                    empleados.setSalario(7500);
                    System.out.println("Tu salario será de $7500 pesos 💸");
                } if (puesto == 3){
                    empleados.setSalario(7000);
                    System.out.println("Tu salario será de $7000 pesos 💸");
                } if (puesto == 4){
                    empleados.setSalario(12000);
                    System.out.println("Tu salario será de $12000 pesos 💸");
                }

                InsertEmpleados.iEmpleados(connection, empleados);
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
