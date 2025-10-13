const MOD = 1000000009n;

function print(value) {
  console.log(value);
}

function getInput() {
  const fs = require("fs");
  const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
  const [input] = fs.readFileSync(filePath).toString().trim().split("\n");
  return input;
}

function getAnswer(v) {
  if (v === 1) return 0n;
  if (v === 2) return 2n;

  let res = 2n;
  for (let i = 2n; i < v; i++) {
    res = (res * 3n) % MOD;
  }
  return res;
}

function main() {
  const n = Number(getInput());
  const answer = getAnswer(n);

  print(Number(answer));
}

main();
