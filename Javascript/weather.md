## 화면에 user 위치의 날씨 표시하기

- user의 위도,경도 얻어오기
```
function onGeoOk(position){
    const lat = position.coords.latitude; //위도
    const lng = position.coords.longitude; //경도
    console.log("You live in",lat,lng);
}
function onGeoError(){
    alert("Can't find you");
}

//위치 좌표 얻어옴(성공했을 때 실행할 함수, 실패했을 때 실행할 함수)
navigator.geolocation.getCurrentPosition(onGeoOk,onGeoError);
```
