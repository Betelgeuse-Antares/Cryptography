package Cryptography;
import java.math.BigInteger;
public class ProbabilisticPrimalityTest {
    public static boolean isPrime(BigInteger n, int k) {
        if (n.compareTo(BigInteger.ONE) <= 0)
            return false;
        if (n.compareTo(BigInteger.valueOf(3)) <= 0)
            return true;
        for (int i = 0; i < k; i++) {
            BigInteger a = randomBigInteger(BigInteger.TWO, n.subtract(BigInteger.ONE));
            if (!a.modPow(n.subtract(BigInteger.ONE), n).equals(BigInteger.ONE))
                return false; 
        }
        return true; 
    }
    private static BigInteger randomBigInteger(BigInteger min, BigInteger max) {
        BigInteger range = max.subtract(min).add(BigInteger.ONE);
        return new BigInteger(range.bitLength(), new java.util.Random()).add(min);
    }
    public static void main(String[] args) {
        BigInteger number = new BigInteger("982451653"); 
        int iterations = 5; 
        boolean isPrime = isPrime(number, iterations);
        System.out.println(number + " is prime: " + isPrime);
    }
}