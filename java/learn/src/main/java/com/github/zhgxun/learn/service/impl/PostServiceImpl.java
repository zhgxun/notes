package com.github.zhgxun.learn.service.impl;

import com.github.zhgxun.learn.dao.PostDao;
import com.github.zhgxun.learn.entity.Post;
import com.github.zhgxun.learn.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public Post getOne(Long id) {
        return postDao.one(id);
    }
}
