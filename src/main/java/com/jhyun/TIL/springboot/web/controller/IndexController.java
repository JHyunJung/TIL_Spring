package com.jhyun.TIL.springboot.web.controller;

//import com.jhyun.TIL.springboot.config.auth.LoginUser;
//import com.jhyun.TIL.springboot.config.auth.dto.SessionUser;
import com.jhyun.TIL.springboot.service.PostService;
import com.jhyun.TIL.springboot.service.UserService;
import com.jhyun.TIL.springboot.web.dto.posts.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/user/new")
    public String userNew() {
        return "user-new";
    }
}
