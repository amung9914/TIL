### @Entity
@Entity가 붙은 클래스는 JPA가 관리. JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수
주의점 : 
- 기본생성자 필수(public 또는 protected 생성자)
- final 클래스, enum, interfacem inner클래스 사용 x
- 저장할 필드에 final 사용 x
* protected는 같은 패키지 또는 자식 클래스에서 사용할 수 있다. 다른 패키지의 자식도 접근허용.
* protected로 일일이 치는 것 보다는 롬복으로 @NoArgsConstructor(access = AccessLevel.PROTECTED)를
* 엔티티 클래스 위에 선언하면 간략하게 protected 생성자를 생성할 수 있다.

## @Entity 속성 정리
### @Table : 엔티티와 매핑할 테이블을 지정한다.
@Table은 엔티티와 매핑할 테이블 지정

![image](https://github.com/amung9914/TIL/assets/137124338/f04a073f-56cb-46b5-87a9-e1145c9ae504)

## 데이터베이스 스키마 자동생성(persistence.xml)
DDL을 애플리케이션 실행 시점에 자동생성한다.
이렇게 생성된 DDL은 개발 장비에서만 사용하는 것을 권장함(적절히 다듬어서 사용해라~~)
![image](https://github.com/amung9914/TIL/assets/137124338/fc7fab48-ac98-4e0f-8193-45ee77565eb9)
create-drop : 테스트 케이스 깔끔하게 할 때 사용
```
persistence.xml에 아래 내용 적기

<property name="hibernate.hbm2ddl.auto" value="create-drop" />
```

#### -(운영에서는 절대 create,create-drop,update 사용하면 안됨!)

테스트서버에서 validate는 괜찮다. (create하면 처음에 drop하고 create하니까 난리남, update도 alter나가서 rock걸림!!! 그럼 장애남....)

개발 초기 단계에서는 create또는 update 괜찮음...그래도 가급적이면 쿼리 직접 쓰는걸 권장.

직접 쿼리 짜는거 귀찮으니까 create 문 꼼꼼히 따져보고 쿼리 알맞게 수정해서 쓰는건 ok~~

## 필드와 컬럼 매핑

### 매핑 어노테이션 정리
![image](https://github.com/amung9914/TIL/assets/137124338/d379700d-7c35-4f39-9caa-324a06f2a516)
@Transient : 캐시데이터 넣을때, 메모리에서만 사용할 때 사용(매핑무시)
* LOB : TEXT,그래픽,이미지,비디오,사운드 등 구조화되지 않은 대형 데이터를 저장 목적
* BLOB : 바이너리 데이터 보관, 이미지, 오디오파일, 비디오 등..
* CLOB : 문자 대형 객체, 많은 양의 문자 데이터를 저장한다.
*        보통 4000문자 이상을 저장하는 경우에 VARCHAR2대신 이용(VARCHAR2열은 최대 4000문자 저장)
*        Oracle Server는 CLOB와 VARCHAR2 사이에 암시적 변환을 수행함.
![image](https://github.com/amung9914/TIL/assets/137124338/37feed14-42e4-4392-a170-d64b9414b5af)
#### @Eunmerated
자바 enum타입을 매핑하는 경우
value는 EnumType.STRING 사용을 해주자!!!
*주의 ORDINAL 사용 xxxxx

#### @Temporal
날짜 타입을 매핑할 때 사용(java.util.Date, java.util.Calender)
참고로 LocalDate, LocalDateTime을 사용할 때는 생략 가능하다.

### 기본키
@Id
@GeneratedValue : 자동생성
#### 권장하는 식별자 전략 : Long형 + 대체키 + 키 생성전략 사용
- 기본 키 제약조건:null아님, 유일, 변하면 안된다.(자연키는 해당값 찾기 어렵다(주민등록번호도 적절x))

## 상속관계 테이블 설계 전략

#### 조인 전략을 기본으로
​
확장할 일도 없을거같고 데이터도 넘 단순하다 
#### -> 싱글 테이블 전략
​
근데, 비즈니스적으로 중요하고 복잡하다 
#### -> 조인 테이블 전략
​
<hr/>​
@Inheritance(strategy=InheritanceType.JOINED : 조인전략
​
SINGLE\_TABLE : 단일 테이블 전략
​
@DiscriminatorColumn(name="DTYPE")
​
@DiscriminatorValue("XXX")
​<hr/>

단일 테이블 전략 단점 : 자식 엔티티가 매핑한 컬럼은 모두 null 허용

## @MappedSuperclass

id,name 등 공통 매핑 정보가 필요할 때 사용, 엔티티가 아님에 주의!

단순히 엔티티가 공통으로 사용하는 매핑정보를 모으는 역할

```
@MappedSuperclass
public abstract class BaseEntity {
    private String createBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
```

속성만 상속받을 때 쓰는 MappedSuperclass

필드 예시는 운영 시 기본으로 깔려야하는 녀석들. 나중에 Spring Data 쓰면 4가지 다 어노테이션으로 쉽게 입력가능~~

## 프록시

지연로딩 LAZY를 사용해서 프록시로 조회. 우리는 얘가 엔티티인지 프록시인지 알 수 없다. 

따라서 JPA에서는 ==비교 대신

#### instance of 사용하기~(프록시객체랑 그냥 객체 == 비교하면 false나옴)

(instanceOf 연산자는 객체가 어떤 클래스인지, 어떤 클래스를 상속받았는지 확인하는데 사용하는 연산자)

![image](https://github.com/amung9914/TIL/assets/137124338/3e2325c9-a843-4d85-94e3-5db103af7e5b)


```

/* TRUE
Member m1 = em.find(Member.class, member1.getId());
Member m2 = em.find(Member.class, member2.getId());

System.out.println("m1 == m2" + (m1.getClass() == m2.getClass()));
 */


Member m1 = em.find(Member.class, member1.getId());
Member m2 = em.getReference(Member.class, member2.getId());
//false
System.out.println("m1 == m2" + (m1.getClass() == m2.getClass()));
System.out.println("m1 == m2" + (m1 instanceof Member)); // true
System.out.println("m1 == m2" + (m2 instanceof Member)); // true
```

영속성컨텍스트에 찾는 이미 엔티티가 있으면 getReference해도 엔티티로 가져옴

```
Member m1 = em.find(Member.class, member1.getId());
System.out.println("m1 = " + m1.getClass());

Member reference = em.getReference(Member.class, member1.getId());
System.out.println("reference = " + reference.getClass());

// JPA는 영속성 컨텍스트에 있고, PK가 같은 경우 == 비교하면 true를 보장해준다.
System.out.println("a == a:"+ (m1 == reference));
```

```
// JPA는 영속성 컨텍스트에 있고, PK가 같은 경우 == 비교하면 true를 보장해준다.
System.out.println("a == a:"+ (m1 == reference));
```

심지어 반대로 하면 ....프록시 가져옴

```
Member reference = em.getReference(Member.class, member1.getId());
System.out.println("reference = " + reference.getClass());

Member m1 = em.find(Member.class, member1.getId());
System.out.println("m1 = " + m1.getClass());

// JPA는 영속성 컨텍스트에 있고, PK가 같은 경우 == 비교하면 true를 보장해준다.
System.out.println("a == a:"+ (m1 == reference));
```

---

실무시 반드시 만나는 예외가 있다...

could not initialize proxy

준영속상태에서 프록시 접근시 발생.

---

### 실무에서는 즉시로딩 쓰면 안돼!  
### @ManyToOne, @OneToOne은 기본이 즉시로딩 -> LAZY로 설정하기
```
@ManyToOne(fetch = FetchType.LAZY)
```
## 영속성 전이 : CASCADE

영속성 전이는 엔티티를 영속화할 때 연관된 엔티티도 함께 영속화하는 편리함을 제공해준다

(ex.부모 엔티티 저장시에 자식 엔티티도 함께 저장)

```

@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
private List<Child> childList = new ArrayList<>();
```

cascade 속성을 사용할 수 있는 경우:

하나의 부모가 자식들을 관리할때,

ex)parent 말고 member가 child를 알게되면 쓰면 안된다.... 단일 엔티티에 완전히 종속적일때(소유자가 하나일때), life cycle 동일할때(등록,삭제)

ex 게시판-첨부파일 경로(다른 엔티티랑 관련 있으면 쓰면 안됨. 소유자가 하나일때만 사용)

### 고아객체제거 : 부모 엔티티와 연관관계가 끊어진 자식 엔티티를 자동으로 삭제

`orphanRemoval = true`

```
Parent parent1 = em.find(Parent.class, id); 
parent1.getChildren().remove(0);
//자식 엔티티를 컬렉션에서 제거
```

고아 객체제거도 참조하는 곳이 하나일 때 사용해야함. (특정 엔티티가 개인 소유하는 경우)

@OneToOne, @OneToMany만 가능

\* 부모 엔티티 삭제 시
``CascadeType.REMOVE``와 ``orphanRemoval = true``는 부모 엔티티를 삭제하면 자식 엔티티도 삭제한다.
- 부모 엔티티에서 자식 엔티티를 제거하면 
``CascadeType.REMOVE``는 자식 엔티티가 그대로 남아 있다.
``orphanRemoval = true``는 자식 엔티티를 제거한다.

두 경우 모두 자식 엔티티에 딱 하나의 부모 엔티티가 연관되어 있는 경우에만 사용해야한다.

## 영속성 전이 + 고아 객체

두 옵션을 모두 활성화하면 부모 엔티티를 통해서 자식의 생명주기를 관리할 수 있다.

도메인 주도 설계(DDD)의 Aggregate Root개념을 구현할 때 유용
