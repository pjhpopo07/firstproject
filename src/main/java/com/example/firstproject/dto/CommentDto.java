package com.example.firstproject.dto;


import com.example.firstproject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //모든 필드 매개변수 갖는 생성자 자동 생성
@NoArgsConstructor //매개변수가 아예없는 기본 생성자 자동 생성
@Getter //각 필드 값 조회할 수 있는 getter 메서드 자동 생성
@ToString //toString 메서드 자동
public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    //public static(정적 메서드)로 선언되었다 객체 생성 없이 호출 가능한 메서드라는 뜻 -> 생성 메서드라고 부른다.
    //매개변수 c를 코드 가독성을 위해 comment로 변환
    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
