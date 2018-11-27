package com.github.zhgxun.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HealthController {

    @RequestMapping(path = "/health", method = RequestMethod.GET)
    public String health() {
        return "success";
    }
}
