package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194 {
    static class Location {
        int x;
        int y;
        int count;
        int keys;

        public Location(int x, int y, int count, int keys) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.keys = keys;
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static char[][] map;
    private static int N, M;
    private static Location start;
    private static Location end;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = input.charAt(j);
                map[i][j] = c;
                if (c == '0') {
                    start = new Location(i, j, 0, 0);
                } else if (c == '1') {
                    end = new Location(i, j, 0, 0);
                }
            }
        }
    }

    private static void solve() {
        Queue<Location> que = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][1 << 6];

        que.add(start);
        visited[start.x][start.y][start.keys] = true;

        while (!que.isEmpty()) {
            Location cur = que.poll();

            if (map[cur.x][cur.y] == '1') {
                System.out.println(cur.count);
                return;
            }

            int[] dx = {1, 0, -1, 0};
            int[] dy = {0, 1, 0, -1};

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (map[nx][ny] == '#') {
                    continue;
                }

                char cell = map[nx][ny];

                if ('A' <= cell && cell <= 'F') {
                    if ((cur.keys & (1 << (cell - 'A'))) == 0) {
                        continue;
                    }
                }
                int newKeys = cur.keys;
                if ('a' <= cell && cell <= 'f') {
                    newKeys |= (1 << (cell - 'a'));
                }

                if (visited[nx][ny][newKeys]) {
                    continue;
                }

                visited[nx][ny][newKeys] = true;
                que.add(new Location(nx, ny, cur.count + 1, newKeys));
            }
        }
        System.out.println(-1);

    }
}

