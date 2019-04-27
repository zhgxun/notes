package com.github.zhgxun.learn.controller;

import com.github.zhgxun.learn.common.util.JsonUtil;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BodyController {

    @PostMapping("body")
    public Param test(@RequestParam String data) {
        System.out.println("控制器");
        Param param = JsonUtil.fromJson(data, Param.class);
        return param;
    }

    @PostMapping("body2")
    public Param test1(@RequestBody Param param) {
        return param;
    }
}

@Data
class Param {
    private String name;
    private int age;
}
