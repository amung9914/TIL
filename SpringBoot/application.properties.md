### application.properties

```
spring.config.activate.on-profile=dev
url=dev.dv.com
username=dev_user
password=dev_pw
#---
spring.config.activate.on-profile=prod
url=prod.dv.com
username=prod_user
password=prod_pw
```

`#---` 로 영역을 구분한다. (구분자 주위 위아래 주석 적으면 안됨)

spring.config.activate.on-profile=프로필이름

ex) 개발 서버에서는 dev프로필 사용 / 운영 서버에서는 prod 사용

커맨드 라인 옵션 인수 실행  
\--spring.profiles.active=dev  
자바 시스템 속성 실행  
\-Dspring.profiles.active=dev

#### 기본값

프로필을 설정하지 않고 실행하는 경우 -> "default"라는 프로필이 활성화된다.  
프로필 지정 안하고 맨 위에 작성 시 기본값으로 설정됨(프로필 지정하고 실행해도 무조건 읽어들인다)

```
url=local.dv.com
username=local_user
password=local_pw
#---
spring.config.activate.on-profile=dev
url=dev.dv.com
username=dev_user
password=dev_pw
```

만약 프로필이  dev인 경우 -> 밑에 문서 읽어서 값을 대체함. 

출력 시 

```
url=dev.dv.com
username=dev_user
password=dev_pw
```

\* 프로필은 여러가지 활성화 가능하다

\*특별한 환경에서 일부 속성을 변경하고 싶으면

외부에 application.properties 를 외부 파일로 새로 만들고 변경하고 싶은 일부 속성만 입력해서 변경하는  
것도 가능하다.

\-> 실행 방법:

1\. ./gradlew clean build  
2\. build/libs 로 이동  
3\. 해당 위치에 application.properties 파일 생성

4\. java -jar 배포된jar파일이름 실행
