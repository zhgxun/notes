package com.github.zhgxun.learn.entity;

import lombok.Data;

@Data
public class Post {

    private long id;
    private String title;
    private int type;
    private String url;
    private String imageUrl;
    private String summary;
    private long userId;
    private int readCount;
    private String remark;
    private int status;
    private int ctime;
    private int utime;
}
