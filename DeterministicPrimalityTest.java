import java.math.BigInteger;
public class DeterministicPrimalityTest {
    public static boolean isPrime(BigInteger n, int k) {
        if (n.compareTo(BigInteger.ONE) <= 0)
            return false;
        if (n.compareTo(BigInteger.valueOf(3)) <= 0)
            return true;
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO))
            return false;
        int r = 0;
        BigInteger d = n.subtract(BigInteger.ONE);
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.TWO);
            r++;
        }
        for (int i = 0; i < k; i++) {
            BigInteger a = randomBigInteger(BigInteger.TWO, n.subtract(BigInteger.ONE));
            BigInteger x = a.modPow(d, n);
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE)))
                continue;
            boolean composite = true;
            for (int j = 1; j < r; j++) {
                x = x.modPow(BigInteger.TWO, n);
                if (x.equals(BigInteger.ONE))
                    return false; // n is composite
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    composite = false;
                    break;
                }
            }
            if (composite)
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