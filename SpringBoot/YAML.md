스프링은 설정 데이터를 사용할 때 application.properties 뿐만 아니라 application.yml 이라는 형식도 지원한다. 

실무에서는 YAML을 주로 사용함.  

\*주의 : application.properties가 우선권을 가지므로 yml 사용 시 제거 필수

yml 문서 프로필 구분자 = `---`

```
my:
  datasource:
    url: local.db.com
    user: local_user
    password: local_pw
    etc:
      max-connection: 1
      timeout: 60s
      options: LOCAL, CACHE
---
spring:
  config:
    activate:
      on-profile: dev
my:
  datasource:
    url: dev.db.com
    user: dev_user
    password: dev_pw
    etc:
      max-connection: 10
      timeout: 60s
      options: DEV, CACHE
```
