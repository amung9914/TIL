## 함수형 인터페이스

함수형 인터페이스는 추상 클래스와 달리 단 하나의 추상 메소드만을 가져야한다.

(하지만 default와 static 메소드의 개수에는 제약이 없다)

일반 인터페이스로 인식하는 것을 방지해주기 위해 @FunctionalInterface를 선언해준다.

자바에서는 기본적으로 4가지의 함수형 인터페이스를 지원하고 있다.

-   Supplier<T> : 매개변수 없이 반환값 만을 갖는다 (T get()를 추상 메소드로 가짐)
-   Consumer<T> : 객체 T를 매개변수로 받아서 사용하며, 반환값은 없음(void accept(T t)를 추상메소드로 가짐)
-   Function<T> : T를 매개변수로 받아서 처리한 후 R로 반환함 (R apply(T t)를 추상메소드로 가짐)
-   Predicate<T> : T를 매개변수로 받아 처리한 후 Boolean을 반환한다 (Boolean test(T t)를 추상메서드로 가짐)
