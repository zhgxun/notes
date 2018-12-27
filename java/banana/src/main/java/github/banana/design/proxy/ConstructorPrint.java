package github.banana.design.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ConstructorPrint {

    public static void main(String[] args) {
        // 动态生成代理类
        // ClassLoader:  每一个Class都必须有一个类加载器加载进来的，比如每个人都有一个妈妈。
        // 既然需要JVM动态生成Java类，所以要为动态生成类的字节码指定类加载器
        // Class Interfaces: 动态生成的字节码实现了哪些接口
        // java.util.Collections is not an interface
        // 使用jdk动态代理类必须是一个接口
        Class clazz = Proxy.getProxyClass(Dao.class.getClassLoader(), Dao.class);
        Constructor[] constructors = clazz.getConstructors();
        // 遍历构造方法
        System.out.println("构造方法...");
        for (Constructor constructor : constructors) {
            // 构造方法的名字
            String name = constructor.getName();
            StringBuilder sb = new StringBuilder(name);
            sb.append("(");
            // 获取每个构造方法的参数类型
            Class[] paramTypes = constructor.getParameterTypes();
            for (Class paramType : paramTypes) {
                sb.append(paramType.getName()).append(",");
            }
            if (paramTypes.length >= 1) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")");
            System.out.println(sb.toString());
        }

        // 遍历类的所有方法
        System.out.println("接口方法...");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            // 构造方法的名字
            String name = method.getName();
            StringBuilder sb = new StringBuilder(name);
            sb.append("(");
            // 获取每个构造方法的参数类型
            Class[] paramTypes = method.getParameterTypes();
            for (Class paramType : paramTypes) {
                sb.append(paramType.getName()).append(",");
            }
            if (paramTypes.length >= 1) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")");
            System.out.println(sb.toString());
        }
    }
}
