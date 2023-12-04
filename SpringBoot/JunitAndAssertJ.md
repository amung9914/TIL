## test를 활용해봅시다

```
package com.blog;

import org.junit.jupiter.api.*;

public class JUnitCycleTest {

    @BeforeAll // 전체 테스트 시작 전 1회 실행 (static으로 선언)
    static void beforeAll(){
        // DB 연결이나 테스트 환경 초기화할 때 사용
        System.out.println("@BeforeAll");
    }

    @BeforeEach // 테스트 케이스 시작 전마다 실행
    public void beforeEach(){
        // 메서드에 사용되는 객체 초기화하거나 미리 필요한 값을 넣는 경우 사용
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1(){
        System.out.println("test1");
    }

    @Test
    public void test2(){
        System.out.println("test2");
    }
    @Test
    public void test3(){
        System.out.println("test3");
    }

    @AfterAll // 전체 테스트 후 종료 전 1회 실행(static)
    static void afterAll(){
        // DB연결종료나 공통으로 사용하는 자원 해제
        System.out.println("@AfterAll");
    }

    @AfterEach //테스트 종료 전마다 실행
    public void afterEach(){
        //테스트 이후 특정 데이터 삭제
        System.out.println("@AfterEach");
    }
}
```

\*컨트롤러 테스트 하는 방법

```
package com.blog.controller;

import com.blog.entity.Member;
import com.blog.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping("/test")
    public List<Member> getAllMembers(){
        List<Member> members = testService.getAllMembers();
        return members;
    }

}
```

위의 컨트롤러를 테스트해보겠습니다 :)

```
package com.blog.controller;

import com.blog.entity.Member;
import com.blog.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class TestControllerTest {

    @Autowired protected MockMvc mockMvc; // 컨트롤러 테스트할 때 사용됨
    @Autowired private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = webAppContextSetup(context)
                .build();
    }
    @AfterEach
    public void cleanUp(){
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers : 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception{
        //given
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L,"홍길동"));

        // when
        // MockMvcRequestBuilders.get;
        final ResultActions result = mockMvc.perform(get(url) //요청 전송
                .accept(MediaType.APPLICATION_JSON)); //JSON으로 받겠다고 명시
        // ResultActions는 반환값을 가지고 이것을 .andExpect()로 응답검증
        // then
        result
                .andExpect(status().isOk())
                // servlet.result.MockMvcResultMatchers.jsonPath; JSON응답값을 가져온다. 0번째 배열에 들어있는 객체의 id값 가져옴
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }


}
```

리턴값이 배열이 아닌 경우

ex. ResponseEntity<ArticleDto>

```
@DisplayName("findArticle: 블로그 글 조회에 성공한다.")
@Test
public void findArticle() throws Exception {
    // given
    final String url = "/api/articles/{id}";
    final String title = "title";
    final String content = "content";

    Article savedArticle = blogRepository.save(Article.builder()
            .title(title)
            .content(content)
            .build());
    // when
    final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

    // then
    resultActions.andExpect(status().isOk())
            .andExpect(jsonPath("$.content").value(content))
            .andExpect(jsonPath("$.title").value(title));
}
```
---

JSON값을 보내는 update기능의 경우 테스트 하는 법

     @Autowired protected MockMvc mockMvc;

    //직렬화 역직렬화를 위한 클래스, 자바객체 <->JSON 데이터
    @Autowired protected ObjectMapper objectMapper;

    //스프링컨테이너의 한 형태. applicationContext에 웹 환경에 필요한 기능을 추가한 것.
    @Autowired private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

```
ResultActions result = mockMvc.perform(put(url, savedArticle.getId())
        // JSON 데이터를 보내요
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(request)));

// then
result.andExpect(status().isOk());
```



[https://github.com/shinsunyoung/springboot-developer/blob/main/chapter4/src/test/java/me/shinsunyoung/springbootdeveloper/TestControllerTest.java](https://github.com/shinsunyoung/springboot-developer/blob/main/chapter4/src/test/java/me/shinsunyoung/springbootdeveloper/TestControllerTest.java)

책참고
