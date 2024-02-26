![image](https://github.com/amung9914/TIL/assets/137124338/f94d9a8d-d26f-42f7-9627-9d3614ef476e)

​
| 종류 | 실행시점 |
| --- | --- |
| Filter | Dispatcher Servlet에 요청이 전달되기 전/후 실행 |
| Interceptor | Servlet이 Controller mapping method를 호출하기 전/후 실행 |
| AOP | target joinPoint(method)가 실행되기 전,후,자유롭게 호출 |
​
---
​
### Filter
​
웹 어플리케이션에 전반적으로 사용되는 기능을 구현할 때 적용할 수 있습니다. <br/>
​
웹 컨텍스트 내에서 실행됩니다. 즉 Spring Container 범위 밖에서 처리됩니다.<br/>
​
![image](https://github.com/amung9914/TIL/assets/137124338/3fd62794-17ca-47fe-9b0a-deb5ca6bb325)

​
클라이언트가 요청을 보내면, 애플리케이션 내부에서는 FilterChain이 생성됩니다.<br/>
​
요청 URI에 따라 적절한 Filter와 Servlet이 포함되어있습니다.<br/>
​
FilterChain의 모든 doFilter method가 호출되면 Servlet으로 요청을 전달합니다.<br/>
​
각 필터는 다음 필터 또는 서블릿의 호출을 막을지 여부를 결정할 수 있습니다.<br/>
​
각 필터는 HttpServletRequest 또는 HttpServletResponse를 수정할 수 있습니다.<br/><br/>
​
예시) (FilterChain : 필터 체인을 제어할 때 사용되는 객체)<br/>
​
전처리<br/>
​
chain.doFilter : FilterChain을 통해 다음 필터 또는 서블릿으로 요청을 전달합니다.<br/>
​
후처리<br/>
​
```
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
    // do something before the rest of the application
    chain.doFilter(request, response); // invoke the rest of the application
    // do something after the rest of the application
}
```
​
#### ❣️implementing Filter를 하는 대신 OncePerRequestFilter를 상속할 수도 있습니다.
​
OncePerRequestFilter를 사용하면 doFilterInternal 메서드를 구현할 수 있습니다.<br/>
​
이것은 모든 서블릿 컨테이너에서 요청 Dispatch당 단일 실행을 보장합니다. (한 번의 요청에 대해 단 한번만 호출)<br/>
​
2번 filter가 실행되는 경우... ex) Forwarding, Spring MVC error처리<br/>
​
인증,인가를 구현하는 경우 주로 사용합니다.<br/>
​
​
```
public class TokenAuthenticationFilter extends OncePerRequestFilter {
​
    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";
​
​
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 요청header의 Authorization 키 값 조회
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        String token = getAccessToken(authorizationHeader); // 실 토큰값으로 만듦
        // 가져온 토큰이 유효한 경우 인증 정보를 설정함
        if(tokenProvider.validToken(token)){
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
​
        filterChain.doFilter(request,response);
    }
```
