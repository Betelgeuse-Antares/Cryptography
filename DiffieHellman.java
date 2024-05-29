/**Author: RAGHAVVRAM J*/
public class DiffieHellman {
	// Power function to return value of a ^ b mod P
	public static long power(long a, long b, long p){
		if (b == 1) {return a;}
		else {return (((long) Math.pow(a, b)) % p);}
	}
	public static void main(String[] args)
	{
		long P, G, x, a, y, b, ka, kb;
		// public keys G and P
		// A prime number P is taken
		P = 23; System.out.println("The value of P:" + P);
		// A prime number G is taken
		G = 9; System.out.println("The value of G:" + G);
		// A will choose the private key a
		a = 4; System.out.println("The private key a for A:"+ a);
		x = power(G, a, P);
		// B will choose the private key b
		b = 3; System.out.println("The private key b for B:"+ b);
		y = power(G, b, P);
		// Generating the secret key after the exchange of keys
		ka = power(y, a, P); // Secret key for A
		kb = power(x, b, P); // Secret key for B
		System.out.println("Secret key for the A is:"+ ka);
		System.out.println("Secret key for the B is:"+ kb);
	}
}
