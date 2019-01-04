package com.github.zhgxun.learn.event;

/**
 * 1. Java的事件机制
 * <p>
 * Java中的事件机制一般包括3个部分: {@link java.util.EventObject}, {@link java.util.EventListener} 和 Source
 * <p>
 * 1.1 java.util.EventObject是事件状态对象的基类, 它封装了事件源对象以及和事件相关的信息, 所有java的事件类都需要继承该类
 * 1.2 java.util.EventListener是一个标记接口, 就是说该接口内是没有任何方法的, 所有事件监听器都需要实现该接口, 事件监听器注册在事件源上,
 * 当事件源的属性或状态改变的时候, 调用相应监听器内的回调方法
 * 1.3 事件源不需要实现或继承任何接口或类, 它是事件最初发生的地方, 因为事件源需要注册事件监听器, 所以事件源内需要有相应的盛放事件监听器的容器
 *
 * 2. Spring的事件
 *
 * ApplicationEvent以及Listener是Spring为我们提供的一个事件监听, 订阅的实现, 内部实现原理是观察者设计模式,
 * 设计初衷也是为了系统业务逻辑之间的解耦, 提高可扩展性以及可维护性
 *
 * 2.1 ApplicationEvent就是Spring的事件接口
 * 2.2 ApplicationListener就是Spring的事件监听器接口, 所有的监听器都实现该接口
 * 2.3 ApplicationEventPublisher是Spring的事件发布接口, ApplicationContext实现了该接口
 * 2.4 ApplicationEventMulticaster就是Spring事件机制中的事件广播器, 默认实现SimpleApplicationEventMulticaster
 *
 * 在Spring中通常是ApplicationContext本身担任监听器注册表的角色, 在其子类AbstractApplicationContext中就聚合了事件广播器
 * ApplicationEventMulticaster和事件监听器ApplicationListener, 并且提供注册监听器的addApplicationListener方法
 *
 *
 */
public class Event {
}
