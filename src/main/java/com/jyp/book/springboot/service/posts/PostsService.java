package com.jyp.book.springboot.service.posts;

import com.jyp.book.springboot.domain.posts.Posts;
import com.jyp.book.springboot.domain.posts.PostsRespository;
import com.jyp.book.springboot.web.dto.PostsResponseDto;
import com.jyp.book.springboot.web.dto.PostsSaveRequestDto;
import com.jyp.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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