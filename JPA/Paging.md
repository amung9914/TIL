## SpringDataJPA - 페이징을 쉽게 하는 법

#### Page 객체

Repository 인터페이스에 Page<T> 메서드 이름(Pageable 변수이름)을 작성한다.

```

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 페이징
    Page<Member> findByAge(int age, Pageable pageable);
```

SpringDataJPA로 페이징 처리를 하면 페이징 계산을 할 필요가 없다. 이미 구현되어있다,.

메서드 호출 시 Pageable 구현체를 매개변수로 넣어준다.

**\*Page 인덱스가 1이 아니라 0부터 시작함. 유의**

아래는 test 코드이다.

```
int age = 10;

// SPRING DATA JPA는 page index가 1이 아니라 0부터 시작한다 (PageRequest = Pageable의 구현체)
PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

// when
Page<Member> page = memberRepository.findByAge(age, pageRequest);
```

Page 객체의 주요 메서드: 

getContent() 메서드 : 현재 페이지의 데이터 목록을 반환

getTotalElements() : 전체 데이터 수 반환

getNumber() : 현재 페이지 번호 반환

getTotalPages() : 총 페이지 수 반환

isFirst() :  현재 페이지가 첫번째 페이지인지 여부 반환

hasNext() : 다음 페이지가 존재하는지 여부 반환

```
// then
List<Member> content = page.getContent();
long totalElements = page.getTotalElements();
for (Member member : content) {
    System.out.println("member = " + member);
}
System.out.println("totalElements = " + totalElements);

assertThat(content.size()).isEqualTo(3);
assertThat(page.getTotalElements()).isEqualTo(5);
assertThat(page.getNumber()).isEqualTo(0); // 현재 페이지 번호 가져옴
assertThat(page.getTotalPages()).isEqualTo(2); // 총 페이지
assertThat(page.isFirst()).isTrue(); // 첫번째 페이지인가
assertThat(page.hasNext()).isTrue(); // 다음 페이지가 있는가
```

#### 실제 컨트롤러 사용 예) 

기본 JpaRepository메서드에 pageable 매개변수로 던져도 됨

DTO로 반환하는거 주의하기!

```
// 예시1 : localhost:8080/members?page=0&size=3 으로 GET 요청시 injection해줌
// 예시2 : localhost:8080/members?page=0&size=3&sort=id,desc&sort=username,desc
// application.yml에 디폴트 설정 변경 시 글로벌 설정도 가능 또는 @PageableDefault 사용
@GetMapping("/members")
public Page<MemberDto> list(Pageable pageable){
    Page<Member> page = memberRepository.findAll(pageable);
    Page<MemberDto> map = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));
    return map;
}
```

MemberDto생성자에 Member 엔티티가 들어갈 수 있으면 아래와 같이 코드 단축가능

```
@GetMapping("/members")
public Page<MemberDto> list(@PageableDefault(size = 5) Pageable pageable){
    Page<Member> page = memberRepository.findAll(pageable);
    return page.map(MemberDto::new);
}
```

메서드에 디폴트 설정을 하고 싶을 때: @PageableDefault 사용

```
@GetMapping("/members")
public Page<MemberDto> list(@PageableDefault(size = 5) Pageable pageable){
    Page<Member> page = memberRepository.findAll(pageable);
    Page<MemberDto> map = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));
    return map;
}
```

#### \* 실무에서 TotalCount 쿼리가 성능이슈가 발생할 가능성이 있음.

쿼리가 복잡해지면 count 쿼리를 분리해야함(성능테스트시 성능이 안나오는 경우가 있음)

해결법 : 카운트 쿼리 분리

예제) left outer join 및 where 조건이 없는 경우 

```
// 카운트 쿼리를 분리한다 (left outer join인 경우 join 안해도 카운트 결과는 같음)
@Query(value = "select m from Member m left join m.team t",
        countQuery = "select count(m.username) from Member m")
Page<Member> findByAge(int age, Pageable pageable);
```

Sort 조건도 복잡해지면 안풀리는데 value에 같이 직접 작성해주면 됨.

#### \*API 변환할 때 사용하는 DTO로 쉽게 변환해보기(map 활용)

```
// when
Page<Member> page = memberRepository.findByAge(age, pageRequest);

// Dto로 변환된 값.
Page<MemberDto> toMap = page.map(m -> new MemberDto(m.getId(), m.getUsername(), null));
```

컨트롤러에서 Page<MemberDto>로 반환가능(JSON으로 변환됨)

---

#### Slice 객체

데이터 목록 가져올 때, 1개 더 가져와서 1개 있으면 \[더보기\] 만들어서 누르면 추가로 호출하기

이런거 구현할 때 사용함. (totalCount는 가져오지 않음.주의)

Repository 에 메서드 생성

```
Slice<Member> findByAge(int age, Pageable pageable);
```

아래와 같이 사용 가능

```
// when
Slice<Member> page = memberRepository.findByAge(age, pageRequest);

// then
List<Member> content = page.getContent();

for (Member member : content) {
    System.out.println("member = " + member);
}

assertThat(content.size()).isEqualTo(3);
assertThat(page.getNumber()).isEqualTo(0); // 현재 페이지 번호 가져옴
assertThat(page.isFirst()).isTrue(); // 첫번째 페이지인가
assertThat(page.hasNext()).isTrue(); // 다음 페이지가 있는가
```

---

본 포스팅은 김영한의 실전! 스프링 데이터 JPA(인프런)를 참고하였습니다
