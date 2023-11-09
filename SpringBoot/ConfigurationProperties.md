# 외부설정 사용 @ConfigurationProperties, 자바 빈 검증기 @Validated, @Profile(프로필에따른 스프링 빈 등록)

## 외부설정 사용 @ConfigurationProperties,

외부설정을 사용하는 클래스에 @Data 대신

생성자 주입으로 setter 제외되도록, 구현 가능.

```
MyDataSourcePropertiesV1 은 스프링 빈으로 등록된다. 그런데 Setter 를 가지고 있기 때문에 누군가
실수로 값을 변경하는 문제가 발생할 수 있다. 여기에 있는 값들은 외부 설정값을 사용해서 초기에만
설정되고, 이후에는 변경하면 안된다. 이럴 때 Setter 를 제거하고 대신에 생성자를 사용하면 중간에
데이터를 변경하는 실수를 근본적으로 방지할 수 있다
```


ConfigurationProperties 장점  
1\. 외부 설정을 객체로 편리하게 변환해서 사용할 수 있다.  
2\. 외부 설정의 계층을 객체로 편리하게 표현할 수 있다.외부 설정을 타입 안전하게 사용할 수 있다.

(스프링이 외부 설정의 묶음 정보를 객체로 변환하는 기능을 제공한다, 외부설정을 자바코드로 관리할 수 있다.)  
3\. 검증기를 적용할 수 있다.

```
package hello.datasource;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.Duration;
import java.util.List;

@Getter
@ConfigurationProperties("my.datasource")
public class MyDataSourcePropertiesV2 {

    private String url;
    private String username;
    private String password;
    private Etc etc;

    public MyDataSourcePropertiesV2(String url, String username, String password, @DefaultValue Etc etc) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.etc = etc;
    }

    @Getter
    public static class Etc{
        private int maxConnection;
        private Duration timeout;
        private List<String> options;

        public Etc(int maxConnection, Duration timeout,
                   @DefaultValue("DEFAULT") List<String> options){
            this.maxConnection = maxConnection;
            this.timeout = timeout;
            this.options = options;
    }


}
```

생성자를 만들어 두면 생성자를 통해서 설정 정보를 주입한다.

``@Getter`` 롬복이 자동으로 getter 를 만들어준다.

``@DefaultValue`` : 해당 값을 찾을 수 없는 경우 기본값을 사용한다.

``@DefaultValue`` Etc etc : etc 를 찾을 수 없을 경우 Etc 객체를 생성하고 내부에 들어가는 값은 비워둔다. ( null , 0 ) 

``@DefaultValue("DEFAULT")`` List options : options 를 찾을 수 없을 경우 DEFAULT 라는 이름의 값을 사용한다

\*참고 : 

스프링 부트 3.0 부터는 생성자가 하나일 때는 생략할 수 있다.

생성자가 둘 이상인 경우에는 사용할 생성자에 @ConstructorBinding 애노테이션을 적용하면 된다

## 자바 빈 검증기(java bean validation) @Validated
외부설정 사용 - @ConfigurationProperties 검증

외부설정을 검증해서 사용할 수 있다(값이 정해진 범위에 있는지, 값이 존재하는지 조건 넣어서 확인 가능)

값이 검증에 통과하지 않으면 애플리케이션 로딩 시점에 확인할 수 있다.

가장 좋은 예외는 컴파일 예외, 그리고 애플리케이션 로딩 시점에 발생하는 예외이다.

1. build.gradle에 ``spring-boot-starter-validation``추가<br/> ``implementation 'org.springframework.boot:spring-boot-starter-validation'``
   
3. ``@Validated``를 ``@ConfigurationProperties`` 와 함께 사용해서 작성한다.


@NotEmpty url , username , password 는 항상 값이 있어야 한다. 필수 값이 된다.

@Min(1) @Max(999) maxConnection : 최소 1 , 최대 999 의 값을 허용한다.

@DurationMin(seconds = 1) @DurationMax(seconds = 60) : 최소 1, 최대 60초를 허용한다.

<hr/>
실행 클래스는 아래와 같이 작성하였다

```
package hello;

import hello.config.MyDataSourceConfigV3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(MyDataSourceConfigV3.class)
@SpringBootApplication(scanBasePackages="hello.datasource")
public class ExternalReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalReadApplication.class, args);
    }
```

## @Profile(각 환경별로 등록할 스프링 빈 분리)
@Profile 을 사용하면 각 환경 별로 외부 설정 값을 분리하는 것을 넘어서, 등록되는 스프링 빈도 분리할 수 있다

#### 만약에 프로필에 따라 빈 등록이 변경되어야 한다면?

예를 들어서  기능을 붙여야 하는데, 로컬 개발 환경에서는 실제 결제가 발생하면 문제가 되니 가짜 결제 기능이 있는 스프링 빈을 등록하고, 운영 환경에서는 실제 결제 기능을 제공하는 스프링 빈을 등록한다고 가정해보자  
  
config 파일에서 @Profile 사용  
  
```

@Slf4j
@Configuration
public class PayConfig {

    @Bean
    @Profile("default")
    public LocalPayClient localPayClient(){
        log.info("LocalPayClient 빈 등록");
        return new LocalPayClient();
    }

    @Bean
    @Profile("prod")
    public ProdPayClient prodPayClient(){
        log.info("ProdPayClient 빈 등록");
        return new ProdPayClient();
    }
}
```

ApplicationRunner 인터페이스를 사용하면 스프링은 빈 초기화가 모두 끝나고 애플리케이션 로딩이 완료되는 시점에 run(args) 메서드를 호출해준다.

(테스트시 사용되는 클래스 예시)

```
package hello.pay;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderRunner implements ApplicationRunner {

    private final OrderService orderService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        orderService.order(1000);
    }
}
```
