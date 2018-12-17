package com.github.zhgxun.simple.hello.controller;

import com.github.zhgxun.simple.framework.annotation.Autowired;
import com.github.zhgxun.simple.framework.annotation.Controller;
import com.github.zhgxun.simple.framework.annotation.RequestMapping;
import com.github.zhgxun.simple.framework.annotation.RequestParam;
import com.github.zhgxun.simple.hello.service.HelloService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/test")
    public void test(HttpServletRequest req, HttpServletResponse res, @RequestParam("name") String name) {
        String result = helloService.parse(name);
        try {
            res.getWriter().write(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
