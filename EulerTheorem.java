package Cryptography;
import java.math.BigInteger;

public class EulerTheorem {
    public static void main(String[] args) {
        BigInteger a = new BigInteger("5");
        BigInteger n = new BigInteger("7");

        if (a.gcd(n).intValue() == 1) {
            BigInteger phiN = phi(n);
            BigInteger result = a.modPow(phiN, n);

            System.out.println("Result: " + result);
        } else {
            System.out.println("a and n are not coprime.");
        }
    }

    private static BigInteger phi(BigInteger n) {
        BigInteger result = n;

        for (BigInteger i = new BigInteger("2"); i.multiply(i).compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            if (n.mod(i).equals(BigInteger.ZERO)) {
                while (n.mod(i).equals(BigInteger.ZERO)) {
                    n = n.divide(i);
                }
                result = result.subtract(result.divide(i));
            }
        }

        if (n.compareTo(BigInteger.ONE) > 0) {
            result = result.subtract(result.divide(n));
        }

        return result;
    }
}
