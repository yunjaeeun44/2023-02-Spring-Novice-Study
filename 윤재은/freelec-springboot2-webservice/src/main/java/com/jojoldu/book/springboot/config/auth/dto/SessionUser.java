package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    //엔티티에 직렬화를 넣는 경우 자식 엔티티가 직렬화 엔티티가 되는 등의 성능 이슈, 부수 효과 발생. 따라서 직렬화 기능을 가진 세션 DTO 생성
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
