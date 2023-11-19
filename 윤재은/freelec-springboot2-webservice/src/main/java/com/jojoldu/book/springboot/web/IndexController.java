package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController { //머스테치 url에 매핑

    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model){ //서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());
        return "index"; //앞의 경로(~/resource/templates)와 뒤의 파일 확장자(.mustache)는 자동으로 지정된다.
        //ViewResolver: url 요청 결과를 전달할 타입과 값을 지정하는 관리자
    }

    @GetMapping("/posts/save")
    public String postSave(){
        return "posts-save"; ///post/save를 호출하면 post-save.mustache를 호출
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
