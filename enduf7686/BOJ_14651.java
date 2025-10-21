import java.util.Scanner;

public class BOJ_14651 {

    private static final int MOD = 1_000_000_009;

    private static int N;
    private static long answer;

    public static void main(String[] args) {
        init();

        dp();

        printAnswer();
    }

    private static void dp() {
        if (N == 1) {
            answer = 0;
            return;
        }

        long[][][] dp = new long[N + 1][3][3];

        dp[1][1][1] = 1;
        dp[1][2][2] = 1;

        dp[2][0][0] = dp[1][1][0] % MOD + dp[1][2][0] % MOD;
        dp[2][0][1] = dp[1][1][1] % MOD + dp[1][2][1] % MOD;
        dp[2][0][2] = dp[1][1][2] % MOD + dp[1][2][2] % MOD;

        dp[2][1][0] = dp[1][1][2] % MOD + dp[1][2][2] % MOD;
        dp[2][1][1] = dp[1][1][0] % MOD + dp[1][2][0] % MOD;
        dp[2][1][2] = dp[1][1][1] % MOD + dp[1][2][1] % MOD;

        dp[2][2][0] = dp[1][1][1] % MOD + dp[1][2][1] % MOD;
        dp[2][2][1] = dp[1][1][2] % MOD + dp[1][2][2] % MOD;
        dp[2][2][2] = dp[1][1][0] % MOD + dp[1][2][0] % MOD;

        for (int n = 3; n <= N; n++) {
            dp[n][0][0] = dp[n - 1][0][0] % MOD + dp[n - 1][1][0] % MOD + dp[n - 1][2][0] % MOD;
            dp[n][0][1] = dp[n - 1][0][1] % MOD + dp[n - 1][1][1] % MOD + dp[n - 1][2][1] % MOD;
            dp[n][0][2] = dp[n - 1][0][2] % MOD + dp[n - 1][1][2] % MOD + dp[n - 1][2][2] % MOD;

            dp[n][1][0] = dp[n - 1][0][2] % MOD + dp[n - 1][1][2] % MOD + dp[n - 1][2][2] % MOD;
            dp[n][1][1] = dp[n - 1][0][0] % MOD + dp[n - 1][1][0] % MOD + dp[n - 1][2][0] % MOD;
            dp[n][1][2] = dp[n - 1][0][1] % MOD + dp[n - 1][1][1] % MOD + dp[n - 1][2][1] % MOD;

            dp[n][2][0] = dp[n - 1][0][1] % MOD + dp[n - 1][1][1] % MOD + dp[n - 1][2][1] % MOD;
            dp[n][2][1] = dp[n - 1][0][2] % MOD + dp[n - 1][1][2] % MOD + dp[n - 1][2][2] % MOD;
            dp[n][2][2] = dp[n - 1][0][0] % MOD + dp[n - 1][1][0] % MOD + dp[n - 1][2][0] % MOD;
        }

        answer += (dp[N][0][0] % MOD +dp[N][1][0] % MOD + dp[N][2][0] % MOD) % MOD;
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
    }

    private static void printAnswer() {
        System.out.println(answer);
    }
}