package Controller;

import Config.Con;
import Model.Inversiones;
import Model.Cliente;
import Repository.InsertInversiones;
import Service.GeneralService;
import Service.HoraActualService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class RegistroInversiones {

    public static ArrayList<Inversiones> rInversiones(Scanner sc){
        ArrayList<Inversiones> registroInversiones = new ArrayList<>();
        ArrayList<Cliente> busquedaCliente = new ArrayList<>();
        Connection connection = null;

        try {
            connection = Con.getConn();
            Inversiones inversiones = new Inversiones();
            Cliente cliente = new Cliente();
            registroInversiones.add(inversiones);
            busquedaCliente.add(cliente);

            int producto = 5;
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.println("Tu producto será una inversion");
            System.out.print("Presiona enter para continuar: ");
            sc.nextLine();
            GeneralService.cleanScreen();
            inversiones.setProducto_id(producto);

            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.println("Para abrir una inversion el minimo es de $1000 mxn pesos");
            System.out.print("Con cuanto dinero abrirás tu inversion?");
            double monto = sc.nextDouble();
            double interes = 0.02;
            sc.nextLine();
            if (monto >= 1000) {
                System.out.println("Muchas gracias, el interes compuesto será del 2%");
                inversiones.setMonto(monto);
                inversiones.setTasa_interes(interes);
            } else {
                System.out.println("El monto minimo es de $1000 mxn pesos");
                return rInversiones(sc);
            }

            LocalDate hoy = HoraActualService.FechaActual();
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.println("Tu inversion inicia el día de hoy: " + hoy);
            System.out.println("----------------------------------------------------------------");
            System.out.print("Oprime enter para continuar");
            sc.nextLine();
            inversiones.setFecha_inicio(hoy);

            
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.print("¿Cuantos años durará tu inversion?: ");
            int lapso = sc.nextInt();
            sc.nextLine();
            LocalDate vencimiento = HoraActualService.FechaActual().plusYears(lapso);
            inversiones.setFecha_vencimiento(vencimiento);
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.println("Tu inversion termina el día: " + vencimiento);

            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            String tipoI = "No a la vista";
            System.out.println("Tu inversion será de tipo: " + tipoI);
            inversiones.setTipo_inversion(tipoI);

            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            System.out.print("¿Cual es la curp del cliente a quien pertenecerá esta inversion?: ");
            String curp = sc.nextLine();
            System.out.println("⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝⁝");
            cliente.setCurp(curp);

            InsertInversiones.iInversiones(connection, inversiones, cliente);
            System.out.println("Inversion registrada");
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
        return registroInversiones;
    }
}