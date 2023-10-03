1000까지 소수를 구해보자

버전 1) 총 연산횟수는 78022

```
public class PrimeNumber1 {
    public static void main(String[] args) {

        int count=0;
        second: for(int i=2;i<=1000;i++){

            for(int j =2; j<=i-1; j++){
                count++;
                if(i%j==0){
                    continue second;
                }
            } //2for end
            System.out.println(i);
        }// 1 for end
        System.out.println("총 연산횟수는 "+count);
    }
}
```

버전 2) 총 연산횟수는 3774

소수를 배열prime에 넣어준다.

prime배열의 값으로 나누어지지 않으면 소수라고 판단하고, prime배열에 추가해준다.

홀수만 연산. 짝수는 연산에 제외 & 2로 나누는 연산은 제외.

```
public class PrimeNumber1 {
    public static void main(String[] args) {

    int count = 0;
    int ptr = 0;
    int[] prime = new int[500];

    prime[ptr++] = 2;
    System.out.println(2);
    prime[ptr++] = 3;
    System.out.println(3);
    for(int n = 5; n <=1000; n=n+2){
        int i;
        boolean flag = true;
        for(i=1;(prime[i]!=0)&&(prime[i]*prime[i]<=n);i++){
                count +=2;
                if(n%prime[i]==0){
                    flag= false;
                    break;
                }
        }
        if(flag){
            prime[ptr++] = n;
            count++;
            System.out.println(n);
        }
    }
        System.out.println("총 연산횟수는"+count);

    }
}
```
