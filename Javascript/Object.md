### 객체

객체 생성 방법 - 객체 리터럴 방식이 주로 쓰임

```
let person = new Object(); // 생성자 방식
let person = {}; // 객체 리터럴 방식
```

```
let person = {
  key: "value", // 프로퍼티(객체 프로퍼티)
  key1: 123,
  key2 : true,
  key3 : undefined,
  key4 : [1,2],
  key5 : function(){}
}; // 객체 리터럴 방식
```

객체 접근 방법 2가지 - 점표기법, 괄호표기법

괄호표기법은 동적인 파라미터를 꺼내오거나 키가 고정되어있지 않은 상황일때 사용함.

```
console.log(person.name); 
console.log(person["name"]); // 반드시 키를 문자열형태로 넣는다
// 동적인 파라미터를 꺼내오거나 키가 고정되어있지 않은 상황일때 쓴다.
console.log(getPropertyValue("name"));

function getPropertyValue(key) {
  return person[key];
}
```

```
const person = {
  name: "이정환",
  age: 25
}; // 객체 리터럴 방식

// 프로퍼티 추가
person.location = "한국";
person['gender'] = "남성";

// 프로퍼티 수정
person.name = "이정환A";

// 프로퍼티 삭제
person.name = null;
```

delete 명령어로 삭제하면 메모리는 그대로 남아있어서 null을 대입하는것이 더 좋다.

메서드가 아닌 프로퍼티는 멤버라고 부른다.

```
const person = {
  name: "이정환", // 멤버
  age: 25, // 멤버
  say: function(){
    console.log(`안녕나는 ${this.name}`);
  } // 메서드 
}; // 객체 리터럴 방식

// 객체 메서드 호출
person.say();
person["say"]();
```

객체 내에 해당 프로퍼티가 있는지 확인하는 방법 : in 연산자 활용(boolean으로 반환)

```
console.log(`name : ${"name" in person}`)
// 출력값: name : true
```

객체를 순회해보자(Object.keys(변수이름))

```
let person = {
  name: "이정환",
  age: 25,
  tall: 175
};
// 객체의 키를 배열로 반환
const personKeys = Object.keys(person);
console.log(personKeys);

for(let i=0; i<personKeys.length; i++){
  console.log(personKeys[i]);
}
```

key를 가지고 value 출력

```
// 객체의 키를 배열로 반환
const personKeys = Object.keys(person);

for(let i=0; i<personKeys.length; i++){
    const curKey = personKeys[i];
    const curValue = person[curKey]; // key를 이용해서 value에 접근
    console.log(`${curKey} : ${curValue}`);
}
```

객체의 value만 배열 형태로 반환하는 Object.values(변수이름); 도 있다.

```
const personValues = Object.values(person);
for(let i = 0; i< personValues.length; i++){
  console.log(personValues[i]);
}
```
