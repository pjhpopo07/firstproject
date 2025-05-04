package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
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
    private ArticleRepository articleRepository;
    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }
    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        //Article을 ResponseEntity 담아서 반환해야 반환하는 데이터에 상태 코드를 실어 넣을 수 있다.
        //ResponseEntity는 REST 컨트롤러의 반환형, 즉 REST API의 응답을 위해 사용하는 클래스
        //1.DTO -> 엔티티 변환하기
        Article article = dto.toEntity();
        log.info("id: {}, article:{}",id,article.toString()); //로그 찍기
        //2.타킷 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리하기
        if (target == null || id != article.getId()) {
            log.info("잘못된 요청 id: {}, article:{}",id,article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            //  ResponseEntity의 status(상태)에는 400 또는 HttpStatus.BAD_REQUEST를 본문에 반환할 데이터가 없으므로 null을 실어 반환한다
        }//4. 업데이트 및 정상 응답(200)하기
        target.patch(article); // 기존 데이터에서 새 데이터 붙이기, 그 후 patch() 메서드 생성
        Article updated = articleRepository.save(target); //수정 내용 DB에 최종 저장
        return ResponseEntity.status(HttpStatus.OK).body(updated);
        //article 엔티티에 담긴 수정용 데이터를 DB에 정하고 updated 변수에 저장한다.
        // 수정된 데이터를 ResponseEntity 보낸다. 이때 상태에는 정상 응답이므로 200또는  HttpStatus.OK를 싣고 본문에는 반환할 데이터인 updated를 싣는다.
    }
    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        //1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //2. 잘못된 요처 처리하기
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //3. 대상 삭제하기
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
