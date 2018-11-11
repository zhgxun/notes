package github.banana.demo;

/**
 * 北京城市属性
 * 
 * Java规定一个.java文件只能包含一个public class，所以必须分成两个文件
 * 
 * @author zhgxun
 *
 */
public class BeiJin {

	public static void main(String[] args) {
		City beiJin = new City("北京", 39.903, 116.401);
		beiJin.setName(" 北京 ", "有一个首都国际机场");
		// beiJin.latitude = 39.903;
		// beiJin.longitude = 116.401;

		System.out.println(beiJin);
		System.out.println(beiJin.getName());
		System.out.println(beiJin.latitude);
		System.out.println(beiJin.longitude);
	}

}
