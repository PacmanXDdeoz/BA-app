package Controller;

import Config.Con;
import Model.Empleados;
import Repository.InsertEmpleados;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistroEmpleado {
    public RegistroEmpleado() {
    }

    public static ArrayList<Empleados> rEmpleado() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Empleados> registroEmpleados = new ArrayList<>();
        Connection connection = null;

        try {
            connection = Con.getConn();
            Empleados empleados = new Empleados();
            registroEmpleados.add(empleados);
            int id = registroEmpleados.size() + 1;
            ((Empleados)registroEmpleados.get(registroEmpleados.size() - 1)).setEmpleado_id(id);

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
            }

            System.out.println("selecciona una opcion valida");
            } while(sucursal >= 5);

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
            if (departamento >= 1 && departamento <= 7) {
                empleados.setDepartamento_id(departamento);
                break;
            } else {
                System.out.println("Selecciona una opcion valida");
            }
            } while(departamento > 7);

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
        do {
            System.out.println("¿En que puesto te contrataron?");
            System.out.println(" 1. Ejecutivo");
            System.out.println(" 2. Asesor");
            System.out.println(" 3. Atencion a clientes");
            System.out.println(" 4. Gerente");
            puesto = sc.nextInt();
            sc.nextLine();
            if (puesto >= 1 && puesto <= 4) {
                empleados.setPuesto_id(puesto);
                break;
            } else {
                System.out.println("Selecciona una opcion valida");
            }
        } while(puesto > 4);

        double salario = 0;
        if (puesto == 1) {
            salario = 8000;
            empleados.setSalario(salario);
            System.out.println("Tu salario será de $8000 pesos \ud83d\udcb8");
        }

        if (puesto == 2) {
            salario = 7500;
            empleados.setSalario(salario);
            System.out.println("Tu salario será de $7500 pesos \ud83d\udcb8");
        }

        if (puesto == 3) {
            salario = 7000;
            empleados.setSalario(salario);
            System.out.println("Tu salario será de $7000 pesos \ud83d\udcb8");
        }

        if (puesto == 4) {
            salario = 12000;
            empleados.setSalario(salario);
            System.out.println("Tu salario será de $12000 pesos \ud83d\udcb8");
        }

        InsertEmpleados.iEmpleados(connection, empleados);
        System.out.println("Empleado registrado");
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
        }
        return registroEmpleados;
    }
}