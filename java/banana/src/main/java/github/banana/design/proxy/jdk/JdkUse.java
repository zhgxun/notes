package github.banana.design.proxy.jdk;

import github.banana.design.proxy.common.Request;
import github.banana.design.proxy.common.RequestImpl;

import java.lang.reflect.Proxy;

public class JdkUse {

    public static void main(String[] args) {
        // 保存生成的字节码类文件
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

        // 测试需要明确生成的代理类来使用, 因为生成代理类其实已经是明确的使用类了
        Request request = (Request) Proxy.newProxyInstance(JdkUse.class.getClassLoader(), new Class[]{Request.class}, new JdkProxy(new RequestImpl()));
        request.params();
    }
}
