애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색 조건이 포함된 SQL이 필요하다.

JPQL은 SQL과 문법이 유사함. 엔티티 객체를 대상으로 쿼리.

## JPQL문법

select_문 :: == <br/>
  select_절<br/>
  from_절<br/>
  [where_절]<br/>
  [groupby_절]<br/>
  [having_절]<br/>
  [orderby_절]<br/>

update_문 :: = update_절[where_절]<br/>
delete_문 :: = delete_절[where_절]<br/>

엔티티와 속성은 대소문자 구분o(Member, age)
JPQL키워드는 대소문자 구분x(select)
엔티티 이름 사용, 테이블 이름이 아님.
#### 별칭은 필수(Member m)

### TypeQuery, Query
- TypeQuery : 반환 타입이 명확할 때 사용
- Query : 반환 타입이 명확하지 않을 때 사용
  
            TypedQuery<Member> query = 
                    em.createQuery("SELECT m FROM Member m", Member.class);
            Query query = 
                    em.createQuery("select m.username, m.age from Member m");


### 결과 조회 API
-query.getResultList() : 결과가 하나 이상일때, 리스트 반환, 결과가 없으면 빈 리스트 반환
-query.getSingleResult() : 결과가 정확히 하나, 객체 반환, 결과가 없어도 에러, 둘 이상이어도 에러,

### 파라미터 바인딩
            Query query = em.createQuery("select m from Member m where m.username=:username");
                        query.setParameter("username","호호");

검색예시

            String jpql = "select m from Member m where m.name like '%hello%'";
            List<Member> result = em.createQuery(jpql,Member.class)
                    .getResultList();

검색예시2


            String jpql = "select m from Member m where m.age > 18";
                      List<Member> result = em.createQuery(jpql,Member.class)
                              .getResultList();

![image](https://github.com/amung9914/TIL/assets/137124338/f3db3f6a-ed68-44e7-b1ed-9eec4cd623b1)

### 프로젝션 : select절에 조회할 대상을 지정하는 것
대상 : 
- select m from Member m -> 엔티티 프로젝션
- select m.team from Member m -> 엔티티 프로젝션
- select m.address from Member m ->임베디드 타입 프로젝션<br/>
  DISTINCT로 중복 제거 가능

embedded타입은 from절에 기준 엔티티를 적어줘야함.

```
List<Address> result = em.createQuery("select o.address from Order o", Address.class)
        .getResultList();
```
### 프로젝션 - 여러 값 조회
ex) select m.username, m.age from Member m <br/>
방법 3가지
1. Query타입으로 조회
2. Object[]타입으로 조회
3. new 명령어로 조회
- 단순 값을 DTO로 바로 조회한다.
- 패키지명을 포함한 전체 클래스명을 입력해야함.
- 순서와 타입이 일치하는 생성자가 필요하다

전체 클래스명 입력하는거 귀찮으면 queryDsl 써야댐. 필드 순서 생성자대로. 틀리면 안됨

```
List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m",MemberDTO.class)
        .getResultList();
```
**
toString()만들때 주의) 무한 루프 만들 수 있어서 양방향 컬럼은 지움.

```
@Entity @Getter @Setter
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private int age;
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
```
## 페이징API
- ``setFirstResult(int startPosition)`` : 조회 시작 위치(0부터시작)<br/>
- ``setMaxResults(int maxResult)`` : 조회할 데이터 수
  
            //페이징 쿼리
            String jpql = "select m from Member m order by m.name desc";
            List<Member> resultList = em.createQuery(jpql,Member.class)
                    .setFirstResult(10)
                    .setMaxResults(20)
                    .getResultList();
## 조인(묵시적 조인보다 명시적 조인이 나중에 쿼리튜닝하기 좋음)
### 내부조인
select m from Member m join m.team t
### 외부조인
select m from Member m left join m.team t
### 세타조인 (=막조인) : 마구잡이 조인
select count(m) from Member m,Team t where m.username = t.name

### 조인 ON 절
활용1. 조인 대상 필터링

