
## 테스트 코드를 만들때 아래 코드로 테스트 준비를 해주면 좋을 것 같다. 참고하자

```

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired protected MockMvc mockMvc;

    //직렬화 역직렬화를 위한 클래스, 자바객체 <->JSON 데이터
    @Autowired protected ObjectMapper objectMapper;

    //스프링컨테이너의 한 형태. applicationContext에 웹 환경에 필요한 기능을 추가한 것.
    @Autowired private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        //WebApplicationContext를 사용해서 mockMvc를 설정
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }
```
이 아래에 실제 테스트할 코드를 붙여줍니다.
이어지는 코드는 예제입니다.

```

 @DisplayName("addAtricle : 블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception{
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        final String requestBody = objectMapper.writeValueAsString(userRequest); //JSON으로 직렬화

        // when
        //요청 전송
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }
```
