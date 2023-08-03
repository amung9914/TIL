### HTML의 기능(form내에 input이 있을경우)
- maxlength : 최대글자수 
```
<form id="login-form">
    <div >
        <input required maxlength="15" type="text" placeholder="what is your name?"/>
        <button>Log In</button>   
        <script src="app1.js"></script>
    </div>
```
- 모든 addEventListener의 첫번째 argument는 지금 막 벌어진 일에 대한 정보 제공
- preventDefault: 어떤 event의 기본행동이든 수행하지 않도록 함.
```
  const loginForm = document.querySelector("#login-form");
const loginInput = document.querySelector("#login-form input");
function onLoginSubmit(event){
    event.preventDefault();
    const username = loginInput.value;
    console.log(loginInput.value);
}
loginForm.addEventListener("submit",onLoginSubmit);
```
### El 태그 사용 ``` `${변수명}` ``` 
- 이름 받으면 hidden 클래스로 있던 h1이 나타남
```
<style>
.hidden{
    display:none;
}
</style>
<body>
    <form id="login-form">
        <div>
            <input required maxlength="15" type="text" placeholder="what is your name?"/>
            <button>Log In</button>   
        </div>
    </form>
    <h1 id="greeting" class="hidden"></h1>
    <script src="app1.js"></script>
</body>
```
```
const loginForm = document.querySelector("#login-form");
const loginInput = document.querySelector("#login-form input");
const greeting = document.querySelector("#greeting");

const HIDDEN_CLASSNAME = "hidden"; 단순한 String 값 나타냄

function onLoginSubmit(event){
    event.preventDefault();
    loginForm.classList.add(HIDDEN_CLASSNAME);
    const username = loginInput.value;
    greeting.innerText = "Hello " + username;
    greeting.innerText = `Hello ${username}`;
    greeting.classList.remove(HIDDEN_CLASSNAME); hidden 클래스 삭제
}

loginForm.addEventListener("submit",onLoginSubmit);
```


