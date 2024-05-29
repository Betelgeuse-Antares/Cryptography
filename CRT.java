/* Author: RAGHAVVRAM J
 * RegNo: 22BCE9190
 */

public class CRT {
    public static int invmod(int N, int num){
        for(int i=1; i<num; i++){
            if ((N * i) % num == 1) {
                return i;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        int[] Num = {3, 4, 5};
        int[] Rem = {2, 3, 1};
        int product = 60;
        int[] pp = {product/Num[0], product/Num[1], product/Num[2]};
        int[] inv = {invmod(pp[0], Num[0]), invmod(pp[1], Num[1]), invmod(pp[2], Num[2])};
        int X = 0;

        for(int i = 0; i < Num.length; i++) {
            X += Rem[i] * inv[i] * pp[i];
        }
        X = X % product;
        System.out.println("Output: " + X);
    }
}
