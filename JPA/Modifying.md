## SpringDataJPA - 벌크성 수정 쿼리

Dirty Checking으로 여러 건 수정시 건마다 쿼리 발생함.

벌크성 수정 쿼리를 이용하면 한 번의 쿼리로 대량의 데이터를 수정할 수 있다.

#### @Modifying 어노테이션 사용

Repository 수정 메서드에 @Modifying 을 붙여준다(안붙이면 에러 발생)

```
//벌크수정쿼리
@Modifying
@Query("update Member m set m.age = m.age + 1 where m.age >= :age")
int bulkAgePlus(@Param("age") int age);
```

test 코드 예제)

```
@Test
public void bulkUpdate() throws Exception {
    // given
    memberRepository.save(new Member("member1",10));
    memberRepository.save(new Member("member2",19));
    memberRepository.save(new Member("member3",20));
    memberRepository.save(new Member("member4",21));
    memberRepository.save(new Member("member5",40));

    // when
    int resultCount = memberRepository.bulkAgePlus(20);
    // then
    assertThat(resultCount).isEqualTo(3);
}
```

#### **\* 벌크연산 주의할 점** : 

#### 벌크연산 후 영속성 컨텍스트를 이용해서 작업 진행 시 문제 발생.

#### 영속성 컨텍스트를 무시하고 DB에 쿼리를 실행하기 때문에, 꼭 벌크연산 후 영속성 컨텍스트를 초기화를 하고 작업해야한다.

방법A) EntityManager의 flush(), clear() 실행

```
memberRepository.save(new Member("member1",10));
memberRepository.save(new Member("member2",19));
memberRepository.save(new Member("member3",20));
memberRepository.save(new Member("member4",21));
memberRepository.save(new Member("member5",40));

// when
int resultCount = memberRepository.bulkAgePlus(20);
entityManager.flush(); // 변경되지 않은 내용 DB 반영
entityManager.clear(); // 영속성 컨텍스트 내의 내용 초기화


List<Member> result = memberRepository.findByUsername("member5");
Member member5 = result.get(0);
System.out.println("member5 = " + member5);
```

영속성 컨텍스트 초기화하지 않으면, DB에는 41살, 영속성컨텍스트에는 40살, 

같은 트랜잭션내에서 작업 시 문제발생.

**방법B) @Modifying(clearAutomatically = true)사용**

쿼리 나간 후 clear 과정 **자동**으로 해줌

```
//벌크수정쿼리
@Modifying(clearAutomatically = true)
@Query("update Member m set m.age = m.age + 1 where m.age >= :age")
int bulkAgePlus(@Param("age") int age);
```

아무래도 벌크연산 하고 바로 트랜잭션 끝나는게 제일 좋을지도...
