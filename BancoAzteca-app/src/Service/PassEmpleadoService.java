package Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PassEmpleadoService {
    
    // Longitud del salt en bytes
    private static final int SALT_LENGTH = 16;
    
    // Algoritmo de hash (SHA-256 es parte de la biblioteca estándar)
    private static final String HASH_ALGORITHM = "SHA-256";
    
    // Número de iteraciones para hacer el proceso más costoso computacionalmente
    private static final int ITERATIONS = 10000;
    
    /**
     * Genera un salt aleatorio
     * @return Array de bytes con el salt generado
     */
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }
    
    /**
     * Aplica hash a la contraseña con el salt proporcionado
     * @param password Contraseña en texto plano
     * @param salt Salt para el hash
     * @return Hash resultante como array de bytes
     */
    private static byte[] hashWithSalt(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        
        // Incorpora el salt
        digest.update(salt);
        
        // Convierte la contraseña a bytes
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        
        // Aplica hash inicial
        byte[] result = digest.digest(passwordBytes);
        
        // Aplica múltiples iteraciones para hacer el proceso más costoso
        for (int i = 0; i < ITERATIONS; i++) {
            digest.reset();
            result = digest.digest(result);
        }
        
        return result;
    }
    
    /**
     * Encripta una contraseña
     * @param plainPassword Contraseña en texto plano
     * @return String con formato "salt:hash" codificado en Base64
     */
    public static String hashPassword(String plainPassword) {
        try {
            // Genera un salt aleatorio
            byte[] salt = generateSalt();
            
            // Aplica hash a la contraseña con el salt
            byte[] hashedPassword = hashWithSalt(plainPassword, salt);
            
            // Codifica salt y hash en Base64
            String saltBase64 = Base64.getEncoder().encodeToString(salt);
            String hashBase64 = Base64.getEncoder().encodeToString(hashedPassword);
            
            // Devuelve "salt:hash" para almacenar ambos
            return saltBase64 + ":" + hashBase64;
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }
    
    /**
     * Verifica si una contraseña coincide con su hash almacenado
     * @param plainPassword Contraseña en texto plano a verificar
     * @param storedHash Hash almacenado en formato "salt:hash"
     * @return true si la contraseña coincide, false en caso contrario
     */
    public static boolean checkPassword(String plainPassword, String storedHash) {
        try {
            // Separa el salt y el hash
            String[] parts = storedHash.split(":");
            if (parts.length != 2) {
                return false;
            }
            
            // Decodifica el salt y el hash almacenados
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] expectedHash = Base64.getDecoder().decode(parts[1]);
            
            // Calcula el hash de la contraseña proporcionada con el mismo salt
            byte[] actualHash = hashWithSalt(plainPassword, salt);
            
            // Compara los hashes (usando comparación de tiempo constante)
            return MessageDigest.isEqual(expectedHash, actualHash);
            
        } catch (NoSuchAlgorithmException | IllegalArgumentException e) {
            return false;
        }
    }
}




/* import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassEmpleadoService {
    // TODO: Valida la contraseña para que cumpla con las especificaciones
        public static boolean validarPass(String password_empleado){
            if(password_empleado.length() < 8) {
                return false; // ? Verifica la longitud minima
            }

            Pattern letraMay = Pattern.compile("[A-Z]"); // ? Busca patrones de mayusculas
            Pattern letraMin = Pattern.compile("[a-z]"); // ? Busca patrones de minusculas
            Pattern num = Pattern.compile("[0-9]"); // ? Busca patrones de numeros

            Matcher mayusculas = letraMay.matcher(password_empleado); // ? Verifica que se cumpla con letras mayusuclas
            Matcher minusculas = letraMin.matcher(password_empleado); // ? Verifica que se cumpla con letras minusculas
            Matcher numero = num.matcher(password_empleado); // ? Verifica que se cumpla con numeros

            return mayusculas.find() && minusculas.find() && numero.find();
        }

        // TODO: Algoritmo para encriptar contraseña usando SHA-256
        public static String hashPass(String password_empleado){
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password_empleado.getBytes());

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
} */
