package boj;

import java.util.*;

public class BOJ_20921 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long K = sc.nextLong();

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) list.add(i);

        List<Integer> result = new ArrayList<>();
        for (int i = N; i >= 1; i--) {
            long move = Math.min(K, i - 1);
            result.add((int) (result.size() - move), i);
            K -= move;
        }

        for (int i = 0; i < N; i++) {
            System.out.print(result.get(i) + " ");
        }
    }
}

