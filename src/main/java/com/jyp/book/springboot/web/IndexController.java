package com.jyp.book.springboot.web;

import com.jyp.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }
    /*
    * 마스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다
    * 앞의 경로 :  src/main/resources/templates
    * 뒤의 확장자 : .mustache
    * src/main/resources/templates/index.mustache로 전환되어 View Resolver가 처리하게 된다ㅣ
    * View Resolver는 URL 요청의 결과를 전달할 타입고 값을 지정하는 관리자 격
    * */

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) { //1)
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }
    /*1)Model
     * 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다
     * postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다
     * */

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
