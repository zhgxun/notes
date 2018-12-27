package github.banana.design.proxy;

public class UserDao implements Dao {

    @Override
    public void save(String message) {
        System.out.println("保存用户信息: " + message);
    }
}
