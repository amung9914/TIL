### 문자열을 다른 문자열로 바꾸는 경우
String replace(char oldChar, char newChar) :

oldChar : 찾을 문자열 , newChar : 새로운 문자열

### 예제
```
문자열을 배열에 해당하는 인덱스로 바꾼다.
public long solution(String numbers){
 String[] arr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
 for(int i=0; i<arr.length;i++){
  numbers = numbers.replace(arr[i],String.valueOf(i));
}
 long answer = Long.parseLong(numbers);
return answer;
}

```


### String 클래스의 replace와 replaceAll의 차이점: 



String replaceAll(String regex, String replacement)

정규식또는 기존문자 , 대체문자 (정규식을 사용하면 정규식을 인식한다. 문자인 경우에는 replace와 같은 역할)

예제)

```
 String  str = "안녕하세요. 반가워요. 또 놀러오세요.";
        str = str.replace(".", "^^");
        System.out.println(str); // 결과 : 안녕하세요^^ 반가워요^^ 또 놀러오세요^^

        String str2 = "안녕하세요. 반가워요. 또 놀러오세요.";
        str2 = str2.replaceAll(".", "^^");
        System.out.println(str2); // 결과 : ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
```
