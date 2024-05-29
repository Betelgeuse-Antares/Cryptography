package Cryptography;
import java.security.*;

public class RSA_DS {
    public static void main(String[] args) throws Exception {
        // Generate key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Sign
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        String msg = "Hello, world!";
        signature.update(msg.getBytes());
        byte[] digitalSignature = signature.sign();

        // Verify
        signature.initVerify(publicKey);
        signature.update(msg.getBytes());
        boolean isVerified = signature.verify(digitalSignature);
        System.out.println("Signature verified: " + isVerified);
    }
}