``
select m,t from Member m left join m.team t on t.name = 'A'
`` 

활용2. 연관관계 없는 엔티티 외부 조인
``
select m, t from Member m left join Team t on m.username = t.name
``

### 서브쿼리
ex) 나이가 평균보다 많은 회원
```
select m from Member m 
where m.age > (select avg(m2.age) from Member m2
```

### 조건식 - CASE 식

​
```
String query =
        "select " +
                "case when m.age <= 10 then '학생요금' " +
                "     when m.age >=60 then '경로요금' " +
                "     else '일반요금' " +
                "end " +
        "from Member m";
List<String> result = em.createQuery(query, String.class)
        .getResultList();
for (String s : result) {
    System.out.println("s = " + s);
}


```
            String query = "select " +
                    "           case t.name " +
                    "               when '팀A' then '인센티브110%' " +
                    "               when '팀B' then '인센티브120%' " +
                    "               else '인센티브105%' " +
                    "           end " +
                    "       from Team t";

### 조건식 - coalesce : 하나씩 조회해서 null이 아니면 반환, null이면 뒤에 인자값 반환<br/>
``select coalesce(m.username,'이름없는회원')from Member m``

### 조건식 - nullif() : 관리자 이름 숨길때? 쓸 수 있다(두개 값이 같을 때 null 반환)
​
```
String query = "select nullif(m.username, '관리자') as username from Member m";
List<String> result = em.createQuery(query, String.class)
        .getResultList();
for (String s : result) {
    System.out.println("s = " + s);
​
}
```

### JPQL 기본 함수 : 
- concat
- substring
- trim
- lower, upper
- length
- locate
- abs,sqrt,mod
- size,index(jpa용도)

### 사용자 정의 함수 호출

1\. H2Dialect 상속받은 클래스 생성

```
package dialect;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MyH2Dialect extends H2Dialect {
    public MyH2Dialect() {
        registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}
```

2\. persistence.xml에 dialect 경로 수정

```
<property name="hibernate.dialect" value="dialect.MyH2Dialect"/>
```

쿼리 이렇게 써도 하이버네이트가 지원해줌.

```

String query = "select group_concat(m.username) from Member m";
```

---
## 경로표현식
select m.username -> 상태필드<br/>
  from Member m <br/>
  join m.team t -> 단일값 연관 필드<br/>
  join m.orders o -> 컬렉션 값 연관 필드<br/>
where t.name = '팀A' <br/>

특징(묵시적 내부조인은 안좋음. 튜닝을 위해 가급적이면 명시적 조인을 사용할 것.)
- 상태필드 : 단순히 값을 저장하기 위한 필드, 경로탐색의 끝, 탐색x
- 연관필드 : 연관관계를 위한 필드
- 단일값 연관 필드 : @ManyToOne, @OneToOne 대상이 엔티티, 묵시적 내부 조인 발생, 탐색 가능
- 컬렉션 값 연관 필드 : @OneToMany,@ManyToMany 대상이 컬렉션, 묵시적 내부조인 발생, 탐색x<br/>
from 절에서 명시적 조인을 통해 별칭을 얻으면 별칭을 통해 탐색 가능<br/>

## 페치조인 fetch join
JPQL에서 성능 최적화를 위해 제공함.
페치 조인은 객체 그래프를 SQL한번에 조회한다.(즉시로딩)

명시적으로 즉시로딩 하는거라고 볼 수 있음, 프록시 아니고 진짜데이터 들고옴. 주로 조회성 기능할때 사용

            String jpql = "select m from Member m join fetch m.team";
            List<Member> members = em.createQuery(jpql,Member.class)
                    .getResultList();
            for (Member member : members) {
                System.out.println("username = " + member.getUsername());
            }
### 페치 조인과 DISTINCT : 일대 다 조인 시 데이터 뻥튀기 막기
JPQL에서 DISTINCT는 
1. sql에 distinct를 추가함
2. 애플리케이션에서 엔티티 중복을 제거함

