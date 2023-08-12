## 다른 두 페이지에서 EL 태그로 변수 공유하기

하나의 JSP페이지 내에서 다른 JSP페이지를 포함하려면 <jsp:include>태그를 이용할 수 있다.<br/>

이 때 , 만약 page2를 page1 에 포함시킨 경우 page1에 전달한 model 데이터는 page2에서도 사용할 수 있다. 

```
@Controller
public class MyController {

    @RequestMapping("/page1")
    public String page1(Model model) {
        model.addAttribute("sharedValue", "This is a shared value");
        return "page1";
    }

    @RequestMapping("/page2")
    public String page2() {
        return "page2";
    }
}

```
```
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Page 1</title>
</head>
<body>

<jsp:include page="page2.jsp" />

<div id="content">
    <p>${sharedValue}</p>
</div>

</body>
</html>

```
```
<div id="content">
    <p>${sharedValue}</p>
</div>
```
