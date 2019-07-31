package github.banana.design.proxy.common;

public class RequestImpl implements Request {

    @Override
    public void params() {
        System.out.println("接收参数请求");
    }
}
