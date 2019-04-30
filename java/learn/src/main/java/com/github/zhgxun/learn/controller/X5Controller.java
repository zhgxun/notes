package com.github.zhgxun.learn.controller;

import com.github.zhgxun.learn.common.aop.annotation.X5;
import com.github.zhgxun.learn.common.bean.User;
import com.github.zhgxun.learn.common.util.ApiX5ResponseUtil;
import com.github.zhgxun.learn.common.util.JsonUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class X5Controller {

    @X5
    @PostMapping("/x5/user")
    public ApiX5ResponseUtil<User> user(@RequestParam String data) {
        try {
            return ApiX5ResponseUtil.success(JsonUtil.fromJson(data, User.class));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiX5ResponseUtil.fail(404, e.getMessage());
        }
    }
}
