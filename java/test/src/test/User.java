package test;

/**
 * 用户信息
 * 
 * @author zhgxun
 *
 */
public class User {
	public long id;
	public String name;
	public int age;

	public User() {

	}

	public User(long id, String name, int age) {
		this.id = id;
		this.name = name.trim();
		this.age = age;
	}

	public User(String name, int age) {
		this.name = name.trim();
		this.age = age;
	}

	@Override
	public String toString() {
		return "<String: id= " + this.id + ", name= " + this.name + ", age= " + this.age + ">";
	}
}
