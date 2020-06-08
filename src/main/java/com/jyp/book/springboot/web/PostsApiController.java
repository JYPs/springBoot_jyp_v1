package com.jyp.book.springboot.web;

import com.jyp.book.springboot.service.posts.PostsService;
import com.jyp.book.springboot.web.dto.PostsResponseDto;
import com.jyp.book.springboot.web.dto.PostsSaveRequestDto;
import com.jyp.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}