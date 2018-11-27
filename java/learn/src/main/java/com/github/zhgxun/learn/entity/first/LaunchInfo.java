package com.github.zhgxun.learn.entity.first;

import lombok.Data;

@Data
public class LaunchInfo {
    private long id;
    private long categoryId;
    private long psSourceId;
    private int isShadow;
    private int isStatProvider;
    private String launchCategoryName;
}
