package UI;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import Model.Productos_Financieros;
public class MenuCliente {
    
    public static void menuPrincipal(){
        Scanner sc = new Scanner(System.in);
        List<Productos_Financieros> productos = new ArrayList<>();
        
        int id = 0;

        while (true) {
            
        try {
            System.out.println("Menu principal: ");
            System.out.println("1. consultar productos");
            System.out.println("2. Agregar productos");
            System.out.println("3. Editar producto");
            System.out.println("0. Regresar");

            int opt = sc.nextInt();

            switch (opt) {
                case 1:
                    if (id == 0) {
                        System.out.println("No hay productos");
                        break;
                    }
                    else {
                        for(Productos_Financieros n: productos){
                            System.out.println("ID: " + productos.get(id));
                            System.out.println("Nombre: " + productos.get(opt));
                        }
                    }
                    
                    break;
                case 2:
                    System.out.println("**Agregar productos**");
                    System.out.print("Nombre del producto: ");
                    productos.add(new Productos_Financieros());
                    System.out.println("Producto agregado correctamente");
                
                    break;
                
                case 3:
                    System.out.println("");

                case 0:
                    System.out.println("");
            
                default:

                    break;
            }

        } catch (Exception e) {
            System.out.println("Error" + e);
    }
    }
}

}
