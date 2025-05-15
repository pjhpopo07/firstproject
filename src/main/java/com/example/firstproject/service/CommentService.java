package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository; //댓글 리파지터리 객체 주입
    @Autowired
    private ArticleRepository articleRepository; //게시글 리파지터리 객체 주입

    public List<CommentDto> comments(Long articleId){
        //1. 댓글 조회
        /*List<Comment> comments = commentRepository.findByArticleId(articleId);
        //2.엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<>();
        //조회한 댓글 엔티티 목록을 DTO 목록으로 변환하기 위해 CommentDto를 저장하는 빈 ArrayList를 만들고 List<CommentDto> 타입의 dtos 변수에 저장
        for(int i=0; i<comments.size(); i++){
            //조회한 댓글 엔티티의 개수(comments.size())만큼 반복하도록 for()문 작성
            Comment c = comments.get(i);
            //조회한 댓글 comments(엔티티 목록)에서 엔티티를 하나씩 꺼내 c 변수에 저장
            CommentDto dto = CommentDto.createCommentDto(c);
            //CommentDto의 createCommentDto(c) 메서드를 호출해 엔티티를 DTO로 변환하고 그 결과를 DTO 변수에 저장
            dtos.add(dto);
            //변환된 DTO를 dtos로 반환한다.
        }*/
        //3. 결과 반환(기존 return문 지우고 댓글 엔티티 목록을 바로 가져온다.)
        return commentRepository.findByArticleId(articleId)
                .stream() //댓글 엔티티 목록을 스트림으로 변환
                .map(comment -> CommentDto.createCommentDto(comment))
                //.map(a->b) 각 스트림의 각 요소를 꺼내 b를 수행한 결과로 매핑
                .collect(Collectors.toList());
                //메서드의 반환형을 맞추기 위해 collect()메서드를 추가 이를 통해 스트림 데이터를 원하느 리스트 자료형으로 반환할 수 있다.
        //서비스 코드의 for()문을 스트림으로 개선했다.
        // 스트림은 자바의 컬렉션 즉 리스트와 해시맵 등 데이터 묶음을 요소별로 순차적으로 조작하는데 좋다.
        //스트림 특징 3가지 1. 원본 데이터를 읽기만 하고 변경 x 2.정렬된 겨로가를 컬렉션이나 배열에 담아 반환 3.내부 반복문으로, 반복문이 코드상 노출x
        //스트림 관련 학습 필요시 자바 문법 중 스트림과 람다식 복습
    }
    @Transactional //create 메서드는 DB내용을 바꾸기 때문에 실패할 경우를 대비해야 한다. 실패시 롤백
    public CommentDto create(Long articleId, CommentDto dto) {
        //1. 게시글 조회 및 에외 발생
        Article article = articleRepository.findById(articleId) //부모 게시글 (Article) 가져오기
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!"+"" +
                        "대상 게시글이 없습니다")); // 없으면 에러 메세지 출력
        //orElseThrow 메서드는 Optional 객체에 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 전달값으로 보낸 예외를 발생

        //2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        //DTO랑 게시글 엔티티를 입력받아 댓글 엔티티를 만든다.
        //createComment() -> 엔티티 메서드 생성()

        //3. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);

        //4. DTO로 변환해 반환
        return CommentDto.createCommentDto(created);
        // created 엔티티를 DTO로 변환 후 반환한다. 새로운 DTO로 변환해 컨트롤러에 반환한다.
    }

}
