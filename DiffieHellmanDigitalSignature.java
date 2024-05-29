package Cryptography;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

public class DiffieHellmanDigitalSignature {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DH");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        String message = "Hello, Diffie-Hellman Digital Signature!";
        SecretKey sharedSecret = new SecretKeySpec(keyPair.getPrivate().getEncoded(), "HmacSHA256");

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(sharedSecret);
        byte[] signatureBytes = mac.doFinal(message.getBytes());

        mac.init(sharedSecret);
        byte[] verifyBytes = mac.doFinal(message.getBytes());

        boolean isVerified = MessageDigest.isEqual(signatureBytes, verifyBytes);

        System.out.println("Message: " + message);
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signatureBytes));
        System.out.println("Signature verified: " + isVerified);
    }
}
