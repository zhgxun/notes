package github.banana.rmi.server;

import github.banana.rmi.api.DemoService;

import java.util.Date;

public class DemoServiceImpl implements DemoService {

    @Override
    public String desc(String s) {
        return String.format("%s: %s", new Date(), s);
    }
}
