package github.banana.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jackson json 解析工具
 */
public class JacksonUtils {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        // 反序列化遇见未知属性时不抛出异常, 默认抛出异常
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // ... 其它需要配置的均可在此配置
        // 更多配置参考 https://github.com/FasterXML/jackson-databind/wiki/Deserialization-Features
    }

    /**
     * 普通对象序列化为字符串
     *
     * @param o 普通对象
     * @return 序列化后的字符串
     * @throws Exception 序列化异常
     */
    public static String toJson(Object o) throws Exception {
        return mapper.writeValueAsString(o);
    }

    /**
     * 普通对象反序列化, 内部可嵌套
     *
     * @param json  原始json字符串
     * @param clazz 期待解析的目标bean对象
     * @param <T>   目标对象
     * @return 解析后的泛型目标对象
     * @throws Exception 解析异常, 调用捕获
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws Exception {
        return mapper.readValue(json, clazz);
    }

    /**
     * 引用对象解析反序列化, 比如解析成 {@link List} {@link java.util.Map}
     * <p>
     * <code>
     * TypeReference ref = new TypeReference<List<Integer>>() {
     * }
     * </code>
     * 该引用比较难构造, 可直接拷贝
     *
     * @param json 原始json字符串, 典型如json数组
     * @param ref  需要借助目标引用类型
     * @param <T>  目标对象
     * @return 解析后的目标泛型对象
     * @throws Exception 解析异常
     */
    public static <T> T fromJson(String json, TypeReference ref) throws Exception {
        return mapper.readValue(json, ref);
    }

    public static void main(String[] args) throws Exception {
        // 1. 普通对象解析
        String json = "{\"name\":\"张三\",\"age\":19,\"desc\":\"单身屌丝一枚\",\"id\":10}";
        System.out.println(JacksonUtils.fromJson(json, User.class));

        User user = new User();
        user.setName("李四");
        user.setAge(20);
        user.setDesc("不知好歹的低年级小学生");
        System.out.println(JacksonUtils.toJson(user));

        // 2. 复杂对象解析
        Student student = new Student();
        student.setClassName("高中一年级");
        student.setYear(2019);
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User u = new User();
            u.setName("张三" + i);
            u.setAge(18 + i);
            u.setDesc("备注: " + i);
            u.setTest("描述: " + i);
            users.add(u);
        }
        student.setUsers(users);
        System.out.println(JacksonUtils.toJson(student));

        String s = "{\"more\":\"多余的属性\",\"className\":\"高中一年级\",\"year\":2019,\"users\":[{\"name\":\"张三0\",\"age\":18,\"desc\":\"备注: 0\",\"test\":\"描述: 0\"},{\"name\":\"张三1\",\"age\":19,\"desc\":\"备注: 1\",\"test\":\"描述: 1\"},{\"name\":\"张三2\",\"age\":20,\"desc\":\"备注: 2\",\"test\":\"描述: 2\"},{\"name\":\"张三3\",\"age\":21,\"desc\":\"备注: 3\",\"test\":\"描述: 3\"},{\"name\":\"张三4\",\"age\":22,\"desc\":\"备注: 4\",\"test\":\"描述: 4\"}]}";
        System.out.println(JacksonUtils.fromJson(s, Student.class));

        // 3. List
        String list = "[{\"name\":\"张三\",\"age\":18,\"desc\":\"小妖精\"},{\"name\":\"李四\",\"age\":19,\"desc\":\"小屁孩\"},{\"name\":\"王五\",\"age\":17,\"desc\":\"狐狸精\"}]";
        List<User> userList = JacksonUtils.fromJson(list, new TypeReference<List<User>>() {
        });
        System.out.println(userList);
        List<User> users1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user1 = new User();
            user1.setName("李四-" + i);
            user1.setAge(10 + i);
            user1.setDesc("上学中-" + i);
            users1.add(user1);
        }
        System.out.println(JacksonUtils.toJson(users1));

        // 4. Map
        Map<String, User> userMap = new HashMap<>();
        for (int i = 1; i < 5; i++) {
            User user1 = new User();
            user1.setName("王五-" + i);
            user1.setAge(20 + i);
            user1.setDesc("种白菜: " + i + " 年");
            userMap.put(user1.getName(), user1);
        }
        System.out.println(JacksonUtils.toJson(userMap));

        String map = "{\"王五-1\":{\"name\":\"王五-1\",\"age\":21,\"desc\":\"种白菜: 1 年\",\"test\":null},\"王五-2\":{\"name\":\"王五-2\",\"age\":22,\"desc\":\"种白菜: 2 年\",\"test\":null},\"王五-3\":{\"name\":\"王五-3\",\"age\":23,\"desc\":\"种白菜: 3 年\",\"test\":null},\"王五-4\":{\"name\":\"王五-4\",\"age\":24,\"desc\":\"种白菜: 4 年\",\"test\":null}}";
        Map<String, User> map1 = JacksonUtils.fromJson(map, new TypeReference<Map<String, User>>() {
        });
        System.out.println(map1);
    }
}

@Data
class User {
    private String name;
    private Integer age;
    private String desc;
    private String test;
}

@Data
class Student {
    private String className;
    private Integer year;
    private List<User> users;
}
