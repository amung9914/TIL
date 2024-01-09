반환타입의 유연성 : Spring Data JPA가 반환타입을 유연하게 지원해준다.

```
public interface MemberRepository extends JpaRepository<Member, Long> {


    // Spring Data JPA가 반환타입을 유연하게 지원해줌.
    List<Member> findListByUsername(String username); // 컬렉션
    Member findMemberByUsername(String username); // 단건
    Optional<Member> findOptionalByUsername(String username); // 단건 Optional
```

주의할 점: List로 반환하는 경우 DB에 일치하는 데이터가 없을때, List가 null이 아니라 비어있는 List를 반환한다.

```

@Test
public void returnType() throws Exception {
    // given
    Member m1 = new Member("AAA", 10);
    Member m2 = new Member("BBB", 20);
    memberRepository.save(m1);
    memberRepository.save(m2);

    List<Member> aaa = memberRepository.findListByUsername("dasfadf");
    System.out.println("aaa = " + aaa.size());

}
```

출력값 : aaa = 0

단 건으로 조회하는 경우 결과값이 없으면 null을 반환한다(JPA와 달리 Exception이 발생하지 않음)

```
Member findMember = memberRepository.findMemberByUsername("dasfadf");
System.out.println("findMember = " + findMember);
```

출력값 : findMember = null

null인지 아닌지 모를때는 **Optional**을 활용하는게 좋아보인다.
