package com.github.zhgxun.learn.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressConfig {
    private Map<Integer, AddressBaseInfo> addressMap;
}
