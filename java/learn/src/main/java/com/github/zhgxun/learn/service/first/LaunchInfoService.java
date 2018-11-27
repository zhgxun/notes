package com.github.zhgxun.learn.service.first;

import com.github.zhgxun.learn.entity.first.LaunchInfo;

import java.util.List;

public interface LaunchInfoService {

    LaunchInfo findOne(Long id);

    List<LaunchInfo> findAll();
}
