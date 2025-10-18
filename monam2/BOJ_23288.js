function print(value) {
  console.log(value);
}

function getInput() {
  const fs = require("fs");
  const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
  const input = fs.readFileSync(filePath).toString().trim().split("\n");
  const [N, M, K] = input.shift().split(" ").map(Number);
  const graph = input.map((row) => row.split(" ").map(Number));
  return { N, M, K, graph };
}

/*
점수 집계
- (현재 주사위가 있는 칸의 점수)
- x (현재 칸에서 이동할 수 있는 칸 중 현재 칸 점수와 같은 것의 갯수)
*/

// 동남서북
const DX = [0, 1, 0, -1];
const DY = [1, 0, -1, 0];

// 위, 북, 동, 서, 남, 아래
let dice = [1, 2, 3, 4, 5, 6];
let totalScore = 0;

/** 주사위 굴리기(주사위 방향으로) */
function rollDice(dice, dist) {
  let [up, north, east, west, south, down] = dice;

  if (dist == 0) {
    // 동
    dice = [west, north, up, down, south, east];
  } else if (dist === 1) {
    // 남
    dice = [north, down, east, west, up, south];
  } else if (dist === 2) {
    // 서
    dice = [east, north, down, up, south, west];
  } else if (dist === 3) {
    // 북
    dice = [south, up, east, west, down, north];
  }

  return dice;
}

/** 주사위 이동 */
function move(x, y, dist, N, M) {
  let nx = x + DX[dist];
  let ny = y + DY[dist];

  if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
    dist = (dist + 2) % 4;
    nx = x + DX[dist];
    ny = y + DY[dist];
  }
  return { nx, ny, dist };
}

/** 현재 위치 점수 계산 */
function getScore(graph, x, y, N, M) {
  const visited = Array.from({ length: N }, () => Array(M).fill(false));
  const queue = [[x, y]];
  visited[x][y] = true;

  let cnt = 1;

  while (queue.length > 0) {
    const [cx, cy] = queue.shift();

    for (let d = 0; d < 4; d++) {
      const nx = cx + DX[d];
      const ny = cy + DY[d];

      if (0 > nx || nx >= N || 0 > ny || ny >= M) continue;

      const isEqual = graph[x][y] === graph[nx][ny]; // 가는 곳이 B와 같은지
      if (!visited[nx][ny] && isEqual) {
        visited[nx][ny] = true;
        queue.push([nx, ny]);
        cnt++;
      }
    }
  }

  return graph[x][y] * cnt;
}

/** 다음 방향 결정 */
function getNextDist(graph, x, y, dice, dist) {
  const A = dice[5]; // 주사위 아랫면
  const B = graph[x][y];

  if (A > B) {
    dist = (dist + 1) % 4;
  } else if (A < B) {
    dist = (dist + 3) % 4;
  }
  return dist;
}

function main() {
  const { N, M, K, graph } = getInput();
  let x = 0,
    y = 0,
    dist = 0;

  for (let i = 0; i < K; i++) {
    // 1. 이동
    const moveResult = move(x, y, dist, N, M);
    x = moveResult.nx;
    y = moveResult.ny;
    dist = moveResult.dist;

    // 2. 주사위 굴리기
    dice = rollDice(dice, dist);

    // 3. 점수 계산
    totalScore += getScore(graph, x, y, N, M);

    // 4. 다음 방향 결정
    dist = getNextDist(graph, x, y, dice, dist);
  }

  print(totalScore);
}

main();