```
String query = "select distinct t From Team t join fetch t.members";

List<Team> result = em.createQuery(query, Team.class)
                .getResultList();

for (Team team : result) {
    System.out.println("team = " + team.getName() + " members = " + team.getMembers().size());
    for(Member member : team.getMembers()){
        System.out.println("->member = " + member);
    }
}
```

---

### 페치조인의 한계
- 페치 조인 대상에는 별칭을 줄 수 없다.
- 둘 이상의 컬렉션은 페치조인 할 수 없다.
- 컬렉션을 페치조인하면 페이징API를 사용할 수 없다. 따라서 컬럭션은 페이징처리 시 페치조인이 안된다.
### 이때 해결방안으로 batch size 조절이 있다.(컬렉션 페이징 처리 시 즉시로딩하는 )

글로벌셋팅을해준다(persistence.xml에 해당 값 입력) value값은 1000이하를 권장함.(실무에서 자주 씀)

```
<property name="hibernate.default_batch_fetch_size" value="100" />
```

만일 아래 코드를 작성한 경우

```
String query = "select t From Team t";

List<Team> result = em.createQuery(query, Team.class)
                .setFirstResult(0)
                .setMaxResults(2)
                .getResultList();

System.out.println("result = " + result.size());

for (Team team : result) {
    System.out.println("team = " + team.getName() + " members = " + team.getMembers().size());
    for(Member member : team.getMembers()){
        System.out.println("->member = " + member);
    }
```

batch size 세팅안해주면 team마다 select member쿼리가 나가는데, (쿼리 여러개 나오면 성능 안나옴)

적용하면 쿼리가 테이블 수 만큼 최적화된다. 

---


복잡한 통계자료 만들때는 여러테이블 조인해서 엔티티가 가진 모양이 아닌 전혀 다른 결과를 내야 하는 경우

페치조인보다 일반조인을 사용해서 필요한 데이터만 조회해서 DTO로 반환하는 것이 효과적임.

---

## Named 쿼리 - 정적쿼리
미리 정의해서 이름을 부여해두고 사용하는 JPQL. 
어노테이션이나 xml에 정의한다.

Spring Data JPA에서는 인터페이스 메소드 위에다가 바로 @Query 어노테이션으로 사용가능

\-> 실무에서는 스프링데이터jpa써야겠죠?

### Named 쿼리 장점:

애플리케이션 로딩 시점에 초기화 한 후 재사용,

애플리케이션 로딩시점에 쿼리 검증함..(쿼리 이상하면 QuerySyntaxException오류 발생)

캐시하고 있어서 코스트 절약

```
@Entity 
@NamedQuery(
        name = "Member.findByUsername", // 클래스명.###이 관례임
        query = "select m from Member m where m.username = :username"
)
public class Member {
```

사용 예시

```
List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
        .setParameter("username", "회원1")
        .getResultList();

for (Member member : resultList) {
    System.out.println("member = " + member);

}
```

## 벌크연산 - 쿼리 한 번으로 여러 테이블 로우 변경(엔티티)
재고가 10개 미만인 모든 상품의 가격을 10%상승하려면?
JPA변경감지기능 사용시 UPDATE 쿼리가 100개... 이걸 벌크연산이 해결해줌

insert, update, delete를 지원한다.

#### 주의할 점 : 벌크연산은 영속성 컨텍스트에 작업 아무것도 안하고 직접 DB작업함.
#### 벌크 연산 수행 후 영속성 컨텍스트 초기화해야함.

영속성 컨텍스트가 이미 변수값을 가지고 있는 경우 : 값이 10살으로 되어 있는 경우))

(벌크로 20살 때렸는데, 영속성에는 10살 되어있음 -> 초기화(clear)한 후 다시 가져와야 함.(이때 flush안해도 자동으로 벌크쿼리 날리면 flush됨.)

```

int resultCount = em.createQuery("update Member m set m.age = 20")
        .executeUpdate();

em.clear();
Member findMember = em.find(Member.class, member1.getId());
System.out.println("findMember.getAge() = " + findMember.getAge());//20
System.out.println(member1.getAge());//기존에 영속성에 있는 변수 : 10
```
