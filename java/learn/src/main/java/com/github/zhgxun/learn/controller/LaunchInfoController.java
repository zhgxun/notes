package com.github.zhgxun.learn.controller;

import com.github.zhgxun.learn.entity.first.LaunchInfo;
import com.github.zhgxun.learn.service.first.LaunchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class LaunchInfoController {

    @Autowired
    private LaunchInfoService launchInfoService;

    @RequestMapping("/launch/one")
    public LaunchInfo findOne(Long id) {
        return launchInfoService.findOne(id);
    }

    @RequestMapping("/launch/all")
    public List<LaunchInfo> findAll() {
        return launchInfoService.findAll();
    }
}
