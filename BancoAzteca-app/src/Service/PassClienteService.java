package Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassClienteService {
    // TODO: Valida la contraseña para que cumpla con las especificaciones
        public static boolean validarPass(String password){
            if(password.length() < 8) {
                return false; // ? Verifica la longitud minima
            }

            Pattern letraMay = Pattern.compile("[A-Z]"); // ? Busca patrones de mayusculas
            Pattern letraMin = Pattern.compile("[a-z]"); // ? Busca patrones de minusculas
            Pattern num = Pattern.compile("[0-9]"); // ? Busca patrones de numeros

            Matcher mayusculas = letraMay.matcher(password); // ? Verifica que se cumpla con letras mayusuclas
            Matcher minusculas = letraMin.matcher(password); // ? Verifica que se cumpla con letras minusculas
            Matcher numero = num.matcher(password); // ? Verifica que se cumpla con numeros

            return mayusculas.find() && minusculas.find() && numero.find();
        }

        // TODO: Algoritmo para encriptar contraseña usando SHA-256
        public static String hashPass(String password){
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes());

                // ? Convierte los bytes a representacion hexadecimal
                StringBuilder hexString = new StringBuilder();
                for(byte b : hash) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1){
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Error al hasear la contraseña: " + e.getMessage());
                // En caso de error, devolver una cadena vacia
                return "";
            }
        }
}
