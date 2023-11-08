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

<hr/>

## 스프링 부트 스타터

스프링 부트 스타터는 라이브러리 자체를 고르는 고민을 해결해준다.

build.gradle - dependencies 에 아래처럼 기재한다.
```
dependencies {
 //3. 스프링 부트 스타터
 implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

### 스프링 부트 스타터 - 자주 사용하는 것 위주

spring-boot-starter : 핵심 스타터, 자동 구성, 로깅, YAML
spring-boot-starter-jdbc : JDBC, HikariCP 커넥션풀
spring-boot-starter-data-jpa : 스프링 데이터 JPA, 하이버네이트
spring-boot-starter-data-mongodb : 스프링 데이터 몽고
spring-boot-starter-data-redis : 스프링 데이터 Redis, Lettuce 클라이언트
spring-boot-starter-thymeleaf : 타임리프 뷰와 웹 MVC
spring-boot-starter-web : 웹 구축을 위한 스타터, RESTful, 스프링 MVC, 내장 톰캣
spring-boot-starter-validation : 자바 빈 검증기(하이버네이트 Validator)
spring-boot-starter-batch : 스프링 배치를 위한 스타터

스프링 부트 스타터의 전체 목록은 다음 공식 메뉴얼을 참고하자.
https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.buildsystems.starters

외부 라이브러리의 버전을 변경하고 싶을 때 다음과 같은 형식으로 편리하게 변경할 수 있다.
``ext ['tomcat.version'] = '10.1.4' ``
스프링 부트가 관리하는 외부 라이브러리 버전 변경에 필요한 속성 값

https://docs.spring.io/spring-boot/docs/current/reference/html/dependencyversions.html#appendix.dependency-versions.properties
스프링 부트가 관리하는 외부 라이브러리의 버전을 변경하는 일은 거의 없다. 하지만 아주 가끔 문제가
발생하기도 하므로 알아두자
