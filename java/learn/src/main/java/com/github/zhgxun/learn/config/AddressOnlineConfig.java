package com.github.zhgxun.learn.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties("address-online")
@PropertySource(name = "address-online", value = {"classpath:address-online.yml"}, factory = YamlUtils.class)
public class AddressOnlineConfig {
    private Map<Integer, AddressBaseInfo> addressMap;
}
