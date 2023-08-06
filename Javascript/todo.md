## To Do List
```
<form id="todo-form">
       <input type="text" placeholder="Write a To Do and Press Enter" required/> 
    </form>
    <ul id="todo-list"></ul>
    <div id="quote">
        <span></span>
        <span></span>
    </div>
```
ul태그에 li태그를js로 추가한다(나중에 항목 삭제구현하려고)
```
const toDoForm = document.getElementById("todo-form");
const toDoInput = toDoForm.querySelector("input");
const toDoList = document.getElementById("todo-list");

function paintToDo(newTodo){
    const li = document.createElement("li");
    const span = document.createElement("span");
    li.appendChild(span);
    span.innerText = newTodo;
    toDoList.appendChild(li);
}

function handleToDoSubmit(event){
    event.preventDefault();
    const newTodo = toDoInput.value;
    toDoInput.value = "";
    paintToDo(newTodo);
}
toDoForm.addEventListener("submit",handleToDoSubmit);
```
- addEventListener 함수에서 event.target : 클릭된 HTML요소.
- 삭제버튼 눌러서 부모요소인 li 제거하기
```
function deleteToDo(event){
    const li= event.target.parentElement;
    li.remove();
}

function paintToDo(newTodo){
    const li = document.createElement("li");
    const span = document.createElement("span");
    span.innerText = newTodo;
    const button = document.createElement("button");
    button.innerText = "X";
    button.addEventListener("click",deleteToDo);

    li.appendChild(span);
    li.appendChild(button);
    toDoList.appendChild(li); // append는 나중에 이뤄줘야함.
}
```
- JSON.stringify(객체 또는 배열) : 객체 또는 배열를 JSON String으로 변환합니다.
- JSON.parse(JSONstring); JSON String을 객체로 변환해줍니다.
  
### javaScript는 배열의 각각 item에 대해 function을 실행시킬 수 있게 해줍니다.

.forEach(함수이름) : array의 각각의 item에 대해 function(sayHello)을 실행시킨다.
```

function sayHello(item){
    console.log("this is the turn of", item);
}

const savedToDos = localStorage.getItem(TODOS_KEY);

if(savedToDos !== null){
    const parseToDos = JSON.parse(savedToDos);
    parseToDos.forEach(sayHello);
}
```
- 람다식으로 표현할 수 있다.
  ``` parseToDos.forEach((item) => console.log("this is the turn of", item)); ```
- 이를 이용해서 화면에 localStorage의 array의 item을 표시할 수 있다.
```
if(savedToDos !== null){
    const parseToDos = JSON.parse(savedToDos);
    parseToDos.forEach(paintToDo);
    // 자바스크립트가 paintToDo메소드에 item 전달해준다.
}
```

### todo 목록에서 특정한 item만 지우기

- Date.now() : ms를 주는 함수. 랜덤값 사용할 때 이용함. 이것을 이용해서 목록(li)에 id값줄 수 있음.
- toDos에 text와 id값을 가지고 있는 객체를 넣어준다.
```
const newTodoObj = {
        text : newTodo,
        id : Date.now()
    }
    toDos.push(newTodoObj);
    paintToDo(newTodoObj);
```
```
function paintToDo(newTodo){
    const li = document.createElement("li");
    li.id = newTodo.id; // HTML li에 id값 적용
```
- filter를 이용해서 지우고 싶은 item을 제외하고 새로운 array를 만든다.
- filter는 arra의 item을 유지하고 싶으면 true 리턴 / 제외하고 싶으면 false 리턴
```

function deleteToDo(event){
    const li= event.target.parentElement;
    li.remove();
    // 우리가 클릭한 li의 id가 다른 것은 남겨둔다.(서로 타입이 달라서 타입변환 필수)
    toDos = toDos.filter(toDo => toDo.id !== parseInt(li.id));
    saveToDos(); // localStorage에 새롭게 저장한다.
}
```
