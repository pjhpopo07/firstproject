package com.example.firstproject.repository;


import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll(); // Iterable -> ArrayList 수정
    //JPA에서 제공하는 Repository 인터페이스 활용(CroudRepository<관리대상 Entity,Entity 대표값 타입>)
    //CrudRepository를 ArticleRepository가 상속받는중
}