package github.banana.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理, jdk动态代理主要依赖反射实现
 */
public class ProxyTest {

    public static void main(String[] args) {
        HelloImpl impl = new HelloImpl();
        InvocationHandlerTest handlerTest = new InvocationHandlerTest(impl);
        // 创建代理对象, 而非真正的实现对象
        Hello proxy = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(), HelloImpl.class.getInterfaces(), handlerTest);
        proxy.say("This is a proxy test.");
    }
}

interface Hello {
    void say(String msg);
}

class HelloImpl implements Hello {

    @Override
    public void say(String msg) {
        System.out.println(msg);
    }
}

/**
 * Jdk 动态代理只需实现对应的方法即可
 */
class InvocationHandlerTest implements InvocationHandler {

    private Object object;

    InvocationHandlerTest(Object o) {
        object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(object, args);
    }
}
