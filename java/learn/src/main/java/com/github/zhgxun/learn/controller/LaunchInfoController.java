package com.github.zhgxun.learn.controller;

import com.github.zhgxun.learn.entity.first.LaunchInfo;
import com.github.zhgxun.learn.service.first.LaunchInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
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

    @RequestMapping("/launch/any")
    public List<LaunchInfo> findAny(Long id, String name) {
        log.info("id: {}, name: {}", id, name);
        return launchInfoService.findAny(id, name);
    }

    @RequestMapping("/launch/in")
    public List<LaunchInfo> findIn(String ids) {
        return launchInfoService.findIn(
                Arrays.stream(ids.split("\\,")).map(Long::parseLong).collect(Collectors.toList())
        );
    }
}
