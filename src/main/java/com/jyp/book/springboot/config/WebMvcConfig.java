package com.jyp.book.springboot.config;

import com.jyp.book.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        System.out.println("addArgumentResolvers____"+argumentResolvers);
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
/*
* LoginUserArgumentResolver가 스프링에서 인식될 수 있도록 WebMvcConfigurer에 추가!
* HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의 addArgumentResolvers()를 통해 추가해야한다!
* 다른 HadlerMethodArgumentResolver가 필요하다면 같은 방식으로 추가해 주면 된다.
* */