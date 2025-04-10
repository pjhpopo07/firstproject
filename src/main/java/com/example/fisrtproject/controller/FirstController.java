package com.example.fisrtproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi") //URL 연결
    public String niceToMeetYou(Model model){ // mustache 문법에 있는 변수를 불러오기 위해 모듈 받아오기
       model.addAttribute("username", "종혁");
        return "greetings"; //templates/greetings.mustache-> 브라우저 전송
    }
    @GetMapping("/bye")
    public String seeyouNext(Model model){
        model.addAttribute("nickname","종혁");
        return "goodbye";
    }
}
