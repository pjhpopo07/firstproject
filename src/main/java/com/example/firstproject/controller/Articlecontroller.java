package com.example.firstproject.controller;

import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.firstproject.dto.ArticleForm;

@Controller
@Slf4j // 로깅을 위한 골뱅이(어노테이션)
public class Articlecontroller {
    @Autowired //스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결해준다.
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return"articles/new";

    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());
     /*System.out.println(form.toString());
     --> 실제 서버에서는 쓰지 않는다 악영향을 주기 때문에 --> 로깅으로 대체*/
        //1.DTO를 변환 -> Entity
        Article article = form.toEntity();
        System.out.println(article);

        //2.Repository에게 Entity를 DB안에 저장하게 한다.
        Article saved=articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString()); --> 이것도 로깅으로 대체
        //articleRepository 인식할 수 있어 기본적으로 제공하는 save기능 사용가능
        //articleRepository 객체를 선언을 안해도 spring 자체에서 해준다.
        return"";
    }
}
