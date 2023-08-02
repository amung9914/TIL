### 1. 타입변환
- parseInt()
```
 const age = prompt("How old are you?");
        console.log(age,parseInt(age));
```
- 함수는 내부에서 외부로 실행 
 ``const age = parseInt(prompt("How old are you?"));``<br/>
 여기서는 prompt가 먼저 실행되고 반환된 값으로 타입변환

### 2. isNaN : number인지 아닌지 알려줌 (boolean)
```
 const age = parseInt(prompt("How old are you?"));
        
        if(isNaN(age)){
            console.log("Please write a number");
        }else{
            console.log("Thank you for writing your age");
        }
```
- 값의 비교
```
const age = parseInt(prompt("How old are you?"));
        
        if(isNaN(age)){
            console.log("Please write a number");
        }else if(age === 100){
            console.log("wow your are wise");
        }else if(age !== 100){
            console.log("not 100");
        }
```
- 자바스크립트에서 ==와 ===는 비교 연산자로, 두 값의 동등성을 평가하는 방법이 다릅니다.
- '=='는 비교 전에 타입변환을 수행한다.
- '==='는 타입변환 없이 두 값의 타입과 값 모두를 비교한다.
- 일반적으로 자바스크립트에서는 `===`를 사용하는 것이 권장된다.(코드의 안정성)
