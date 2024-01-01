package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter //롬복의 어노테이션. 클래스 내 모든 필드의 Getter 메소드 자동 생성. Entity 클래스는 Setter 메소드 금지!
@NoArgsConstructor //롬복의 어노테이션. 기본 생성자 자동 추가
@Entity //JPA의 어노테이션. 테이블과 링크될 클래스임을 나타냄. 언더스코어 네이밍으로 테이블 이름 매칭
public class Posts extends BaseTimeEntity { //실제 DB의 테이블과 매칭될 클래스. Entity 클래스
    //Entity 클래스를 Request/Response 클래스로 사용해서는 안된다.
    @Id //해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성규칙. auto_increment됨.
    private Long id;

    @Column(length = 500, nullable = false) //테이블의 칼럼을 의미.
    private String title;

    @Column(columnDefinition = "Text", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함. 생성자와 다르게 채워야하는 필드를 명확하게 지정해야 함.
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){ //JPA의 영속성 컨텍스트로 인해 쿼리가 없음. Entity 객체의 값만 변경하면 별도의 Update 쿼리는 필요 없다.
        this.title = title;
        this.content = content;
    }
}
