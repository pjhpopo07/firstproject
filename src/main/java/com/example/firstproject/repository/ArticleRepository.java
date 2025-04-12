package com.example.firstproject.repository;


import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    //JPA에서 제공하는 Repository 인터페이스 활용(CroudRepository<관리대상 Entity,Entity 대표값 타입>)
}
