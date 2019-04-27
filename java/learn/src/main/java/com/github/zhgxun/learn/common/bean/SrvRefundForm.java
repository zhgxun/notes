package com.github.zhgxun.learn.common.bean;

import lombok.Data;

@Data
public class SrvRefundForm {
    // 仅限制小驴商家使用, 固定值传65
    private Long partnerId;
    // 申请的订单, 多个逗号分隔
    private String orderId;
    // 操作标识, 1为申请退款, 0为申请取消退款
    private int opCode;
}
