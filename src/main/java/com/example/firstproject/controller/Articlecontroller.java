package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.firstproject.dto.ArticleForm;

@Controller
public class Articlecontroller {

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return"articles/new";

    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());
        return "";
    }
}
