package com.github.zhgxun.learn.config;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;

public class YamlUtils extends DefaultPropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        Assert.notNull(name, "Custom profile.yml file name is null");
        Assert.hasLength(name, "Custom profile.yml file name is empty");
        List<PropertySource<?>> propertySources = new YamlPropertySourceLoader().load(name, resource.getResource());
        return propertySources.get(0);
    }
}
