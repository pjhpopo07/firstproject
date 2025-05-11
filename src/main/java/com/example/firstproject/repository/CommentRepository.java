package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//Article리파지터리는 crud를 사용했찌만 Comment리파지터리는 jpa를 사용한다.
public interface CommentRepository extends JpaRepository<Comment, Long> {
    //쿼리를 메서드로 작성하는 것을 네이티브 쿼리 메서드라고 한다. 2가지 방법 1. @Query 2. orm,xml 파일 사용
    //@쿼리 어노테이션은 SQL이랑 비슷한 JPQL이라고 하는 객체 지향 쿼리 언어를 통해 복잡한 쿼리 처리를 지원한다.
    //naticeQuary 속성을 true로 하면 기존 SQL문을 그대로 사용할 수 있다.
    //기본 형식은 @Quary(value="쿼리", nativeQuary = true)
    // 특정 게시글의 모든 댓글 조회
    @Query(value= "SELECT * FROM comment WHERE article_id =:articled", nativeQuery = true) //value 속정을 실행을 위헌 쿼리문
    List<Comment> findByArticleId(Long articled);
    //Nickname은 XML로 작성 -> 네이티브 쿼리 XML라고 말한다.
    //XML의 기본 경로와 파일 이름은 META-INF/orm.xml, 이 경로로 파일 생성시 자동인식
    // 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);
}
