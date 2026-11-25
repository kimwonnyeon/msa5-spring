package org.msa.msa5spring.controller;

import jakarta.websocket.server.PathParam;
import org.msa.msa5spring.request.QueryRequestDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class RequestMappingController {
    @GetMapping
    public String index(){
        return "유저 조회";
    }

    @PostMapping
    public String store(){
        return "유저 생성";
    }

    @PutMapping
    public String update(){
        return "유저 수정";
    }

    @DeleteMapping
    public String destroy(){
        return "유저 삭제";
    }

    //쿼리 파라미터 획득
    @GetMapping("/query")
    public String query(QueryRequestDTO queryRequestDTO){
        return String.format("%d : %s",queryRequestDTO.id(),queryRequestDTO.name());
    }

    //세그먼트 파라미터 획득
    //DTO로 받을 거면 @ModelAttribute
    //파라미터 바로 받을 거면 @PathVariable
    //path를 동일 하게 작성 하지 말것
    //ex)/segment/{id}/{name},/segment/1/my(id:1,name:my)
    @GetMapping("/segment/{id}/{name}")
    public String segment(@ModelAttribute QueryRequestDTO queryRequestDTO){
        return String.format("%d : %s",queryRequestDTO.id(),queryRequestDTO.name());
    }

    //폼 데이터 파라미터 획득
    @PostMapping("/form")
    public String form(@ModelAttribute MultipartFile file){
        return  String.format("%s : %s",file.getContentType(),file.getOriginalFilename());
    }

    //JSON 데이터 파라미터 획득
    @GetMapping("/json")
    public String json(@RequestBody QueryRequestDTO queryRequestDTO){
        return String.format("json= %s : %s",queryRequestDTO.id(),queryRequestDTO.name());
    }
}
// Query Parameter 방식
//- 어노테이션 X, DTO 객체 생성

//Segment Parameter 방식
//- @PathVariable, 각 키에 맞는 파라미터를 별도로 맵핑 메소드의 파라미터로 생성
//- @ModelAttribute, DTO 객체 생성

//Form Data 방식
//- @ModelAttribute, DTO 객체 또는 맵핑 메소드의 파라미터

//Json 방식
//-@RequestBody, DTO 객체