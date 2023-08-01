## 자바스크립트의 함수

단순한 형태
```
function sayHello() {
    console.log("Hello my name is ");
}

sayHello();
```

괄호 안의 이름으로 변수를 생성함.
```
function sayHello(nameOfPerson, age) {
    console.log(nameOfPerson);
}

sayHello("nico",3);
sayHello("dal"),2;
sayHello("lynn",1);
```
object를 이용한 function
```
const player1 = {
    name: "nico",
    sayHello: function(otherPersonsName){
        console.log("hello" + otherPersonsName);
    }
};
player1.sayHello("lynn");
```
