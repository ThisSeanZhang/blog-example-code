function sleep_s(time) {
  return new Promise((res, rej) => {
    setTimeout(() => res(), time);
  });
}

async function main() {
  let start = new Date().getTime();
  console.log(`start at ${start}`);
  let a = sleep_s(2000);
  let b = sleep_s(3000);
  // await b;
  // await a;
  await Promise.all([a, b]);
  console.log(`end of ${(new Date().getTime() - start) / 1000}`);
  throw new Error("test");
}

// main();

try{
  main()
} catch(e) {
  console.log(`catch you ${e}`);
}
console.log('=====================================')
Promise.resolve(main()).catch((e) => console.log(e));