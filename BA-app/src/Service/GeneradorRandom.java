package Service;
import java.util.Random;

public class GeneradorRandom {

    private static final Random random = new Random(); // Puedes reutilizar esta instancia

    public static long generarCuenta() {
        // El número más pequeño de 11 dígitos es 10,000,000,000
        long min = 10_000_000_000L;
        // El rango de números de 11 dígitos es 90,000,000,000 (de 10B a 99B)
        long range = 90_000_000_000L;

        return min + (long)(random.nextDouble() * range);
    }

    public static long generarTarjeta() {
        // El número más pequeño de 16 dígitos es 10,000,000,000
        long min = 1_000_000_000_000_000L;
        // El rango de números de 16 dígitos es 90,000,000,000 (de 10B a 99B)
        long range = 9_000_000_000_000_000L;

        return min + (long)(random.nextDouble() * range);
    }

    public static int generarCvv() {

        int min = 1_00;
        int range = 9_00;

        return min + (int)(random.nextDouble() * range);
    }
}


