package com.github.zhgxun.learn.common.util;

import com.github.zhgxun.learn.common.errors.ParamException;
import com.github.zhgxun.learn.common.errors.ParseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionUtil {

    @ExceptionHandler(value = Exception.class)
    public ApiX5ResponseUtil<String> exception(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if (e instanceof ParamException
                || e instanceof IllegalArgumentException
                || e instanceof ParseException) {
            return ApiX5ResponseUtil.fail(400, e.getMessage());
        }
        return ApiX5ResponseUtil.fail(500, "系统繁忙");
    }
}
