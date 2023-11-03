package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //스프링 부트의 자동설정, 스프링 Bean 읽기, 생성을 자동으로 설정
public class Application { //메인 클래스. 프로젝트의 최상단에 위치해야 함.
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //내장 WAS 실행
    }
}
