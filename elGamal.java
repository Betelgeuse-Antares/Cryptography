package Cryptography;
import java.math.BigInteger;
import java.security.SecureRandom;

public class elGamal {
    private BigInteger p; 
    private BigInteger g; 
    private BigInteger x; 
    private BigInteger y; 
    public elGamal(int bitLength) {
        generateKeys(bitLength);
    }
    private void generateKeys(int bitLength) {
        p = BigInteger.probablePrime(bitLength, new SecureRandom());
        g = generateGenerator(p);
        x = BigInteger.valueOf(2 + (long) (Math.random() * (p.longValue() - 2)));
        y = g.modPow(x, p);
    }
    public BigInteger[] encrypt(BigInteger plaintext) {
        BigInteger k = BigInteger.valueOf(2 + (long) (Math.random() * (p.longValue() - 2))); 
        BigInteger c1 = g.modPow(k, p); 
        BigInteger c2 = plaintext.multiply(y.modPow(k, p)).mod(p); 
        return new BigInteger[]{c1, c2};
    }
    public BigInteger decrypt(BigInteger[] ciphertext) {
        BigInteger c1 = ciphertext[0];
        BigInteger c2 = ciphertext[1];
        BigInteger s = c1.modPow(x, p); 
        BigInteger plaintext = c2.multiply(s.modInverse(p)).mod(p); 
        return plaintext;
    }
    private BigInteger generateGenerator(BigInteger p) {
        for (BigInteger g = BigInteger.valueOf(2); g.compareTo(p.subtract(BigInteger.ONE)) < 0; g = g.add(BigInteger.ONE)) {
            if (g.modPow(p.subtract(BigInteger.ONE), p).equals(BigInteger.ONE)) {
                return g;
            }
        }
        return null; 
    }
    public static void main(String[] args) {
        int bitLength = 64;
        elGamal elGamal = new elGamal(bitLength);
        BigInteger plaintext = new BigInteger("1234567890");
        BigInteger[] ciphertext = elGamal.encrypt(plaintext);
        System.out.println("Encrypted message:");
        System.out.println("c1: " + ciphertext[0]);
        System.out.println("c2: " + ciphertext[1]);
        BigInteger decryptedPlaintext = elGamal.decrypt(ciphertext);
        System.out.println("Decrypted message:");
        System.out.println(decryptedPlaintext);
    }
}