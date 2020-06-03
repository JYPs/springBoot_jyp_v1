package com.jyp.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRespository extends JpaRepository<Posts, Long> {
    // 인터페이스로 생성 후 JpaRepository<Entity 클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성됨

    // SpringDataJpa에서 제공하지 않는 메소드는 쿼리로 작성해도 된다~
    // 가독성을 위해서 @Query를 사용함
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
// 규모가 있는 프로젝트에서의 조회는 FK의 조인, 복잡한 조건 등으로 인해 이런 Entity클래스만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용한다
// 대표적 예로 querydsl, joop, Mybatis