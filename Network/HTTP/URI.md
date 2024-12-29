## #URL, WebBrowser

### URI? URL? URN?

![image](https://github.com/user-attachments/assets/7e8a3fc5-f0af-4bbb-a2be-9ce741b5ba47)

URN은 이름을 부여한 것. 거의 URL을 사용한다 

![image](https://github.com/user-attachments/assets/266c100c-1b2e-4628-a1f0-50b908dd2966)

URL - Locator: 리소스가 있는 위치를 지정

URN - Name : 리소스에 이름을 부여

URI 

- Uniform : 리소스 식별하는 통일된 방식
- Resource : 자원, URI로 식별할 수 있는 모든 것(제한 없음)
- Identifier : 다른 항목과 구분하는데 필요한 정보

즉, URI는 인터넷 리소스 식별자라고 할 수 있음

- 만약 회원 조회를 위한 endpoint인 경우 `회원`이 리소스이다.

### URL 전체 문법

![image](https://github.com/user-attachments/assets/97929cbd-affc-475e-a68f-28a025eec093)

- port : http : 80, https:443 포트를 주로 사용하며, 일반적으로 포트번호 생략가능
- path : 리소스 경로, 계층적 구조로 되어 있음
- query :
    - key=value형태
    - ? 로 시작, &로 추가 가능
    - query parameter, query string 등으로 불림. 웹서버에 제공하는 문자형태의 파라미터
- fragment
    - html 내부 북마크 등에 사용
    - 서버에 전송하는 정보 아님
    - 예시 - [`https://docs.spring.io/spring-boot/docs/current/reference/html/gettingstarted.html#getting-started-introducing-spring-boot`](https://docs.spring.io/spring-boot/docs/current/reference/html/gettingstarted.html#getting-started-introducing-spring-boot)
