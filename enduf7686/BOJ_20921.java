import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BOJ_20921 {

    private static int N, K;
    private static int[] arr;

    public static void main(String[] args) {
        init();

        greedy();

        printAnswer();
    }

    private static void greedy() {
        int indexFirst = 0;
        int indexLast = N - 1;

        for (int n = N; n >= 1; n--) {
            if (K == 0) {
                break;
            }

            if (K >= n - 1) {
                arr[indexFirst++] = n;
                K -= (n - 1);
                continue;
            }

            if (K <= n - 2) {
                arr[indexLast--] = n;
                continue;
            }

            arr[N - K - 1] = n;
            K = 0;
        }

        int num = 1;

        for (int i = 0; i < N; i++) {
            if (arr[i] == 0) {
                arr[i] = num++;
            }
        }
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        arr = new int[N];
    }

    private static void printAnswer() {
        String answer = Arrays.stream(arr)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(answer);
    }
}
