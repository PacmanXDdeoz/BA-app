package Service;

import java.io.IOException;

public class GenerealService {
    
    public final void cleanScreen() {
        try {
            String sistemaOperativo = System.getProperty("os.name").toLowerCase();

            if (sistemaOperativo.contains("win")) {
                // * Comando para Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // * Comando para Linux
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar limpiar la terminal: " + e.getMessage());
        }
    }

    public final void showLoadingTruck() {
        String truck = "...💸  ";
        int steps = 10; // Número de pasos en la animación
        int delay = 300; // Tiempo entre pasos en milisegundos

        for (int i = 0; i < steps; i++) {
            try {
                // Borra la línea anterior para simular movimiento
                System.out.print("\r" + " ".repeat(i) + truck);
                Thread.sleep(delay); // Pausa antes del siguiente paso
            } catch (InterruptedException e) {
                System.out.println("\nError durante la animación de carga.");
                Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
                break;
            }
        }
    }
}
