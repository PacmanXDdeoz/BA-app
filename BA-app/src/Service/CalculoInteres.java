package Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculoInteres {

    private static final BigDecimal TASA_INTERES_FIJA = new BigDecimal("0.02"); // 2%

    public static BigDecimal calcularMontoFinalSimple(
            BigDecimal montoInicial,
            LocalDate fechaInicio,
            LocalDate fechaVencimiento) {

        // --- Validaciones ---
        if (montoInicial == null || montoInicial.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto inicial debe ser mayor a cero.");
        }
        if (fechaInicio == null || fechaVencimiento == null) {
            throw new IllegalArgumentException("Las fechas de inicio y vencimiento no pueden ser nulas.");
        }
        if (fechaVencimiento.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser anterior a la fecha de inicio.");
        }

        // --- Calcular la duración en años ---
        // Usamos ChronoUnit.DAYS para la duración en días y luego la convertimos a años
        // para manejar fracciones de año. Utilizamos 365.25 para considerar años bisiestos.
        long diasDuracion = ChronoUnit.DAYS.between(fechaInicio, fechaVencimiento);
        BigDecimal duracionEnAnios = BigDecimal.valueOf(diasDuracion).divide(new BigDecimal("365.25"), 10, BigDecimal.ROUND_HALF_UP);

        // --- Cálculo de Interés Compuesto Anual ---
        // Monto_Final = MontoInicial * (1 + Tasa_Interes_Fija)^DuracionEnAnios
        BigDecimal factorCrecimiento = BigDecimal.ONE.add(TASA_INTERES_FIJA);

        // Convertimos a double para usar Math.pow() para el cálculo de potencia,
        // ya que BigDecimal no tiene un método pow() para exponentes decimales.
        // Luego, convertimos el resultado de vuelta a BigDecimal.
        double montoFinalDouble = montoInicial.doubleValue() * Math.pow(factorCrecimiento.doubleValue(), duracionEnAnios.doubleValue());

        // Redondeamos el resultado final a dos decimales, como es común en moneda
        return BigDecimal.valueOf(montoFinalDouble).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
