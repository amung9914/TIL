byte < short < int < long < float < double

#### 자동타입변환 :

1\. 작은 허용범위타입 -> 큰 허용범위타입

2\. 정수타입이 실수타입으로 저장되는 경우

3\. char 타입이 int 타입에 저장되는 경우(유니코드 값 저장)

```
byte byteValue = 10;
int intValue = byteValue;
```

#### 강제 타입변환 :

작은 허용 범위 타입 = (작은 허용 범위 타입) 큰 허용 범위 타입  
실수 타입(float,double)은 정수타입(byte,short,int,long)으로 변환 시 소수점 이하는 버림. 정수 부분만 저장됨

```
double doubleValue = 3.14;
int intValue = (int) doubleValue; // 3
```

#### 정수 연산에서의 자동 타입 변환(int)

int 보다 큰 경우, 두 피연산자 중 허용 범위가 큰 타입으로 변환된다.  
실수 연산은 double타입으로 자동 변환된다.

정수 산술연산결과 = 정수  
실수 산술연산결과 = 실수

```
int x = 1;
int y = 2;
double result = x / y;
이 때 result를 출력하면 0.0이 출력된다.
```

실수값이 나오게 하려면? double타입으로 변환한다,

```
int x = 1;
int y = 2;
double result = (double) x / y;
result 는 0.5로 출력됨
```

문자열은 앞에서 순서대로 + 연산 수행함.  
만약 먼저 수행된 연산이 결합 연산이라면 이후 + 연산은 모두 결합 연산이다.

```
String str = 1 + 2 + "3"; -> String str 3 + "3"; -> String str = "33";
String str = 1 + "2" + 3; -> String str "12"+3; -> String str = "123";
```

#### 기본타입의 값을 문자열로 변경 하는 경우

(byte,short,char,int,long,float,double,boolean)  
`String.valueOf(기본타입값)`

#### 문자열을 기본 타입으로 강제타입변환

String -> int

```
String str = "3000000";
int value = Integer.parseInt(str);
```

String -> double

```
String str = "12.345";
double value = Double.parseDouble(str);
```
