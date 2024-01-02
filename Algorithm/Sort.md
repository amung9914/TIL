정렬 알고리즘의 핵심요소 : 교환, 선택, 삽입

1\. 버블 정렬 : 이웃한 두 요소의 대소 관계를 비교하고 필요에 따라 교환을 반복한다. (단순 교환 정렬)

요소수가 n인 경우

변수 i값을 0부터 n-2까지 1씩 증가시키며 패스를 n-1번 수행한다? (n-1번 패스가 수행되면 마지막 요소는 끝에 놓임)

```
for(int i=0; i< n-1; i++){
    // a[i], a[i+1],...,a[n-1]에 대해 
    // 끝에서부터 앞쪽으로 스캔하면서 이웃하는 두 요소를 비교&교환
}
```

2\. 단순 선택 정렬 : 가장 작은 요소를 맨 앞으로 이동하고, 두 번째 작은 요소는 맨앞에서 두번째로 이동하고, 작업반복.

\-> 안정적이지 않다.(값이 3인 요소가 두개가 있는 경우 요소의 순서가 바뀔 수 있음)

1.  아직 정렬하지 않은 부분에서 가장 작은 키 값 선택(a\[min\])
2.  a\[min\]과 아직 정렬하지 않은 부분의 첫번째 요소를 교환.

이 두가지를 n-1번 반복한다.

```
for(int i = 0; i < n-1; i++){
    // min = a[i],...a[n-1]에서 값이 가장 작은 요소의 인덱스
    // a[i]과 a[min]의 값을 교환
}
```

3\. 단순 삽입 정렬 : 선택한 요소를 그보다 더 앞쪽의 알맞은 위치에 '삽입'하는 작업을 반복한다.

```
for(int i = 1; i< n; i++){
    // tmp <- a[i]
    // a[0],...,a[i-1]의 알맞은 곳에 tmp를 삽입한다
}
```

조건 :

1) 정렬된 열의 왼쪽 끝에 도달한다.

2)  tmp보다 작거나 같은 key를 갖는 항목 a\[j-1\]을 발견한다.

 둘 중 한 조건을 만족할때까지 j를 1씩 감소시키면서 대입 작업을 반복한다.

```
static void insertionSort(int[] a, int n){
    for(int i=1; i<n; i++){
        int j;
        int tmp = a[i];
        for(j=i; j>0 && a[j-1] > tmp;j--){
            lookcount++;
            a[j] = a[j-1];
        }

        a[j]= tmp;

    }
}
```

---

위의 버블,선택.삽입정렬은 모두 O(n제곱)의 시간복잡도를 가진다. 효율이 좋지 않다.

셸 정렬 : 단순 삽입 정렬을 개선한 알고리즘. 4-정렬, 2-정렬로 조금 정렬한 상태에서 단순삽입 정렬을 마지막으로 수행.

정렬횟수는 늘지만 전체적으로 요소의 이동횟수가 줄어든다.

단순삽입정렬과 달리 선택한 요소와 비교하는 요소가 h칸 만큼 떨어져있다.

```
static void shellSort(int[] a, int n){
   for(int h = n/2; h>0; h/=2)
       for(int i = h; i < n; i++){
           int j;
           int tmp = a[i];
           for(j = i-h; j >= 0 && a[j] > tmp; j -=h)
               a[j+h] = a[j];
           a[j + h] = tmp;
       }
}
```

h의 초기값은  n/2, 반복을 수행할때마다 h를 2로 나눈다.

\* 버전2. 수열을 사용해서 더 효율적으로 바꾸기(h값이 서로 배수가 되지 않도록 만들어서 요소가 섞이도록 함)

```
// V2 : h 값은 ...,40,13,4,1
static void shellSort(int[] a, int n){
    int h;

    // h값을 계산하는 시퀀스
    for(h=1; h<n; h=h*3+1)
        ;
    // h값을 초기화하지 않고 첫번째 for문에서 계산된 값 사용
    for(; h>0; h/=3)
        for(int i = h; i < n; i++){
            int j;
            int tmp = a[i];
            for(j = i-h; j >= 0 && a[j] > tmp; j -=h)
                a[j+h] = a[j];
            a[j + h] = tmp;
        }
}
```

---

퀵정렬 : 가장 빠른 정렬 알고리즘 중 하나.

```

static void partition(int[]a, int n){
    int pl = 0;
    int pr = n-1;
    int x = a[n/2]; // 피벗(가운데 요소)

    // 피벗 x를 기준으로 배열a를 나눈다.
    do{
        while(a[pl]<x) pl++;
        while(a[pr]>x) pr--;
        if(pl <= pr)
            swap(a,pl++,pr--);
    }while (pl <=pr);
    System.out.println("피벗값은"+x+"입니다");

    System.out.println("피벗 이하의 그룹");
    for(int i=0; i<=pl -1; i++)
        System.out.print(a[i]+ " ");
    System.out.println();

    if(pl > pr+1){
        System.out.println("피벗과 같은 그룹");
        for(int i= pr+1; i<pl;i++)
            System.out.print(a[i]+" ");
        System.out.println();
    }
    System.out.println("피벗 이상의 그룹");
    for(int i=pr+1; i<n;i++)
        System.out.print(a[i]+" ");
    System.out.println();

}
```
