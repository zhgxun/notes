package com.github.zhgxun.learn.dao;

import com.github.zhgxun.learn.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper扫描的包需要跟xml配置相对应, 方法名即为xml中的id标识
 */
@Mapper
public interface PostDao {

    Post one(@Param(value = "id") Long id);
}
