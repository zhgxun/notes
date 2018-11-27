package com.github.zhgxun.learn.controller;

import com.github.zhgxun.learn.entity.Post;
import com.github.zhgxun.learn.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("/one")
    public Post one(Long id) {
        return postService.getOne(id);
    }

    @RequestMapping("/list")
    public List<Post> list() {
        return postService.list();
    }

    @RequestMapping("/titles")
    public List<Post> titles() {
        return postService.titles();
    }
}
