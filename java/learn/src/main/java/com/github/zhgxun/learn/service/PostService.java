package com.github.zhgxun.learn.service;

import com.github.zhgxun.learn.entity.Post;

import java.util.List;

public interface PostService {

    Post getOne(Long id);

    List<Post> list();

    List<Post> titles();
}
