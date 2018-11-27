package com.github.zhgxun.learn.entity.second;

import lombok.Data;

import java.util.Date;

@Data
public class Budget {
    private long id;
    private long budgetId;
    private double dailyBudget;
    private int type;
    private long userId;
    private Date createTime;
    private Date updateTime;
    private int creator;
    private int updater;
    private int version;
}
