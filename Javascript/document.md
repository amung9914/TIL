### document 객체
document객체를 통해 HTML문서 정보를 가져올 수 있다. (이미 서로 연결되어있음)
```
document.title = "Hello! From JS!";
```
- id값으로 요소 가져오기
- console.dir(요소) : 상세하게 나타냄
```
const title = document.getElementById("title");
console.dir(title);
title.innerText="Got you!";
```
동일한 클래스의 많은 요소들 가져오기
```
document.getElementsByClassName("클래스이름");
```
querySelector()는 CSS방식으로 요소를 가져올 수 있다. 여러개가 있는 경우 첫번째 요소만 가져온다.
```
const hellos = document.querySelector(".hello h1");
console.log(hellos);
```
첫번째 요소만 가져온다?
```
const hellos = document.querySelector(".hello h1:first-child");
```
querySelectorAll() 는 조건에 맞는 모든 요소를 array형태로 가져온다.

## 클릭이벤트
- 클릭 시 함수 실행
```
const title = document.querySelector(".hello h1");

function handleTitleClick(){
    console.log("title was clicked!");
}

title.addEventListener("click",handleTitleClick);
HTML요소.addEventListener("이벤트",함수이름);
```
- 여러 이벤트 실행
```
  function handle(){
    title.innerText= "mouse is here!";
}

function handlemouseLeave(){
    title.innerText="mouse is gone";
}
    
title.addEventListener("click",handleTitleClick);
title.addEventListener("mouseenter",handle);
title.addEventListener("mouseleave",handlemouseLeave);
```
resize event, 복사 event
```
function handleWindowResize(){
    document.body.style.backgroundColor="tomato";
}
    
function handleWindowCopy(){
    console.log("copier!");
}

window.addEventListener("resize",handleWindowResize);
window.addEventListener("copy",handleWindowCopy);
```
- 클래스이름 변경/ 삭제
```
const h1 = document.querySelector(".hello h1");

function handleTitleClick(){
    if(h1.className ==="active"){
        h1.className = ""; 삭제
    }else{
        h1.className = "active"; 변경
    }
}

h1.addEventListener("click",handleTitleClick);
```
- toggle : classList에 classname이 있으면 제거/ 없으면 추가
```
const h1 = document.querySelector(".hello h1");

function handleTitleClick(){
    h1.classList.toggle("cliked");
}

h1.addEventListener("click",handleTitleClick);
```
