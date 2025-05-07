package com.example.firstproject.service;

import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


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
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        //a,b,c 3개의 객체를 Array.asList()메서드를 사용해 ArrayList로 합치고 이를 List<Article>타입의 expexted에 저장
        //그 후 Arrays 클래스 import class 클릭하여 Arrays(java.util)을 선택해 관련 패키지 임포트
        //Arrays.asList란 입력된 배열 또는 2개 이상의 동일한 타입 데이터를 정적리스트로 만들어 반환한다.
        //정적 리스트는 고정 크기이므로 add()나 remove()메서드를 사용할 수 없다 사용할려면 일반 리스트로 만들어쓴다.
        //2. 실제데이터
        List<Article> article = articleService.index();
        // articleService.index() 메서드 호출해 그 결과를  List<Article>타입의 article에 받아온다.
        //3. 비교 및 검증
        assertEquals(expected.toString(), articleService.toString());
    }
}