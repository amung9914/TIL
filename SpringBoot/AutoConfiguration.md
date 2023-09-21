### Auto Configuration

스프링 부트는 자동 구성(Auto Configuration)이라는 기능을 제공하는데, 일반적으로 자주 사용하는 수  
많은 빈들을 자동으로 등록해주는 기능이다.  
JdbcTemplate , DataSource , TransactionManager 등등 모두 스프링 부트가 자동구성을 제공해서 자동으로 스프링 빈으로 등록된다.

### 자동 구성을 언제 사용하는가?

  
AutoConfiguration 은 라이브러리를 만들어서 제공할 때 사용하고, 그 외에는 사용하는 일이 거의 없다.   
왜냐하면 보통 필요한 빈들을 컴포넌트 스캔하거나 직접 등록하기 때문이다. 하지만 라이브러리를 만들어서  
제공할 때는 자동 구성이 유용하다. 실제로 다양한 외부 라이브러리들이 자동 구성을 함께 제공한다.

---

@AutoConfiguration : 자동 구성을 사용하려면 이 애노테이션을 등록해야 한다.  
자동 구성도 내부에 @Configuration 이 있어서 빈을 등록하는 자바 설정 파일로 사용할 수 있다.  
after = DataSourceAutoConfiguration.class  
자동 구성이 실행되는 순서를 지정할 수 있다. JdbcTemplate 은 DataSource 가 필요하기  
때문에 DataSource 를 자동으로 등록해주는 DataSourceAutoConfiguration 다음에  
실행하도록 설정되어 있다.  
@ConditionalOnClass({ DataSource.class, JdbcTemplate.class })  
IF문과 유사한 기능을 제공한다. 이런 클래스가 있는 경우에만 설정이 동작한다. 만약 없으면 여기 있는  
설정들이 모두 무효화 되고, 빈도 등록되지 않는다.  
@ConditionalXxx 시리즈가 있다. 자동 구성의 핵심이므로 뒤에서 자세히 알아본다.  
JdbcTemplate 은 DataSource , JdbcTemplate 라는 클래스가 있어야 동작할 수 있다.  
@Import : 스프링에서 자바 설정을 추가할 때 사용한다

---

#### 스프링 부트가 제공하는 자동 구성 기능을 이해하려면 다음 두 가지 개념을 이해해야 한다

#### 1\. @Conditional : 특정 조건에 맞을 때 설정이 동작하도록 한다.

같은 소스 코드인데 특정 상황일 때만 특정 빈들을 등록해서 사용하도록 도와주는 기능이 바로 @Conditional 이다.

 ->

이미 부트가 구현체를 만들어놓음. 

아래는 -Dmemory=on 일 때 실행하는 예제

```
@ConditionalOnProperty(name = "memory", havingValue = "on")
```

@ConditionalOnClass , @ConditionalOnMissingClass  
클래스가 있는 경우 동작한다. ConditionalOnMissingClass는 없는 경우 동작 @ConditionalOnBean , @ConditionalOnMissingBean  
빈이 등록되어 있는 경우 동작한다. 나머지는 그 반대  
@ConditionalOnProperty  
환경 정보가 있는 경우 동작한다.  
@ConditionalOnResource  
리소스가 있는 경우 동작한다.  
@ConditionalOnWebApplication , @ConditionalOnNotWebApplication  
웹 애플리케이션인 경우 동작한다.  
@ConditionalOnExpression  
SpEL 표현식에 만족하는 경우 동작한다.

#### 2\. @AutoConfiguration : 자동 구성이 어떻게 동작하는지 내부 원리 이해.

스프링 부트는 다음 경로에 있는 파일을 읽어서 스프링 부트 자동 구성으로 사용한다.  
resources/META-INF/spring/  
org.springframework.boot.autoconfigure.AutoConfiguration.imports

예제)

memory.MemoryAutoConfig
