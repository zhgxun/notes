package github.banana.demo;

/**
 * 城市
 * 
 * @author zhgxun
 *
 */
public class City {
	// 城市名称
	private String name;

	// 经度
	double latitude;

	// 维度
	double longitude;

	/**
	 * 构造方法
	 * 
	 * 1. 构造方法可以在创建对象实例时初始化对象实例
	 * 
	 * 2. 构造方法名就是类名
	 * 
	 * 3. 构造方法的参数没有限制
	 * 
	 * 4. 构造方法没有返回值, 也没有void
	 * 
	 * 5. 必须用new操作符调用构造方法
	 * 
	 * 6. 编译器会为没有定义构造方法的类自动生成默认构造方法
	 * 
	 * 7. 初始化顺序: 初始化字段, 执行构造方法代码
	 * 
	 * 8. 可以定义多个构造方法, 编译器根据参数自动判断使用哪个
	 * 
	 * 9. 可以在一个构造方法中通过this()调用另一个构造方法
	 * 
	 * @param name
	 * @param latitude
	 * @param longitude
	 */
	public City(String name, double latitude, double longitude) {
		this.setName(name);
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * 方法重载
	 * 
	 * 1. 多个方法的方法名相同
	 * 
	 * 2. 但各自的参数不同, 个数, 类型, 位置不同
	 * 
	 * 3. 方法返回类型通常相同
	 * 
	 * 4. 方法重载的目的是方便调用
	 * 
	 * 5. 重载方法应该完成相同的功能
	 * 
	 * 6. 不要去交换参数顺序
	 */

	/**
	 * 给城市命名
	 * 
	 * @param name
	 * @throws NullPointException
	 */
	public void setName(String name) throws NullPointerException {
		if (name == null) {
			throw new NullPointerException();
		}
		this.name = name.trim();
	}

	/**
	 * 名字拼接
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public void setName(String firstName, String lastName) {
		if (firstName == null) {
			throw new NullPointerException();
		}
		if (lastName == null) {
			throw new NullPointerException();
		}
		this.name = firstName.trim() + lastName.trim();
	}

	/**
	 * 获取城市的名字
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}
}
