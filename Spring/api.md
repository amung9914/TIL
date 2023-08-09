## api  숨기기

- 속성 파일 사용: API 키를 별도의 속성 파일에 저장하고, 해당 파일을 애플리케이션에서 읽어오는 방식을 사용할 수 있습니다. 
- 예를 들어, Spring Boot에서는 application.properties나 application.yml 파일에 키를 저장하고, @Value 어노테이션을 사용하여 값을 주입받을 수 있습니다.

### Spring 기반에서 사용해보기
- 속성 파일 생성: 프로젝트 내에 custom.properties 파일을 생성합니다.
- 이 파일은 프로젝트의 리소스 디렉토리(src/main/resources)에 위치해야 합니다.

- 속성 값 추가: 생성한 속성 파일에 API 키 값을 추가합니다.
```
api.key=mySecretApiKey
```
- 필요한 클래스에서@PropertySource("classpath:custom.properties") 사용
- @Value 어노테이션을 사용하여 속성 값을 주입받을 수 있습니다.

```
@Component
@PropertySource("classpath:custom.properties")
public class MyService {

    @Value("${api.key}")
    private String apiKey;

    // 이제 apiKey 변수에 해당 속성 값이 주입됩니다.
}
```
gitignore에 해당 file을 등록해줍니다.
