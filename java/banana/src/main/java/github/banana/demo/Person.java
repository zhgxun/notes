package github.banana.demo;

import java.util.Objects;

/**
 * 人类
 * 
 * @author zhgxun
 *
 */
public class Person {
	private final String name;
	private final int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	
	// 正确使用Map必须保证
	// 作为Key的对象必须正确覆写equals()方法
	// 作为Key的对象必须正确覆写hashCode()方法
	// 
	// 覆写hashCode
	// 如果两个对象相等，则两个对象的hashCode()必须相等
	// 如果两个对象不相等，则两个对象的hashCode()尽量不相等（可以相等，会造成效率下降）
	// hashCode可以通过Objects.hashCode()辅助方法实现

	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.age);
	}

	@Override
	public boolean equals(Object obj) {
		// 相同对象
		if (obj == this) {
			return true;
		}
		// 父对象
		if (obj instanceof Person) {
			Person p = (Person) obj;
			// 使用对象本身提供的比较方法
			return Objects.equals(p.getName(), this.getName()) && p.getAge() == this.getAge();
		}
		return false;
	}

	@Override
	public String toString() {
		return "(Person: " + name + ", " + age + ")";
	}
}
