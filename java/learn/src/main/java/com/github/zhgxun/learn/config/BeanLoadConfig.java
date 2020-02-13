package com.github.zhgxun.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class BeanLoadConfig {

    @Autowired
    private Environment env;

    @Autowired
    private AddressOfflineConfig addressOfflineConfig;

    @Autowired
    private AddressOnlineConfig addressOnlineConfig;

    @Bean
    public AddressConfig addressConfig() {
        if (env.getActiveProfiles()[0].equals("staging")) {
            return new AddressConfig(addressOfflineConfig.getAddressMap());
        }
        return new AddressConfig(addressOnlineConfig.getAddressMap());
    }
}
