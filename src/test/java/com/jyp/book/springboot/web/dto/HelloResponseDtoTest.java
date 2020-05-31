package com.jyp.book.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name); // 1) 2)
        assertThat(dto.getAmount()).isEqualTo(amount);

    }
}
/*1)
* assertThat ==> assertj라는 테스트 검증 라이브러리의 검증 메소드
* 검증하고 싶은 대상을 메소드 인자로 받는다
*
* Junit의 기본 assertThat이 아닌 assertj의 assertTaht을 사용함
* ==> 장점
*           CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않는다
*               -Junit의 assertThat을 쓰게 되면 is()와 같이 CoreMatchers 라이브러리가 필요함
*           자동완성이 좀 더 확실하게 지원된다
*               -IDE에서는 CoreMatchers와 같은 Matcher 라이브러리의 자동완성 지원이 약함
*
* 2)
* isEqualTo
* assertj의 동등 비교 메서드
*
*
* */