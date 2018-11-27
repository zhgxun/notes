package com.github.zhgxun.learn.dao.first;

import com.github.zhgxun.learn.entity.first.LaunchInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LaunchInfoDao {

    LaunchInfo findOne(@Param("id") Long id);

    List<LaunchInfo> findAll();

    List<LaunchInfo> findAny(@Param("id") Long id, @Param("name") String name);

    List<LaunchInfo> findIn(List<Long> ids);
}
