- setInterval(함수이름,ms) : 설정한 시간마다 함수를 호출함
```
setInterval(sayHello,5000); 5초마다 함수 실행(5초뒤에 최초실행)
```
- setTimeout설정한 시간 뒤에 함수 한번만 실행
```
setTimeout(sayHello, 5000);
```

## 시계구현
```
<h2 id="clock">00:00:00</h2>
<script>
const clock = document.querySelector("h2#clock");

function getClock(){
   const date = new Date();
   clock.innerText = (`${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`);
}

getClock();//즉시 실행
setInterval(getClock, 1000);//1초 뒤부터 실행
</script>
```
### padStart() : string을 길게 만들 때 사용하는 메소드. string에 쓸 수 있음.
ex> `` "1".padStart(2,"0") ``<br/>
길이를 2로 만들면서 0으로 채움(길이가2인 경우에는 작동x)<br/>
padEnd는 뒤에 0 채움.<br/>
ex> ``"hello".padStart(20,"x")``<br/>
- Number에서 String으로 변환 -> String(값);
```
function getClock(){
   const date = new Date();
   const hours = String(date.getHours()).padStart(2,"0");
   const minutes = String(date.getMinutes()).padStart(2,"0");
   const seconds = String(date.getSeconds()).padStart(2,"0");
   clock.innerText = (`${hours}:${minutes}:${seconds}`);
}
```
- random number 구하기(0~9)<br/>
`` quotes[Math.floor(Math.random()*quotes.length)]; ``<br/>
floor(내림)을 이용해서 quotes라는 array의 인덱스값을 랜덤으로 가져옴<br/>
<br/>

- 사진을 random으로 body에 추가하기
```
const images = ["1.jpg","2.jpg","3.jpg"];

const chosenImage = images[Math.floor(Math.random()*images.length)];
const bgImage = document.createElement("img");
bgImage.src = `/img/${chosenImage}`;
document.body.appendChild(bgImage);
```
  

