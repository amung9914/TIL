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
