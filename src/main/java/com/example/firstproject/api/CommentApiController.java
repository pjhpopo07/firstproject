package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    //1. 댓글 조회
    @GetMapping("api/articles/{articledId}/comments") //댓글 조회 요청 접수
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articledId){
        //comments 메서드 생성
        // 서비스 위임(댓글 서비스에 조회 작업을 위임해 얻은 결과를 클라이언트에 응답)
        List<CommentDto> dtos = commentService.comments(articledId);
        //결과 응답(예외 처리 방식을 선호) 예외방식이란 예기치 못한 상황이 발생할 때를 대비해 대처하는 코드를 미리 작성
        return ResponseEntity.status(HttpStatus.OK).body(dtos); //서비스 위임을 dtos로 했으니 -> body에도 dtos 삽입한다.
        //REST API의 응답은 ResponseEntity에 실어 보내야한다.
    }
    //2. 댓글 생성
    @PostMapping("/api/articles/{articledId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto){
        //서비스 위임
        CommentDto createdDto = commentService.create(articleId,dto);
        //댓글 생성을 위해 CommentService의 articleId 랑 dto 메서드를 호출한다

        //결과 응답
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }
    //3. 댓글 수정
    //4. 댓글 삭제
}
