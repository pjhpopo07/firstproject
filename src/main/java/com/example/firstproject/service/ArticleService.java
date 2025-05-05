package com.example.firstproject.service;


import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
