1\. 같은행, 같은 열에 겹치지 않도록 flag를 이용하였음(분기한정법이용)

```
public class QueenB {

    static boolean[] flag = new boolean[8];
    static int[] pos = new int[8];

    static void print(){
        for (int i = 0; i < 8; i++)
            System.out.printf("%2d",pos[i]);
        System.out.println();
    }
    static void set(int i){
        for (int j=0; j<8; j++) {
            if (flag[j] == false) {
                pos[i] = j;
                if (i == 7)
                    print();
                else {
                    flag[j] = true;
                    set(i + 1);
                    flag[j] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        set(0);
    }
}
```

재귀 호출한 set(i+1) 메서드 실행이 끝나면 퀸을 j행에서 제거해준다. (flag\[j\]에 false대입)

2\. 대각선 중복 문제를 고려하여 8퀸 문제 풀이.

3개의 배열(flag)을 사용하는 한정 조작을 수행하면 효율적으로 배치할 수 있다.

```
public class QueenB {

    static boolean[] flag_a = new boolean[8]; // 각 행에 퀸을 배치했는지 체크
    static boolean[] flag_b = new boolean[15]; // / 대각선방향으로 퀸을 배치했는지 체크
    static boolean[] flag_c = new boolean[15]; // \ 대각선 방향으로 퀸을 배치했는지 체크
    static int[] pos = new int[8]; // 각 열에 있는 퀸의 위치

    static void print(){
        for (int i = 0; i < 8; i++)
            System.out.printf("%2d",pos[i]);
        System.out.println();
    }
    static void set(int i){
        for (int j=0; j<8; j++) {
            if(flag_a[j] == false &&
                flag_b[i+j] == false &&
                flag_c[i-j+7] == false){
                pos[i] = j;
                if(i==7)
                    print();
                else{
                    flag_a[j] = flag_b[i+j] = flag_c[i-j+7] = true;
                    set(i+1);
                    flag_a[j] = flag_b[i+j] = flag_c[i-j+7] = false;
                }
            }

        }
    }

    public static void main(String[] args) {
        set(0);
    }
}
```

3\. 비재귀적으로 풀어보기
