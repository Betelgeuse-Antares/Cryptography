package Cryptography;
public class hill_cipher {
    private static final int MATRIX_SIZE = 3; 
    public static void main(String[] args) {
        String key = "GYBNQKURP";
        String plaintext = "act";
        int[][] keyMatrix = getKeyMatrix(key);
        String ciphertext = encrypt(plaintext, keyMatrix);
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Ciphertext: " + ciphertext);
    }
    private static int[][] getKeyMatrix(String key) {
        int[][] keyMatrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        int index = 0;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                keyMatrix[i][j] = (int) (key.charAt(index) - 'A');
                index = (index + 1) % key.length(); 
            }
        }
        return keyMatrix;
    }
    private static String encrypt(String plaintext, int[][] keyMatrix) {
        plaintext = plaintext.toUpperCase();
        StringBuilder ciphertext = new StringBuilder();
        while (plaintext.length() % MATRIX_SIZE != 0) {
            plaintext += 'X';
        }
        for (int i = 0; i < plaintext.length(); i += MATRIX_SIZE) {
            String block = plaintext.substring(i, i + MATRIX_SIZE);
            int[] vector = new int[MATRIX_SIZE];
            for (int j = 0; j < MATRIX_SIZE; j++) {
                vector[j] = block.charAt(j) - 'A';
            }
            int[] encryptedVector = multiply(keyMatrix, vector);
            for (int j = 0; j < MATRIX_SIZE; j++) {
                ciphertext.append((char) (encryptedVector[j] + 'A'));
            }
        }
        return ciphertext.toString();
    }
    private static int[] multiply(int[][] matrix, int[] vector) {
        int[] result = new int[MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
            result[i] %= 26; 
        }
        return result;
    }
}
