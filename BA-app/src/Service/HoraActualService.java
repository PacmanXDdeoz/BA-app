package Service;

import java.time.LocalDate;
import java.time.Period;

public class HoraActualService {

    public static LocalDate FechaActual(){
        return LocalDate.now();
    }
    
    public static LocalDate FechaExpiracion(){
        return LocalDate.now().plusYears(3);
    }

    public static int calcularEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");
        }
        // Obtiene la fecha actual
        LocalDate fechaActual = LocalDate.now();
        // Calcula el período entre la fecha de nacimiento y la fecha actual
        return Period.between(fechaNacimiento, fechaActual).getYears();
    }
}
