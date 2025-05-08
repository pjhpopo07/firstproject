package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        //1. 예상데이터
        Article a=new Article(1L, "가가가가", "1111");
        Article b=new Article(2L, "나나나나", "2222");
        Article c=new Article(3L, "다다다다", "3333");
        //Article 객체 a,b,c에 저장하고 id는 Long타입의 접미사인 L을 붙힌다.
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        //a,b,c 3개의 객체를 Array.asList()메서드를 사용해 ArrayList로 합치고 이를 List<Article>타입의 expexted에 저장
        //그 후 Arrays 클래스 import class 클릭하여 Arrays(java.util)을 선택해 관련 패키지 임포트
        //Arrays.asList란 입력된 배열 또는 2개 이상의 동일한 타입 데이터를 정적리스트로 만들어 반환한다.
        //정적 리스트는 고정 크기이므로 add()나 remove()메서드를 사용할 수 없다 사용할려면 일반 리스트로 만들어쓴다.
        //2. 실제데이터
        List<Article> articles = articleService.index();
        // articleService.index() 메서드 호출해 그 결과를  List<Article>타입의 article에 받아온다.
        //3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    @Transactional
    void show_성공_존재하는_id_입력() {
        //1. 예상 데이터
        Long id=1L;
        Article expected = new Article(id,"가가가가", "1111");
        //2. 실제 데이터
        Article article = articleService.show(id);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void show_실패_존재하지_않는_id_입력(){
        //1. 예상 데이터
        Long id=-1L;
        //존재하지 않는 id눈 -1로 조회한다.
        Article expected = null;
        // DB에 조회돤 내용이 없으면 null로 반환한다
        //2. 실제 데이터
        Article article = articleService.show(id);
        //3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title과_content만_있는_dto_입력() {
        //1. 예상 데이터
        String title ="라라라라"; //title과 content 임의 값 지정
        String content = "4444";
        ArticleForm dto =new ArticleForm(null, title, content); //dto 생성
        Article expected = new Article(4L, title, content); //예상 데이터 작성
        //2. 실제 데이터
        Article article = articleService.create(dto);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력(){
        //1. 예상 데이터
        Long id = 4L; //id,title과 content 임의 값 지정
        String title ="라라라라";
        String content = "4444";
        ArticleForm dto =new ArticleForm(id,title,content);
        Article expected = null;
        //2. 실제 데이터
        Article article = articleService.create(dto);
        //3. 비교 및 검증
        assertEquals(expected,article);
    }
}