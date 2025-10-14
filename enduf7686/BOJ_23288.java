import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_23288 {

    private static int[][] dice = {
            {0, 2, 0},
            {4, 1, 3},
            {0, 5, 0},
            {0, 6, 0}
    };

    private static int N, M, K;
    private static int[][] map;
    private static int answer;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        init();

        simulation();

        printAnswer();
    }

    private static void simulation() {
        int y = 1;
        int x = 1;
        int direction = 1;

        for (int turn = 0; turn < K; turn++) {
            if ((direction == 0 && y == 1) ||
                    (direction == 1 && x == M) ||
                    (direction == 2 && y == N) ||
                    (direction == 3 && x == 1)) {
                direction = (direction + 2) % 4;
            }

            move(direction);

            y = y + dy[direction];
            x = x + dx[direction];

            answer += getScore(y, x);

            if (dice[3][1] > map[y][x]) {
                direction = (direction + 1) % 4;
            }

            if (dice[3][1] < map[y][x]) {
                direction = (direction + 3) % 4;
            }
        }
    }

    private static void move(int direction) {
        if (direction == 0) {
            moveUp();
            return;
        }

        if (direction == 1) {
            moveRight();
            return;
        }

        if (direction == 2) {
            moveDown();
            return;
        }

        moveLeft();
    }

    private static int getScore(int y, int x) {
        int score = 1;

        Queue<Location> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][M + 1];

        queue.offer(new Location(y, x));
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            Location current = queue.poll();

            for (int direction = 0; direction < 4; direction++) {
                int Y = current.y + dy[direction];
                int X = current.x + dx[direction];

                if (Y < 1 || Y >= N + 1 || X < 1 || X >= M + 1 || visited[Y][X] || map[Y][X] != map[current.y][current.x]) {
                    continue;
                }

                queue.offer(new Location(Y, X));
                visited[Y][X] = true;
                score++;
            }
        }

        return score * map[y][x];
    }

    private static void moveUp() {
        int temp = dice[0][1];

        dice[0][1] = dice[1][1];
        dice[1][1] = dice[2][1];
        dice[2][1] = dice[3][1];
        dice[3][1] = temp;
    }

    private static void moveRight() {
        int temp = dice[1][2];

        dice[1][2] = dice[1][1];
        dice[1][1] = dice[1][0];
        dice[1][0] = dice[3][1];
        dice[3][1] = temp;
    }

    private static void moveDown() {
        int temp = dice[3][1];

        dice[3][1] = dice[2][1];
        dice[2][1] = dice[1][1];
        dice[1][1] = dice[0][1];
        dice[0][1] = temp;
    }

    private static void moveLeft() {
        int temp = dice[1][0];

        dice[1][0] = dice[1][1];
        dice[1][1] = dice[1][2];
        dice[1][2] = dice[3][1];
        dice[3][1] = temp;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        for (int y = 1; y <= N; y++) {
            st = new StringTokenizer(br.readLine());

            for (int x = 1; x <= M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private static class Location {

        private int y;
        private int x;

        public Location(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
