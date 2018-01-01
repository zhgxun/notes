package github.banana;

public class User implements Comparable<User> {

    // 用户属性
    private long id;
    private String name;
    private int age;

    public User(long id, String name, int age) {
        this.id = id;
        this.name = name.trim();
        this.age = age;    
    }

    public User(String name, int age) {
        this.name = name.trim();
        this.age = age;
    }
    
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "<User: id = " + id + ", name= " + name + ", age = " + age + ">";
    }

    @Override
    public int compareTo(User o) {
        return o.name.compareTo(this.name);
    }
}
