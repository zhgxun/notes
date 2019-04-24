package com.github.zhgxun.learn.common.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class TraceBeanV2 {

    public TraceBeanV2(String acceptStation, String acceptTime) {
        this.acceptStation = acceptStation;
        this.acceptTime = acceptTime;
    }

    // 快递链路信息, 例如 签收成功, 签收人: 杨凯
    @SerializedName("acceptstation")
    private String acceptStation;

    // 当前记录时间 2017-08-16 11:09:01
    @SerializedName("accepttime")
    private String acceptTime;
}
