## Stream<String>에서 String으로 변환하기

Stream.collect(Collectors.joining());

### 중복된 문자 제거 문제 풀이
https://school.programmers.co.kr/learn/courses/30/lessons/120888

Q) 문자열 my_string이 매개변수로 주어집니다. my_string에서 중복된 문자를 제거하고 하나의 문자만 남긴 문자열을 return하도록 solution 함수를 완성해주세요.

```
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

class Solution {
    public String solution(String my_string) {
        Stream<String> stringStream = Pattern.compile("").splitAsStream(my_string);
        return stringStream.distinct().collect(Collectors.joining());
    }
}
```

Collectors의 joining메소드에서는 StringBuilder를 new, append 해주고 StringBuilder::toString을 해주고 있습니다.
따라서 Stream.collect(Collectors.joining());으로 Stream내의 문자를 문자열로 만들어줄 수 있습니다.
