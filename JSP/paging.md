## MVC 페이징 처리시 유의점

#### 1. 페이징 블록과 목록게시판 연결 시 주의점

Criteria와 PageMaker 코드를 이용하여 페이징 처리를 하는 경우<br/>
서블릿요청경로에 맞게 view의 a태그를 작성해주어야함.

만약 Controller에서 서블릿 요청경로를 ```"notice.do"```라고 설정한 경우 해당 경로를 통해 요청이 처리되도록 한다.
```
<!-- 페이징 블럭 출력 -->
				<tr>
					<th colspan="4">
						<c:if test="${pm.first}">
							<a href="notice.do${pm.makeQuery(1)}">[처음]</a>
						</c:if>
						<c:if test="${pm.prev}">
							<a href="notice.do${pm.makeQuery(pm.startPage-1)}">[이전]</a>
						</c:if>
						<c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}" step="1">
							<a href="notice.do${pm.makeQuery(i)}">[${i}]</a>						
						</c:forEach>
						<c:if test="${pm.next}">
							<a href="notice.do${pm.makeQuery(pm.endPage+1)}">[다음]</a>
						</c:if>
						<c:if test="${pm.last}">
							<a href="notice.do${pm.makeQuery(pm.maxPage)}">[마지막]</a>
						</c:if>
					</th>
				</tr>
```

#### 2. 검색기능을 추가하여 DB에서 list 검색하는 경우 주의점
SearchCriteria 를 이용하여 PreparedStatement를 활용한 검색 기능을 구현하고 있는데, <br/>
WHERE 절에 동적으로 컬럼이름을 바인딩하니 실행이 되지 않는다. 직접적으로 아래와 같이 넣어준다.  <br/>
```String sql = "SELECT * FROM notice_board WHERE notice_content LIKE ? ORDER BY notice_num DESC limit ?, ?";```  <br/>
이 때 검색 기능 구현을 위해 LIKE절은 아래와 같이 입력해준다. <br/>
```pstmt.setString(1, "%"+cri.getSearchValue()+"%");``` <br/>

