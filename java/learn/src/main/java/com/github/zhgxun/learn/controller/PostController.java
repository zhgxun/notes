package com.github.zhgxun.learn.controller;

import com.github.zhgxun.learn.entity.Post;
import com.github.zhgxun.learn.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("/post")
    public Post one(Long id) {
        return postService.getOne(id);
    }
}
