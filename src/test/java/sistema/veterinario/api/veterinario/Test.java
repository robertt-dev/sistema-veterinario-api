package sistema.veterinario.api.veterinario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.xml.bind.DatatypeConverter;

public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        // String hash = "35454B055CC325EA1AF2126E27707052";
        String password = "brasil123";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        System.out.println(myHash);
    }
}
