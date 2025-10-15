function print(value) {
  console.log(value.join(" "));
}

function getInput() {
  const fs = require("fs");
  const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
  const [input] = fs.readFileSync(filePath).toString().trim().split("\n");
  return input.split(" ").map((i) => +i);
}

function makeDescArray(n) {
  return Array.from({ length: n }, (_, i) => n - i);
}

function main() {
  let [n, k] = getInput();

  let resArr = [];
  const remains = makeDescArray(n);

  for (let i = 0; i < n; i++) {
    const len = remains.length;

    for (let j = 0; j < len; j++) {
      const invCnt = len - 1 - j;

      if (invCnt <= k) {
        resArr.push(remains.splice(j, 1)[0]);
        k -= invCnt;
        break;
      }
    }
  }
  print(resArr);
}

main();
