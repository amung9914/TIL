## 람다 함수란?
람다 함수는 이름을 가질 필요가 없는 익명함수입니다
자바는 람다식을 함수형 인터페이스(FunctionalInterface)의 익명 구현 객체로 취급합니다.

### 람다의 표현식
람다는 매개변수, 화살표, 함수몸통으로 이용해서 사용할 수 있습니다.
```
() -> {}
() -> 1
() -> {return 1;}
(int x) -> x+1
```

#### 함수형 인터페이스
하나의 추상메소드 선언된 인터페이스.
