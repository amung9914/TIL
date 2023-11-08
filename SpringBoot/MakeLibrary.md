## 라이브러리를 만들어보자

여러분이 만든 실시간 자바 Memory 조회 기능이 좋다고 소문이 나서, 여러 프로젝트에서 사용하고 싶어한다.<br/>
이 기능을 여러곳에서 사용할 수 있도록 라이브러리로 만들어보자.<br/>
참고로 라이브러리를 만들 때는 스프링 부트 플러그인 기능을 사용하지 않고 진행한다 <br/>

### 자동구성 라이브러리 만들기

### 1. Config 클래스에 ``AutoConfiguration``, ``@ConditionalOnProperty`` 추가
- @AutoConfiguration : 스프링 부트가 제공하는 자동 구성 기능을 적용할 때 사용하는 애노테이션이다
- @ConditionalOnProperty : 라이브러리를 가지고 있더라도 상황에 따라서 해당 기능을 켜고 끌 수 있게 유연한 기능을 제공한다

### 2. 자동 구성 대상 지정 (중요)
 - src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports 파일생성
 - 해당 파일에는 앞서 만든 자동 구성을 패키지를 포함해서 지정해준다. (예시에서는 memory.MemoryAutoConfig라고 적어줬음)
 - 스프링부트는 시작 시점에 이 파일의 정보를 먼저 읽어서 내부에 있는 MemoryAutoConfig가 자동으로 실행됨.

### 3. 라이브러리 사용하기
- 프로젝트 하위 폴더 libs를 생성해서 jar 파일을 복사한다.
- build.gradle에 dependencies에 파일을 추가한다.
- 예제
```
dependencies {
 implementation files('libs/memory-v2.jar') //추가
```
   
