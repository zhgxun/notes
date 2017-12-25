package test;

public class Cla {

	/**
	 * class本身是一种数据类型（Type），class/interface的数据类型是Class，JVM为每个加载的class创建唯一的Class实例。
	 * 
	 * Class实例包含该class的所有信息，通过Class实例获取class信息的方法称为反射（Reflection）
	 * 
	 * VM总是动态加载class，可以在运行期根据条件控制加载class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Class<String> cla = String.class;
		System.out.println(cla);

		// 获取class的实例
		String s = "Hello";
		System.out.println(s.getClass());

		// 需要捕获异常
		try {
			System.out.println(Class.forName("java.lang.String"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// instanceOf不但匹配当前类型， 还匹配当前类型的子类
		Integer m = new Integer(123);
		boolean m1 = m instanceof Number;
		System.out.println(m1);
		boolean m2 = m instanceof Integer;
		System.out.println(m2);
		boolean m3 = m.getClass() == Integer.class;
		System.out.println(m3);
		System.out.println(Number.class);

		// 从Class实例获取class信息
		System.out.println(cla.getName());
		System.out.println(cla.getSimpleName());
		System.out.println(cla.getPackage().getName());
		System.out.println(cla.isInterface());
		System.out.println(cla.isArray());
		System.out.println(cla.isEnum());
		System.out.println(cla.isPrimitive());
	}

}
