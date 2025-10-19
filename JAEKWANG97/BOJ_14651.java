package boj;

import java.util.Scanner;

public class BOJ_14651 {
    private static final long MOD = 1_000_000_009;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(solve(N));
    }

    private static long solve(int N) {
        if (N == 1) return 0; // 1자리수로는 3의 배수 불가
        long pow = modPow(3, N - 2, MOD);
        return (2 * pow) % MOD;
    }

    private static long modPow(long base, int exp, long mod) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
}
