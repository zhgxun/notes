package github.banana.design.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InvocationHandlerTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Collection proxy = (Collection) Proxy.newProxyInstance(
                Collection.class.getClassLoader(),
                new Class[]{Collection.class},
                new InvocationHandler() {
                    List target = new ArrayList();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        long start = System.currentTimeMillis();
                        Thread.sleep(1000);
                        Object value = method.invoke(target, args);
                        System.out.println("Method: " + method.getName() + "Cost: " + ((System.currentTimeMillis() - start) / 1000) + "ms");
                        return value;
                    }
                }
        );
        proxy.add("a");
        proxy.add("b");
        proxy.add("c");
        System.out.println("Size: " + proxy.size());
    }

    private static void newInstance() throws Exception {
        Class clazz = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        Constructor constructor = clazz.getConstructor(InvocationHandler.class);
        Collection proxy = (Collection) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
        System.out.println(proxy);
        System.out.println(proxy.toString());
        proxy.clear();
    }
}
