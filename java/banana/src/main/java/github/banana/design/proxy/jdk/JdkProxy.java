package github.banana.design.proxy.jdk;

import github.banana.design.proxy.common.Request;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代理实现
 */
public class JdkProxy implements InvocationHandler {

    private Request object;

    public JdkProxy(Request request) {
        this.object = request;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 该处 proxy 是一个很宽泛的对象, 需要捕捉自己需要的代理对象, 一种方法是直接指定自己需要的代理对象
        // com.sun.proxy.$Proxy0
        System.out.println(proxy.getClass().getName());
        return method.invoke(object, args);
    }
}
