package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //Junit 실행자 대신에 SpringRunner라는 스프링 실행자 실행. 스프링 부트 테스트와 Junit 사이의 연결자
@WebMvcTest //JPA 기능이 작동하지 않음. 외부 연동과 관련된 부분만 활성화
public class HelloControllerTest {
    @Autowired //스프링이 관리하는 빈을 주입 받는다.
    private MockMvc mvc; //웹 API 테스트할 떄 사용. 스프링 MVC 테스트의 시작점

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 /hello주소로 http GET 요청
                .andExpect(status().isOk()) //HTTP Header의 Status를 검증
                .andExpect(content().string(hello)); //응답 본문의 내용을 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name) //API 테스트할 때 사용될 요청 파라미터 설정. 값은 String만 허용
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //JSON 응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드면 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}