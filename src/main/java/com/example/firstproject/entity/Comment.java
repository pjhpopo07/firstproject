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

    public static Comment createComment(CommentDto dto, Article article) {
        //1.예외 발생
        //예외 발생 코드 2가지 1-1.dto id 존자핼 경우  IllegalArgumentException 예외발생
        //1-1.1 의도적으로 IllegalArgumentException 예외 발생 -> throw new IllegalArgumentException("메세지")
        if(dto.getId()!=null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        //2.엔티티 생성 및 반환
        //예외 상황이 발생하지 않았다면 엔티티를 만들어 반환하게 한다.
        //전달값으로 필요한 요소 id,nickname,body는 dto로 가져오고 부모게이글은 article 자체 입력
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }
}
