package github.banana.rpc;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserServer {

    @Override
    public User query(String name) {
        Map<String, User> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(i + 10);
            user.setName("name_" + i);
            user.setDesc("desc_" + i);
            map.put(user.getName(), user);
        }
        return map.getOrDefault(name, null);
    }
}
