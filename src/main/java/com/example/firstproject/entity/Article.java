package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //DB가 해당 객체를 인식 가능!
@Getter //롬복으로 게터 추가
@ToString
@AllArgsConstructor
@NoArgsConstructor //매개변수 없는 생성자 코드를 작성할 필요가 없다.
public class Article {
    /*Article 클래스 'Article'에는
    [public, protected] no-arg 생성자가 포함되어아 하는 오류*/
    @Id //대표값을 지정! LIKE A 주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) //1,2,3,... 자동 생성 어노테이션
    private Long id;
    //Entity에는 대표값을 넣어야 한다.

    @Column
    private  String title;

    @Column
    private  String content;

    ///lic Long getId() {//주의 데이터 타입을 String -> Long으로 변경
    ///return id;
    ///}*
}
