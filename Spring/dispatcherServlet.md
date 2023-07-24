<h2>DispatcherServlet</h2>
@Controller 클래스 에서
메소드 호출에 필요한 매개변수는 dispatcher servlet이 무조건 전달해준다. 아낌없이 주는 나무..
(ex.model은 request랑 똑같은 역할을 한다. 포워드로 화면 출력할때 request에 실어서 정보 전달...)

<ul>
  <li><h4>호출된 메소드의 반환값이 없으면 요청경로가 view 페이지가 됨.</h4>
    (ex. @RequestMapping("doA") 에서 /WEB-INF/views/ + "doA" + ".jsp")</li>
  <li>
    <h4>서블릿이 name값이 일치하는 파마리터의 값을 message에 담아준다.</h4>
     * RequestParam 이라고 지정하면 무조건 값이 전달되어야 함.<br/>
    @RequestMapping(value="doD", method=RequestMethod.GET)
    public String doD(@RequestParam(name="msg", required = true) String message, Model model) {
  </li>
  <li>
      <h4>required=false : 필수값이 아니라고 지정. 이땐, defaultValue로 기본값 지정가능.</h4>
      @RequestParam(name="msg" , required=false, defaultValue = "empty message") String message,
    Model model
    ) {
  </li>
  <li>
    <h4>name값이랑 변수이름이 같은 경우, 변수 이름으로 일치하는 파라미터 값을 찾음. int타입이면 parseint도 해줌 </h4>
    @RequestMapping(value="doF", method=RequestMethod.POST)
    public String doD(String msg, int age, Model model) {
   </li>
  <li>
   <h4>key 값이 생략되면 ProductVO class 이름의 첫글자만 소문자로바꿔서 key값으로 지정된다.</h4>
   key == productVO
  </li>
  <li><h3>VO를 파라미터 값이 다 저장되있는 상태로 넘겨받을 수 있다.! 데이터 타입까지!! 자동으로 !!</h3> 
  @RequestMapping("productWrite")
  public String productWrite(
  Model model,
  ProductVO vo 
  ) {
 
  </li>
</ul>
