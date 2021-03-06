package com.jyp.book.springboot.web;

import com.jyp.book.springboot.service.posts.PostsService;
import com.jyp.book.springboot.web.dto.PostsResponseDto;
import com.jyp.book.springboot.web.dto.PostsSaveRequestDto;
import com.jyp.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
// ---> 생성자!!!, final이 선언된 ㅣ모든 필드를 인자값으로 하는 생성자를 롬복이 대신 생성해줌 --> @Autowired로 Bean 객체를 안받아도됨. 생성자로 Bean 주입 받는걸 더 권장
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id; // index.js에서 dataType을 json으로 줘서 리턴 값이 필요함...
        // dataType: json의 의미는 응답을 얻기위해 dataType을 설정했지만 응답이 데이터 유형에서 설정한 것이 아닐경우 발생 ==> jQuery 도큐먼트에서 그러하다~~
    }
}