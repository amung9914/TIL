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
Date.now() : 랜덤값 사용할 때 이용함. 이것을 이용해서 목록(li)에 id값줄 수 있음.
