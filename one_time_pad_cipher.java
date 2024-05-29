package Cryptography;
import java.util.Random;
public class one_time_pad_cipher {
    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final int ALPHABET_SIZE = ALPHABET.length;
    public static void main(String[] args) {
        String key = generateRandomKey(5); 
        System.out.println("Generated Key: " + key);
        String plaintext = "HELLO";
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
    private static String generateRandomKey(int length) {
        StringBuilder key = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALPHABET_SIZE);
            key.append(ALPHABET[randomIndex]);
        }
        return key.toString();
    }
    private static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keyChar = key.charAt(i);
            int encryptedCharIndex = (plainChar - 'A' + keyChar - 'A') % ALPHABET_SIZE;
            ciphertext.append(ALPHABET[encryptedCharIndex]);
        }
        return ciphertext.toString();
    }
    private static String decrypt(String ciphertext, String key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            char keyChar = key.charAt(i);
            int decryptedCharIndex = (cipherChar - 'A' - keyChar + 'A');
            if (decryptedCharIndex < 0) {
                decryptedCharIndex += ALPHABET_SIZE;
            }
            decryptedText.append(ALPHABET[decryptedCharIndex]);
        }
        return decryptedText.toString();
    }
}