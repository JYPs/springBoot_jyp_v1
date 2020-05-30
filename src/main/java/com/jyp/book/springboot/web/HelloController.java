package com.jyp.book.springboot.web;

import com.jyp.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, // 1)
                                     @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}

/*1)
* ReqeustParam
*   외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
*   외부에서 name (@RequestParam("name")) 이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장하게 된다
* */
