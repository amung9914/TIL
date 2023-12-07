### 회원등록 API

요청 값으로 엔티티 대신에 DTO를 RequestBody에 매핑한다.

```
@PostMapping("/api/v2/members")
public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
    Member member = new Member();
    member.setName(request.getName());

    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
}
```

```
@Data //api용 DTO, 이렇게하면 api스펙 까지 않아도 뭐가 넘어오는지 알 수 있음.절대 엔티티로 바로 사용하지 마시오
static class CreateMemberRequest{
    @NotEmpty //@Valid가 검증해서 api 에러 메세지 전송함.
    private String name;
}
```

실무에서는 회원 엔티티를 위한 api가 다양하게 만들어지는데, 한 엔티티에 각각의 api를 위한 모든 요청 요구사항을 담기 어렵다.

엔티티로 바로 받는 경우 엔티티가 바뀌면 api스펙도 바껴서 오류가능성이 높아진다. 

#### 실무에서는 엔티티를 api 스펙에 노출하면 안된다.

---

### 회원수정API

마찬가지로 DTO를 요청 파라미터에 매핑해준다. 변경감지를 사용하여 데이터를 수정한다.

실제로는 부분 업데이트 시 PUT보다는  PATCH를 사용하거나 POST를 사용하는 것이 REST 스타일에 맞음.

```
@PutMapping("/api/v2/members/{id}")
public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                           @RequestBody @Valid UpdateMemberRequest request){
    //커맨드랑 쿼리 분리해서 유지보수성 증대시키는 스타일임.
    memberService.update(id,request.getName());
    Member findMember = memberService.findOne(id);
    return new UpdateMemberResponse(findMember.getId(), findMember.getName());

}
```

//memberService 코드

```
@Transactional
public void update(Long id, String name) {
    Member member = memberRepository.findOne(id);
    member.setName(name);
}
```

// 아래는 다시 Controller코드

```
@Data
static class UpdateMemberRequest {
    private String name;

}
```

```
@Data
@AllArgsConstructor
static class UpdateMemberResponse {
    private Long id;
    private String name;
}
```

---

### 회원 조회 API

응답값으로 엔티티를 직접 외부에 노출하면 응답 스펙을 맞추기 위해 로직이 추가되는문제.

컬렉션을 직접 반환하면 향후 API 스펙을 변경하기 어렵다.

(별도의 Result 클래스(껍데기클래스) 생성으로 해결할 수 있다.)

따라서 API 응답 스펙에 맞추어 별도의 DTO를 반환한다.

엔티티리스트를 받아와서 DTO리스트로 매핑하고, 이를 Result 클래스에 담아서 반환한다.

```
@GetMapping("/api/v2/members")
public Result memberV2(){
    List<Member> findMembers = memberService.findMembers();
    List<MemberDto> collect = findMembers.stream()
            .map(m -> new MemberDto(m.getName()))
            .collect(Collectors.toList());

    return new Result(collect);  //data필드의 값을 Dto로 넣어준다.
                                // 이렇게하면 나중에 api 반환값 요구사항 새로 추가됐을때
                                //Result의 필드만 추가하면 구현가능!(ex. count)
    /*return new Result(collect.size(),collect);*/
}
```

```
@Data
@AllArgsConstructor // 껍데기클래스 생성
static class Result<T> {
    /*private int count;*/
    private T data;
}
@Data
@AllArgsConstructor //api스펙이 Dto랑 1:1로 매핑됨. 필요한거만 노출가능.
static class MemberDto {
    private String name;
}
```
