package com.example.firstproject.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //DB에 테이블 생성
@Getter //각 필드 값 조회할 수 있는 getter 메서드 자동 생성
@ToString//to String 메서드 자동 생성
@AllArgsConstructor //모든 필드 매개변수 갖는 생성자 자동 생성
@NoArgsConstructor //매개변수가 아예 없는 기본 생성자 자동 생성
public class Comment {
    private Long id; //대표키
    private Article article; //해당 댓글의 부모 게시글
    private String nickname; //댓글을 단 사람
    private String body; //댓글 본문

}
