package com.jyp.book.springboot.web;

import com.jyp.book.springboot.config.auth.LoginUser;
import com.jyp.book.springboot.config.auth.dto.SessionUser;
import com.jyp.book.springboot.service.posts.PostsService;
import com.jyp.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

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
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { //1) , 4)
        model.addAttribute("posts", postsService.findAllDesc());

//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); //2)

        if (user != null) { //3)
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
    /*1)Model
     * 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다
     * postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다
     * */
    /*
    * 2)(SessionUser) httpSession.getAttribute("user")
    *   앞서 작성된 CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
    *   즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있다
    *
    * 3)if (user != null)
    *   세션에 저장된 값이 있을 때만 model에 userName으로 등록
    *   세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게 된다.
    *
    * 4)@LoginUser SessionUser user
    *   기존에 (SessionUser) httpSession.getAttribute("user") 로 가져오던 세션 정보 값이 개선
    *   이제는 어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 가져올 수 있게 됨
    *
    *  */

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
