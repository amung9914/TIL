### 배열

배열 생성 두가지 방법

```
let arr1 = new Array();
let arr2 = [];
```

배열엔 다양한 타입이 들어갈 수 있음

```
                                    // 객체, 배열, 함수
let arr = [1,"2",true,null,undefined,{},[],function(){}];
console.log(arr);
```

배열에 값 추가하기(push메서드)

```
let arr = [1,2,3,4,5];

// 배열의 마지막에 원소 추가
arr.push(6);
arr.push({key:"value"});
console.log(arr.length); // 배열 길이 출력
```

### 배열 내장함수 이용

forEach() : 배열의 각각의 요소에 대해 실행한다

```
for(let i = 0 ; i<arr.length; i++){
  console.log(arr[i]);
}
//위와 같다
arr.forEach((elm)=> console.log(elm));

// 함수형으로 바꿔보면
arr.forEach(function (elm){
  console.log(elm);
})
```

map() : 원본 배열의 모든 요소를 순회하면서 어떤 연산을 해서 리턴된 값만 따로 추려내서 반환해줌

```
const arr = [1, 2, 3, 4];

const arr1 = [];
arr.forEach((elm) => arr1.push(elm * 2));

// 위와 같다
const newArr = arr.map((elm)=>{
  return elm*2;
})

console.log(newArr);
```

includes() : 배열에 전달받은 인자와 일치하는 값이 존재하는지 boolean타입으로 반환(===연산을 사용한다(타입까지 비교))

```
const arr = [1, 2, 3, 4];

let number = 3;
arr.forEach((elm)=>{
  if(elm === number){
    console.log(true);
  }
});
//위와 같다
console.log(arr.includes(number));
```

indexOf() : 전달받은 인자와 일치하는 값의 인덱스번호를 반환한다. (===연산을 사용한다(타입까지 비교))

```
const arr = [1, 2, 3, 4];

let number = 3;
console.log(arr.indexOf(number)); // 2
```

객체배열에서 찾기 -> findIndex() : 콜백함수가 true인 인덱스 번호 반환  
만약 배열에 두가지 이상 일치하는 값이 있는 경우 제일 처음 만난 인덱스 반환

```
const arr = [
  {color: "red"},
  {color: "black"},
  {color: "blue"},
  {color: "green"}
];

console.log(arr.findIndex((elm)=>elm.color === "green")); // 3

// 위와 같다
console.log(arr.findIndex((elm)=> {
  return elm.color === "green"})); // 3
```

find() : 조건에 일치하는 요소를 가져온다

```
const idx = arr.findIndex((elm)=> {
  return elm.color === "green"});

console.log(arr[idx]); // {color: "green"}

// 위 와 같다
const element = arr.find((elm)=> elm.color ==="green");
console.log(element); // {color: "green"}
```

filter() : 배열 필터링 : 배열에서 특정한 조건을 만족하는 요소를 배열 형태로 반환

```
const arr = [
  { num: 1, color: "red" },
  { num: 2, color: "black" },
  { num: 3, color: "blue" },
  { num: 4, color: "green" }
];

console.log(arr.filter((elm) => elm.color === "blue"));
```

slice() : 잘린 배열을 새로운 배열로 반환

```

const arr = [
  { num: 1, color: "red" },
  { num: 2, color: "black" },
  { num: 3, color: "blue" },
  { num: 4, color: "green" }
];

console.log(arr.slice(0,2)); // end-1까지 반환 
// 출력값 : (2) [Object, Object]
```

concat() : 배열 붙여서 새로운 배열로 반환

```
const arr1 = [
  { num: 1, color: "red" },
  { num: 2, color: "black" },
  { num: 3, color: "blue" },
];

const arr2 = [
  { num: 4, color: "green" },
  { num: 5, color: "blue" },
];

console.log(arr1.concat(arr2)); 
// arr1의 마지막인덱스 뒤에 arr2 붙임
```

sort() : 원본배열을 사전순으로 정렬

숫자를 정렬할때는 비교함수를 만들어서 전달해야함

```
let chars = ["나", "다", "가"];
chars.sort();
console.log(chars); // ["가", "나", "다"]

let numbers = [0, 1, 3, 2, 10, 30, 20];

const compare = (a, b) => {
  // 1. 같다
  // 2. 크다
  // 3. 작다
  if (a > b) {
    // 크다
    return 1; // a를 뒤로 보낸다
  }
  if (a < b) {
    // 작다
    return -1; // a를 앞으로 보낸다
  }
  // 같다
  return 0; // 옮기지 않는다
};

numbers.sort(compare);
console.log(numbers); // [0, 1, 2, 3, 10, 20, 30]
```

join() : 배열 요소들을 문자열로 합쳐서 반환한다.

```
const arr = ["이정환","님","안녕하세요","또오셨네요"];
console.log(arr.join()); // 구분자를 안넣으면 ","로 구분
// 이정환,님,안녕하세요,또오셨네요 
console.log(arr.join(" ")); // 이정환 님 안녕하세요 또오셨네요 
console.log(arr.join("==="));
```
