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
@ConfigurationProperties("address-offline")
@PropertySource(name = "address-offline", value = {"classpath:address-offline.yml"}, factory = YamlUtils.class)
public class AddressOfflineConfig {
    private Map<Integer, AddressBaseInfo> addressMap;
}
