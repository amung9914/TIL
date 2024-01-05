### Contains()
문자열에 지정 문자열이 포함되어 있는지 확인한다.
```
// str2를 포함시 1을 리턴, 포함x : 2를 리턴
public int Solution(String str1, String str2){
  return (str1.contains(str2)? 1: 2);

```

#### 문자열의 길이를 구하는 메서드 length

```
String s = "ABC이지스DEF";
int length = s.length(); // 9
System.out.println(length);
```

#### 문자열 안에서 특정 문자를 꺼내는 메서드 charAt

```
String s = "ABC이지스DEF";
char c = s.charAt(0); // A
System.out.println(c);
```

#### 부분 문자열을 꺼내는 메서드 substring.

```
String s = "ABC이지스DEF";
String substring1 = s.substring(3);
System.out.println(substring1); // 이지스DEF
String substring2 = s.substring(6, 8);
System.out.println(substring2); // DE
```

매개변수가 1개일때 : 해당 인덱스부터 끝까지의 문자열

매개변수가 2개일때 : 인덱스가 begin인 문자부터 end 바로 앞 문자까지의 문자열

#### 다른 문자열과 내용이 같은지 조사하는 메서드 equals

```
String s = "ABC이지스DEF";
boolean equal = s.equals("이지스");
System.out.println(equal); // false
```

#### 다른 문자열과 비교(대소관계판단)하는 메서드 compareTo

```
String s = "ABCDEF";
int abc = s.compareTo("ABC");
System.out.println(abc); // 양의 정숫값
int abcdel = s.compareTo("ABCDEL");
System.out.println(abcdel); // 음의 정숫값
int abcdef = s.compareTo("ABCDEF");
System.out.println(abcdef); // 0
```

문자열이 매개변수보다 작으면 음의 정숫값

문자열이 매개변수보다 크면 양의 정숫값 (문자코드가 크면 그 쪽 문자열이 더 큰것으로 판단함)

문자열이 매개변수와 같으면 0

#### String.indexOf 메서드로 문자열 검색하기

```
String s = "ABC이지스DEF이지스";
int idx1 = s.indexOf("이지스");
System.out.println(idx1);//3
int idx2 = s.indexOf("이지스", 5);
System.out.println(idx2); // 9
```

indexOf(String str, int fromIndex) : fromIndex부터의 문자열을 검색한다

lastIndexOf() : 가장 뒤쪽에 위치한 문자열을 검색한다. fromIndex가 주어지면 지정된 인덱스에서 역방향으로 검색한다

```
String s = "ABC이지스DEF이지스";
int lastidx1 = s.lastIndexOf("이지스");
System.out.println(lastidx1); // 9
int lastidx2 = s.lastIndexOf("이지스",5);
System.out.println(lastidx2); // 3
```

#### 문자열을 Byte sequence로 부호화하여 바이트 배열에 넣어두는 메서드 : getBytes

```
String s = "***";
int length = s.getBytes().length;
System.out.println(length); // 3
```

\* 부호화 : 인코딩(encoding). 문자열을 기계가 처리하는 바이트 시퀀스로 변환하는 것.<-> decoding(디코딩)

문자열 자릿수 맞추기(문자열 공백) : String.format()

```
// 0으로 자릿수 맞추기(5자리) =00010
System.out.println("0으로 자릿수 맞추기(5자리) ="+String.format("%05d",10));;
String format1 = String.format("%5s", "ab");
//오른쪽 자릿수맞추기(5자리) =   ab
System.out.println("오른쪽 자릿수맞추기(5자리) ="+format1);
String format2 = String.format("%-5s","ab");
//왼쪽 자릿수 맞추기(5자리) =ab   
System.out.println("왼쪽 자릿수 맞추기(5자리) ="+format2);
```

응용 예시)

%%%ds : %%는 '%'문자를 표시하기 위한 이스케이프문자, %d는 정수를 대체할 자리 표시자

```
String.fotmat("%%%ds",5) // == "%5s"
```

위의 코드로 문자열의 폭을 지정할 수 있다.

아래와 같이 응용할 수 있다.

```
public static void main(String[] args) {
    String s1 = "AB주이지스DEF이지스12";
    String s2 = "이지스"; // 패턴

    int idx1 = s1.indexOf(s2);
    int idx2 = s1.lastIndexOf(s2);

    if(idx1 == -1)
        System.out.println("텍스트 안에 패턴이 없습니다");
    else{
        // 찾아낸 문자열 바로 앞까지의 문자 개수를 구한다
        int len1 = 0;
        for (int i = 0; i < idx1; i++)
            len1 += s1.substring(i,i+1).getBytes().length;
        len1 += s2.length();

        int len2 = 0;
        for (int i = 0; i < idx2; i++)
             len2 += s1.substring(i,i+1).getBytes().length;
        len2 += s2.length();

        System.out.println("텍스트 : " + s1);
        System.out.printf(String.format("패 턴: %%%ds\n", len1),s2);
        System.out.println("텍스트 : " + s1);
        System.out.printf(String.format("패 턴: %%%ds\n", len2),s2);
```

출력값: 

텍스트 : AB주이지스DEF이지스12  
패 턴:      이지스  
텍스트 : AB주이지스DEF이지스12  
패 턴:                  이지스
