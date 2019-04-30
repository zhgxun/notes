package com.github.zhgxun.learn.common.aop;

import com.github.zhgxun.learn.common.errors.ParamException;
import com.github.zhgxun.learn.common.errors.ParseException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@Aspect
@Component
@Slf4j
public class X5Aspect {

    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(com.github.zhgxun.learn.common.aop.annotation.X5)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        String data = request.getParameter("data");
        log.info("Aspect X5 Request origin data: {}", data);
        if (StringUtils.isEmpty(data)) {
            log.error("Request param data content is empty: {}", data);
            throw new ParamException("Request param data content is empty");
        }
        String decodeData = new String(Base64.getDecoder().decode(data));
        log.info("Aspect X5 Request base64 decode data: {}", decodeData);

        // 注意以下的解析, 因为x5的局限, body 必须保持为一个字符串, 提前解析成bean将导致不一致的问题
        JsonObject jsonObject;
        try {
            jsonObject = new JsonParser().parse(decodeData).getAsJsonObject();
        } catch (IllegalStateException e) {
            log.error("Request param data parse error: {}", e.getMessage());
            throw new ParseException("Request param data parse error");
        }
        check(jsonObject);

        String body = jsonObject.get("body").getAsString();
        Object[] args = joinPoint.getArgs();
        // 参数数量是未知的, 但是x5的标准是只有一个data参数, 这个是约定俗称的
        // 但是如果有多个参数, 则会被覆盖而丢弃, 这点需要注意
        if (!StringUtils.isEmpty(body) && args != null && args.length > 0) {
            args[0] = body;
            return joinPoint.proceed(args);
        }
        return joinPoint.proceed();
    }

    private void check(JsonObject data) {
        JsonObject header = data.getAsJsonObject("header");
        if (header == null) {
            throw new ParamException("Request header is null");
        }
        String appId = header.get("appid").getAsString();
        if (StringUtils.isEmpty(appId)) {
            throw new ParamException("Request header appid is empty");
        }
        String sign = header.get("sign").getAsString();
        if (StringUtils.isEmpty(sign)) {
            throw new ParamException("Request header sign is empty");
        }

        String body = data.get("body").getAsString();
        if (body == null) {
            throw new ParamException("Request body is null");
        }
        checkSign(appId, body, sign);
    }

    private void checkSign(String appId, String body, String sign) {
        String key = "test";
        if (!sign.equalsIgnoreCase(DigestUtils.md5DigestAsHex((appId + body + key).getBytes()))) {
            throw new ParamException("Request param sign result error");
        }
    }
}
