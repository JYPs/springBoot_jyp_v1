package com.jyp.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRespository extends JpaRepository<Posts, Long> {
    // 인터페이스로 생성 후 JpaRepository<Entity 클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성됨
}
