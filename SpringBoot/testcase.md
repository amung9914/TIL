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

<hr/>
### @Transactional

DB를 이용해서 테스트 후 넣은 데이터를 rollback해서 지워줌.(testcase에 붙었을 때만 이렇게 동작함.)

```

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
```

이렇게 하면 DB의 실제 데이터에 반영이 안됨.

@SpringBootTest : 스프링 컨테이너와 테스트를 함께 실행한다.  
@Transactional : 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고,   
테스트 완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지  
않는다.
