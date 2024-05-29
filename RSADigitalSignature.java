package Cryptography;
import java.security.*;
import java.util.Base64;
public class RSADigitalSignature {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); 
        KeyPair keyPair = keyGen.generateKeyPair();
        String message = "Hello, RSA Digital Signature!";
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(keyPair.getPrivate());
        signature.update(message.getBytes());
        byte[] signatureBytes = signature.sign();
        signature.initVerify(keyPair.getPublic());
        signature.update(message.getBytes());
        boolean isVerified = signature.verify(signatureBytes);
        System.out.println("Message: " + message);
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signatureBytes));
        System.out.println("Signature verified: " + isVerified);
    }
}