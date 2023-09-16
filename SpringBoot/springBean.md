### Spring Bean

#### 1번째 방법:  컴포넌트 스캔

```
@SpringBootApplication
```

이 붙어있는 클래스가 위치한 패키지 및 하위 패키지에서

@Controller,@Service, @Repository @Component  어노테이션 사용 시 자동으로 컴포넌트 스캔.

#### 2번째 방법: 자바코드로 직접 작성

```

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
```

메인메소드 있는 경로 위치에서 새로 클래스 파일 생성

단, 컨트롤러는 이 방식으로 못하고 @Controller 해주어야함.
