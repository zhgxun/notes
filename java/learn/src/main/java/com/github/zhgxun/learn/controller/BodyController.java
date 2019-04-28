package com.github.zhgxun.learn.controller;

import com.github.zhgxun.learn.common.util.ApiX5ResponseUtil;
import com.github.zhgxun.learn.common.util.JsonUtil;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BodyController {

    @PostMapping("body")
    public ApiX5ResponseUtil<Param> test(@RequestParam String data) {
        return ApiX5ResponseUtil.success(JsonUtil.fromJson(data, Param.class));
    }

    @PostMapping("body2")
    public ApiX5ResponseUtil<Param> test1(@RequestBody Param param) {
        return ApiX5ResponseUtil.success(param);
    }
}

@Data
class Param {
    private String name;
    private int age;
}
