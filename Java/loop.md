### 삼항 연산자

한 줄에 간단하게 작성하는 경우 효과적이다.

조건식 ? 값 또는 연산식(true인 경우) : 값 또는 연산식(false인 경우)

```
int score = 95;
char grade = (score > 90) ? 'A' : 'B';
```
연산 결과 'A' 

### continue문

for문, while문, do-while문 내부에서 실행되면 증감식 또는 조건식으로 돌아간다

ex) 짝수만 출력하기
```
	public static void main(String[] args) {
		for(int i =1; i<=10; i++) {
			
			if(i%2==1) {
				continue;
			}
			System.out.println(i);
		}
	}
```
