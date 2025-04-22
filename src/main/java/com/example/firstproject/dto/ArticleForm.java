package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
//생성자를 적지 않고 똑같은 효과를 내준다.
@ToString
//To.String 적지 않고도 똑같은 효과를 내준다.
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
     return new Article(id, title, content);
    }

}
