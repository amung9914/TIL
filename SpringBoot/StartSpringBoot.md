## 스프링 부트를 실행해보자
스프링부트를 실행할때는 자바 main()메서드에서 SpringApplication.run()을 호출한다.
이때, 메인 설정 정보를 넘겨주어야 한다. 보통 ``@SpringBootApplication`` 이 붙어 있는 현재 클래스를 지정해준다.

```
@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

}

```
* ``@SpringBootApplication`` 안에는 컴포넌트 스캔 기능이 설정되어있다. 현재 패키지와 그 하위 패키지 모두를 컴포넌트 스캔함.
