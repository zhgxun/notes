package github.banana.rmi.server;

import github.banana.rmi.api.SumService;

public class SumServiceImpl implements SumService {

    @Override
    public int getAdd(int a, int b) {
        return a + b;
    }
}
