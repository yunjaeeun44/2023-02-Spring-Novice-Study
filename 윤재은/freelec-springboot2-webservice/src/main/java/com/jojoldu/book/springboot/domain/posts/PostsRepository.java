package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//DB 레이어 접근자(DAO)
public interface PostsRepository extends JpaRepository<Posts, Long> {
    //JpaRepository<Entity 클래스, PK 타입> 상속 시 기본적인 CRUD 메소드 자동으로 생성.
    //Entity 클래스와 Entity Repository는 함께 위치 해야한다.

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") //SpringDataJpa에서 제공하지 않는 메서드는 쿼리로 작성해도 된다.
    List<Posts> findAllDesc();
}
