package github.banana.design.proxy.cglib;

import github.banana.design.proxy.common.Request;
import github.banana.design.proxy.common.RequestImpl;
import net.sf.cglib.proxy.Enhancer;

public class CglibUser {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RequestImpl.class);
        enhancer.setCallback(new CglibProxy());
        Request request = (Request) enhancer.create();
        request.params();
    }
}
