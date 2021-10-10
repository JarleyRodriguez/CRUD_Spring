package util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class encriptarPassword {
    public static void main(String[] args) {
        //clase para encriptar contraseñas es de spring
        
        var password ="54321";
        System.out.println("password :" + password);
        System.out.println("password Encrytado: " + encriptarPassword(password));
    }
    
    public static String encriptarPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
