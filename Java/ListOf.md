### 자바에서 리스트를 만드는 방식은 대표적으로 3가지 정도 존재한다.

```java
public static void main(String[] args) {
    // 생성자 방식
    List<Number> arrayList = new ArrayList<>();
    arrayList.add(1);
    arrayList.add(2);
    arrayList.add(3);

    // Arrays 클래스의 asList 메서드
    List<Number> asList = Arrays.asList(1, 2, 3);

    // List 인터페이스의 of 메서드 (jdk 9)
    List<Number> listOf = List.of(1, 2, 3);
}
```

하나는 생성자로 직접 리스트 객체를 인스턴화 시키는 것이고, 

좀 더 간편하게 원소가 들은 리스트를 한방에 생성하기 위해 별도로 Arrays.asList() 와 List.of() 메서드를 지원한다.

Arrays.asList()는 배열을 리스트로 변환하는 메서드이고, 

List.of()는 자바9 부터 지원하는 List 인터페이스의 디폴트 메서드인 정적 팩토리 메서드Visit Website이다. 

이 둘은 반환하는 결과는 같은 것 같아 보이지만 섬세한 몇가지 차이점이 존재한다.

Arrays.asList는 변경이 가능하기 때문에 Thread-Safe 하지 않음. List.of는 완전 불변하기 때문에 쓰레드에 안전함.

Arrays.asList()는 null 요소를 허용하고 List.of()는 null 요소를 허용하지 않음

메모리는 List.of 가 덜 사용. 따라서 반 불변인 Arrays.asList 보단 완전 불변인 List.of 사용 권장

Arrays.asList , List.of 모두 변경할 수 없기 때문에 별도로 Collections을 생성해서 요소의 값을 복사하여 사용해야 한다.

출처: https://inpa.tistory.com/entry/JAVA-☕-ArraysasList-와-Listof-차이-한방-정리 [Inpa Dev 👨‍💻:티스토리]
