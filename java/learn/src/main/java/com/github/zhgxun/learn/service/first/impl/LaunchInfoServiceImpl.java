package com.github.zhgxun.learn.service.first.impl;

import com.github.zhgxun.learn.dao.first.LaunchInfoDao;
import com.github.zhgxun.learn.entity.first.LaunchInfo;
import com.github.zhgxun.learn.service.first.LaunchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LaunchInfoServiceImpl implements LaunchInfoService {

    @Autowired
    private LaunchInfoDao launchInfoDao;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public LaunchInfo findOne(Long id) {
        return launchInfoDao.findOne(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<LaunchInfo> findAll() {
        return launchInfoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<LaunchInfo> findAny(Long id, String name) {
        return launchInfoDao.findAny(id, name);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<LaunchInfo> findIn(List<Long> ids) {
        return launchInfoDao.findIn(ids);
    }
}
