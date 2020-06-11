package com.jyp.book.springboot.config.auth.dto;

import com.jyp.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
// 이건 직렬화 기능을 가진 세션 Dto로 이해하자 --> 나중에 이 세션유저를 직렬화 하여 세션에 저장할듯???
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
