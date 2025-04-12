package com.example.firstproject.controller;

import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.firstproject.dto.ArticleForm;

import java.util.Optional;

@Controller
@Slf4j // 로깅을 위한 골뱅이(어노테이션)
public class Articlecontroller {
    @Autowired //스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결해준다.
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new") //서버의 리소스를 조회할떄 사용
    public String newArticleForm() {
        return "articles/new";

    }

    @PostMapping("/articles/create") //서버에 리소스를 등록(저장)할 때 사용
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
     /*System.out.println(form.toString());
     --> 실제 서버에서는 쓰지 않는다 악영향을 주기 때문에 --> 로깅으로 대체*/
        //1.DTO를 변환 -> Entity
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article);->로깅으로 대체

        //2.Repository에게 Entity를 DB안에 저장하게 한다.
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString()); --> 이것도 로깅으로 대체
        //articleRepository 인식할 수 있어 기본적으로 제공하는 save기능 사용가능
        //articleRepository 객체를 선언을 안해도 spring 자체에서 해준다.
        return "";
    }

    @GetMapping("/articles/{id}") // 데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model) {
        log.info("id="+ id); //매개변수로 id 받아오기
        //1.ID 조회하여 DB에서 해당 데이터 가져오기
        //#1 첫번째 방법 Optional<Article> articleEntity = articleRepository.findById(id); //findByID()는 JPA의 CrudRepository 제공하는 메서드
        //aricleRepository가 findBy(id)를 반환할 떄 반환형 Article이 아니라서 반환형인 Optional<Article>를 사용한다.
        Article articleEntity = articleRepository.findById(id).orElse(null);//#2 두번째 방법
        //id값으로 데이터를 찾을 id 값이 없으면 null로 반환하라는 뜻임
        //2. 모뎀에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        //article이라는 이름에 articleEntity 객체를 등록한다. 형식(model.addAttribute("String name,Object name)
        //3. 뷰페이지 반환하기
        return "articles/show";
        //localhost:8080/articles/1000으로 접속
        //서버의 컨트롤러가 URL 요청을 받는 것까지 작업
        }



}