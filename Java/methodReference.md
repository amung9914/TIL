## 메소드 참조

메소드 참조는 람다 표현식이 단 하나의 메소드만을 호출하는 경우에 불필요한 매개변수를 제거하고 사용할 수 있게 해줌.

메소드 참조를 사용하면 불필요한 매개변수를 제거하고 '::'기호를 사용하여 표현할 수 있다.
```
클래스이름::메소드 이름

또는

참조변수이름::메소드이름
```
클래스이름으로 사용하는 예제
```
  IntStream stream = new Random().ints(4);
  		//stream.forEach(e->System.out.println(e));
        stream.forEach(System.out::println);
```
참조변수이름으로 사용하는 예제
```
MyClass obj = new MyClass;

Function<String, Boolean> func = (a) -> obj.equals(a); // 람다 표현식
Function<String, Boolean> func = obj::equals(a);       // 메소드 참조
```

## 생성자 참조
단순히 객체를 생성하고 반환하는 람다 표현식은 생성자 참조로 변환할 수 있다.
```
// (a) -> {return new Object(a);}
Object::new;
```
배열을 생성하는 경우
```
Function<Integer,double[]> func1 = a -> new double[a]; // 람다 표현식
Function<INteger,double[]> func2 = double[]::new;      // 생성자 참조
```
