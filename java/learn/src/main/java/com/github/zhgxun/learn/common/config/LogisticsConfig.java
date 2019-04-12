package com.github.zhgxun.learn.common.config;

import com.github.zhgxun.learn.common.util.YamlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "all-logistics")
@PropertySource(name = "logistics.yml", value = {"classpath:logistics.yml"}, factory = YamlUtil.class)
public class LogisticsConfig {
    private Map<String, Logistics> logistics;
}

@Data
class Logistics {
    private int id;
    private String bizCode;
    private String bizName;
}
