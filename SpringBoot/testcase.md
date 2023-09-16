### Test code 작성

```
import static org.assertj.core.api.Assertions.*;
```

assertj : 값의 비교를 도와줌.

예제)

```
assertThat(member).isEqualTo(result);
```

```
@Test
public void save(){
    Member member = new Member();
    member.setName("spring");

    repository.save(member);

    Member result = repository.findById(member.getId()).get();
    assertThat(member).isEqualTo(result);

}
```

예제2) - 에러나는 경우

```

@Test
public void findByName(){
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    Member result = repository.findByName("spring2").get();

    assertThat(result).isEqualTo(member1);
}
```

result는 member2라서 에러 발생

### 테스트 작성시 주의점

여러 테스트가 순서 상관없이 실행되어야함.

따라서 테스트 수행 후 저장소나 공용데이터를 지워주어야 함.

```
// 메소드 실행이 끝날때마다 동작하는 콜백메소드
@AfterEach
public void afterEach(){
    repository.clearStore();

}
```

이 경우에는 

MemoryMemberRepository.java에 Map을 비워주는 메소드를 만들어서 테스트메소드 실행 후 비워줌.

```
public void clearStore(){
    store.clear();
}
```
