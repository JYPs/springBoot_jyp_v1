package com.jyp.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA Auditing 활성화, 나중에 테스트 할때는 java.lang.IllegalStateException: Failed to load ApplicationContext
// 발생 시킴. @EnableJpaAuditing를 사용하기 위해선 최소 하나의 @Entity 클래스가 필요하다. @WebMvcTest에서는 당연히 없음!
// 대신 JpaConfig를 생성하여 JPA Auditing을 활성화 시킴
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
