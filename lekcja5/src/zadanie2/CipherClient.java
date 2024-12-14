package zadanie2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CipherClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4444);
            Cipher cipherService = (Cipher) registry.lookup("CipherService");

            String text = "lokomotywa";
            int shift = 1;

            String encryptedText = cipherService.shiftConsonants(text, shift);
            System.out.println("Tekst jawny: " + text);
            System.out.println("Szyfrogram: " + encryptedText);

            String decryptedText = cipherService.decryptConsonants(encryptedText, shift);
            System.out.println("Odszyfrowany tekst: " + decryptedText);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }
}
