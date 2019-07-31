package github.banana.design.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("o: " + o.getClass().getName());
        System.out.println("method: " + method.getName());
        System.out.println("objects: " + objects.length);
        System.out.println("methodProxy: " + methodProxy.getSuperName());
        return methodProxy.invokeSuper(o, objects);
    }
}
