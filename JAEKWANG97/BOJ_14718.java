import java.io.*;
import java.util.*;

public class BOJ_14718 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] enemies = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            enemies[i][0] = Integer.parseInt(st.nextToken());
            enemies[i][1] = Integer.parseInt(st.nextToken());
            enemies[i][2] = Integer.parseInt(st.nextToken());
        }

        int answer = Integer.MAX_VALUE;

        // 모든 가능한 능력치 조합 완전 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    int a = enemies[i][0];
                    int b = enemies[j][1];
                    int c = enemies[k][2];

                    int count = 0;
                    for (int e = 0; e < N; e++) {
                        if (a >= enemies[e][0] && b >= enemies[e][1] && c >= enemies[e][2]) {
                            count++;
                        }
                    }

                    if (count >= K) {
                        answer = Math.min(answer, a + b + c);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
