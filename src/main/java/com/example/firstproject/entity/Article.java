package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity //DB가 해당 객체를 인식 가능!
@ToString
@AllArgsConstructor
public class Article {
    /*Article 클래스 'Article'에는
    [public, protected] no-arg 생성자가 포함되어아 하는 오류*/
    @Id //대표값을 지정! LIKE A 주민등록번호
    @GeneratedValue //1,2,3,... 자동 생성 어노테이션
    private Long id;
    //Entity에는 대표값을 넣어야 한다.

    @Column
    private  String title;

    @Column
    private  String content;

    public Article() {

    }
}
