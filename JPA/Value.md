## JPA의 데이터 타입 분류
### 엔티티 타입
- @Entity로 정희하는 객체
### 값타입
- 단순히 값으로 사용하는 자바 기본 타입이나 객체

## 값타입 분류
-기본 타입 : 자바 기본타입(int, double), 래퍼클래스(Integer,Long),String
-임베디드타입(embedded type)
-컬렉션 값 타입(collection value type) -> 실무에서는 이거 대신 일대다 관계를 사용한다.

## 임베디드 타입
- 새로운 값 타입을 직접 정의할 수 있다.
- 주로 기본 값 타입을 모아서 만들어서 복합 값 타입이라고 함
- 임베디드 타입을 포함한 모든 값 타입은 값 타입을 소유한 엔티티에 생명주기를 의존함
- ``@Embeddable`` : 값 타입을 정의하는 곳에 표시
- ``@Embedded`` : 값 타입을 사용하는 곳에 표시
- 기본 생성자 필수

### @AttrieOverride : 속성 재정의
한 엔티티에서 같은 값 타입을 사용하는 경우 컬럼명이 중복되기때문에
@AttributeOverrides, @AttributeOverride를 사용해서 컬럼명 속성을 재정의할 수 있다. 

* 임베디드 타입 같은값 타입을 여러 엔티티에서 공유하면 위험함!
* 값 타입은 a.equals(b)를 사용해서 동등성 비교를 해야 한다.
  
### **** 값 타입은 정말 값 타입이라 판단될 때만 사용해야한다.
  
엔티티와 값 타입을 혼동해서 엔티티를 값 타입으로 만들면 안됨

식별자가 필요하고, 지속해서 값을 추적, 변경해야 한다면 그것은 값 타입이 아닌 엔티티

## 값 타입 컬렉션 대신 실무에서 쓰는 방법
값 타입 컬렉션을 
#### Entity로 승급시킨다.

```
@Entity
@Getter
@Table(name = "ADDRESS")
public class AddressEntity {
    @Id @GeneratedValue
    private Long id;
    private Address address;


    public AddressEntity(String city, String street, String zipcode) {
        this.address = new Address(city, street, zipcode);
    }

    public AddressEntity() {}
}
```

address는 값타입으로 만들었다. (position, x.y좌표 정도 할때만 사용한다)(setter를 private으로 만들어서 불변성 확보)

이때 EqualsAndHashCode 꼭 붙여야

```
@Embeddable @EqualsAndHashCode
public class Address {
    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public Address() {

    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    private void setCity(String city) {
        this.city = city;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
```

---

## 실제 실무에서 활용 예시

```

@Embeddable
@EqualsAndHashCode
public class Address {
    //공통적인 룰 사용
    @Column(length = 10)
    private String city;
    @Column(length = 20)
    private String street;
    @Column(length = 5)
    private String zipcode;
    
    //비즈니스적인 메소드
    public String fullAddress(){
        return getCity()+" "+getStreet()+" "+getZipcode();
    }

    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
    
    public boolean isValid(){
        //있어없어 검증할때 쓰는 메소드 만듦
    }
```
