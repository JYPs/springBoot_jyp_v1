package com.jyp.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //1)
@EntityListeners(AuditingEntityListener.class) //2)
public class BaseTimeEntity {

    @CreatedDate //3)
    private LocalDateTime createdDate;

    @LastModifiedDate //4)
    private LocalDateTime modifiedDate;
}
/*
* BaseTimeEntity클래스는 모든 Entity의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리하는 역할
*
* 1)@MappedSuperclass
*   JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdDate, modifiedDate)도 칼럼으로 인식하도록 함
*
* 2)@EntityListeners(AuditingEntityListener.class)
*   BaseTimeEntity 클래스에 Auditing 기능을 포함시킴
*   Auditing 기능 ==> Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능입니다.
*                   도메인을 영속성 컨텍스트에 저장하거나 조회를 수행한 후에 update를 하는 경우 매번 시간 데이터를 입력하여 주어야 하는데,
*                   audit을 이용하면 자동으로 시간을 매핑하여 데이터베이스의 테이블에 넣어주게 됩니다.
* 3)@CreatedDate
*   Entity가 생성되어 저장될 때 시간이 자동으로 저장된다
* 4)@LastModifiedDate
*   조회한 Entity의 값을 변경할 때 시간이 자동 저장된다
* */
