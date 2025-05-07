package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index(); //
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        //Article을 ResponseEntity 담아서 반환해야 반환하는 데이터에 상태 코드를 실어 넣을 수 있다.
        //ResponseEntity는 REST 컨트롤러의 반환형, 즉 REST API의 응답을 위해 사용하는 클래스
        Article updated = articleService.update(id,dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }//4. 업데이트 및 정상 응답(200)하기


    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PostMapping("api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        //@RequestBody는 REST API방식으로 POST 요청으로 받고 있으모로
        //서버에서 응답할 때 데이터 생성 결과뿐만 아니라 상태 코드도 함께 보내므로 ResponseEntity<List<Article>>으로 반환한다.
        List<Article> createdList = articleService.createArticles(dtos);
        //여러 게시글의 생성 요청을 받은 컨트롤러는 articleService의 createArticles() 메서드를 호출한다.
        //이때 매개변수 dtos도 함께 전달 잘 보낸 경우 반환값은 createList 리스트로 저장한다.
        return (createdList !=null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        //createList에 내용이 있다면 생성이 잘 되엇다는 뜻이므로 ResponseEntity의 상태에는 ok라 하고 본문에는 createdlist를 실어 보낸다.

    }

}
