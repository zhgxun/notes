package github.banana;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 生成一个序列化的类版本号
	 */
	private static final long serialVersionUID = 5386922947918687341L;

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

	@Override
	public String toString() {
		return "<User: id = " + id + ", name= " + name + ", age = " + age + ">";
	}
}
