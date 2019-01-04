package com.github.zhgxun.learn.event;

import lombok.Data;

/**
 * 模拟订单模型, 项目中就是一个中立的entity, bean 等, 能起到监听或者可作为事件本体即可
 */
@Data
public class OrderBean {
    private int id;
    private String desc;
}
