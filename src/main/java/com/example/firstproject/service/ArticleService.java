package com.example.firstproject.service;


import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Article delete(Long id) {
        //1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null) {
            return null;
        }
        articleRepository.delete(target);
        return target;
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article create(ArticleForm dto) {
        Article article =dto.toEntity();
        return articleRepository.save(article);
    }

    public Article update(Long id,ArticleForm dto) {
        //3-1/dto -> 엔티티로 변환하기
        Article article = dto.toEntity();
        log.info("id:{}, article{}", id, article.toString());//3-1
         //3-2.타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        //3-3.질못된 요청 처리하기
        if(target == null || id != article.getId()) {
            log.info("잘못된 요청 id:{}, article{}", id, article.toString());
            return null;
        }
        //3-4 반환하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //1. dto 묶음을 엔티티 묶음으로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        // dtos를 스트림화 -> map()으로 dto가 올 때마다 dto,toEntity를 수행해 매핑한다 -> 매핑한 것을 리스트로 묶는다
        // -> 최종 결과 articleList에 묶는다(stream이란 리스트 같은 자료구조에 저장된 요소를 하나씩 순회하면서 처리하는 코드 패턴)
        // For문 사용해 6줄보다 스트림 문법 사용시 3줄로 줄일수 있다.
        //2. 엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        // articleList를 스트림화한다. -> article이 하나씩 올때마다 articleRepository를 통해 DB에 저장
        //3. 강제 예외 발생시키기
        articleRepository.findById(-1L)
                .orElseThrow(()-> new IllegalArgumentException("결제 실패!"));
        //ArticleRepository를 통해 findByID()로 id로 -1인 데이터를 찾는다. JPA로 자동 생성된 id로 양수이므로 음수가 될 수 없다
        // orElseThrow()메서드로 IllegalArgumentException을 발생시키는데 "결제 실패!"라는 메세지를 함게 남긴다.
        //4. 결과 값 반환하기
        return articleList;
    }
}
