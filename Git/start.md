## Git Clone 받는 법(인텔리제이 Local 창 기준)

인텔리제이 에서 프로젝트 아무거나 열고나서

Local에서 명령어로 

1. 원하는 경로로 이동
2. clone 프로젝트를 저장할 폴더 생성

```jsx
mkdir didimCM
```

1. git clone 실행

```jsx
git clone <clone주소>
```

### **오류 발생 시 메세지를 잘 읽어볼 것.

`fatal: could not read Username for '[g](http://gitlab.redwoodk.com/)it 프로젝트 주소': No such file or directory` 

다음과 같은 오류 발생 시 대처 요령 : [user.name](http://user.name) 또는 [user.email](http://user.email) 설정하기

현재 시스템의 모든Git 작업에 사용할 사용자 이름과 이메일을 설정한다.

```jsx
git config --global user.name "kimsy" // 사용자 이름 설정
git config --global user.email you@example.com // 사용자 이메일 설정

// 이제 다시 git clone을 해보자.
git clone <git clone 주소>
```

Git에서는 커밋을 할 때 사용할 이름과 이메일을 지정할 수 있으며, 이때 커밋에 기록된 이메일은 GitHub의 사용자를 연결할 때도 사용된다.