package com.jyp.book.springboot.service.posts;

import com.jyp.book.springboot.domain.posts.Posts;
import com.jyp.book.springboot.domain.posts.PostsRespository;
import com.jyp.book.springboot.web.dto.PostsListResponseDto;
import com.jyp.book.springboot.web.dto.PostsResponseDto;
import com.jyp.book.springboot.web.dto.PostsSaveRequestDto;
import com.jyp.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // ---> 생성자!!!, final이 선언된 ㅣ모든 필드를 인자값으로 하는 생성자를 롬복이 대신 생성해줌
@Service
public class PostsService {
    private final PostsRespository postsRespository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRespository.save(requestDto.toEntity()).getId();
    };

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRespository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent()); //1)
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRespository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) //3)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRespository.findAllDesc().stream()
                .map(PostsListResponseDto::new) //2)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRespository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRespository.delete(posts); //4)
    }
}
/* 스프링에서 Bean을 주입받는 방식 ==> @Autowired, setter, 생성자
*   가장 적합한 방식은 생성자로 주입받는 방식이다.
*   @Autowired는 권장하지 않는다
*   생성자로 Bean 객체를 받도록 하면 @Autowired와 동일한 효과를 볼 수 있다.
* */

/*1) update 기능에서 데이터베이스에 쿼리를 날리는 부분이 없음 --> JPA의 영속성 컨텍스트 때문에 가능
    JPA EntityManager가 활성화된 상태(Spring Data Jpa를 쓴다면 기본 옵션)로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속석 컨텍스트가 유지된 상태임.
    이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다.

    ==> 이 개념을 더티 체킹(dirty checking) 이라 한다
*
*  */
/*2)
* .map(PostsListResponseDto::new) == .map(posts -> new PostsListResponseDto(posts))
* postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메소드임
*
* 3)readOnly = true
* 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선되기 때문에 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용하는것!
* */

/*
* 4)postsRepository.delete(posts)
* JpaRepository에서 이미 delete 메소드를 지원하고 있으니 이를 활용함
* 엔티티를 파라미터로 삭제할 수도 있고, deleteByid 메소드를 이용하면 id로 삭제할 수도 있다
* 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제한다.
* */