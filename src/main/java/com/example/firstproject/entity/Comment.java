package com.example.firstproject.entity;

import jakarta.persistence.*;
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
    @Id //대표키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에 자동으로 1씩 증가
    private Long id; //대표키

    @ManyToOne //article 필드에 @ManyToOne 어노테이션을 붙여 Comment 엔티티와 이 필드가 가리키는 Article 다대일 관계 설정
    @JoinColumn(name="article_id") //외래키 생성, Article 엔티티의 기본키와 매핑
    //@JoinColumn(name="외래키")  필드에 다대일 관계를 설정했다면 외래키도 매핑시켜줘야 한다.
    //외래키 설정하며 Comment 엔티티로 생성될 DB에 테이블에 article_id라는 속성이 만들어진다.
    //예를 들어, Article_id 저장된 값은 Article 엔티티 대표키 id로 매핑한다.
    private Article article; //해당 댓글의 부모 게시글

    @Column
    private String nickname; //댓글을 단 사람
    @Column
    private String body; //댓글 본문
}
