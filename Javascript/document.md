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
