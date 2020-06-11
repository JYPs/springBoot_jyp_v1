package com.jyp.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
/*
* 1)Optional
* Optional<T> 클래스는 Integer나 Double 클래스처럼 'T'타입의 객체를 포장해 주는 래퍼 클래스(Wrapper class)입니다.
* 따라서 Optional 인스턴스는 모든 타입의 참조 변수를 저장할 수 있습니다.
*
* 2) 그러면 래퍼 클래스란 뭐냐??
*    일단 의미는 이거다 --> 기본 자료형(primitive data types)에 대한 클래스 표현
*    ex)       문자열 -> 기본형     vs   문자열 -> wrapper 클래스
*          Integer.parseInt("100") , Integer.valueOf("100") 이 둘의 차이점 생각해보기
*   래퍼 클래스는 언제 사용?
*   1. 매개변수로 객체가 요구 될때.
    2. 기본형 값이 아닌 객체로 저장해야 할 때.
    3. 객체간의 비교가 필요할 때.
*   https://includestdio.tistory.com/1 <-- int vs integer 비교인데 꼭 보자
* */