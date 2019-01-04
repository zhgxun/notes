package com.github.zhgxun.learn.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderRegisterEvent extends ApplicationEvent {

    private OrderBean orderBean;

    /**
     * 继承ApplicationEvent事件, 需要提供至少一个构造方法给父类初始化, 实现类可以复写需要的构造方法
     * <p>
     * 后续使用完全可以用事件来判断 if (event instanceof OrderBean) {} 来知晓是否是关心的事件, 也可以明确关心事件,
     * 其它监听也不受影响
     *
     * @param source 事件源, 提供提供给父类事件, 不确定时指定本对象 this 即可
     */
    public OrderRegisterEvent(Object source) {
        super(source);
    }

    /**
     * 构造方法仅仅是演示用, 按项目需求需要提供何种形式即可, 不限定
     * <p>
     * spring发布事件, 是不关心事件的接受者是否能处理该事件的, 类似广播模式, 需要事件监听者选择性的处理对应的时间
     *
     * @param source 事件源
     * @param bean   自定义的附加信息, 比如为了表示该事件属于何种bean, 类型等, 让事件监听者判断, 是否需要处理该事件即可
     */
    public OrderRegisterEvent(Object source, OrderBean bean) {
        super(source);
        this.orderBean = bean;
    }
}
