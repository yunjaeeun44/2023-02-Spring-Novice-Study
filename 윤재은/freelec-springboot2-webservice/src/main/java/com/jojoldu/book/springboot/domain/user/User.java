package com.jojoldu.book.springboot.domain.user;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //롬복의 어노테이션. 클래스 내 모든 필드의 Getter 메소드 자동 생성. Entity 클래스는 Setter 메소드 금지!
@NoArgsConstructor //롬복의 어노테이션. 기본 생성자 자동 추가
@Entity //JPA의 어노테이션. 테이블과 링크될 클래스임을 나타냄. 언더스코어 네이밍으로 테이블 이름 매칭
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING) //JPA로 데이터베이스로 저장할 때 Enum값을 문자열로 저장
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
