package github.banana.view;

/**
 * 字符串的特点
 * <p>
 * 如果使用双引号声明, 则会在字符串常量池中寻找, 如果存在则直接返回引用, 否则创建引用
 * 如果使用new方式声明, 则则堆中创建实例
 * 使用 {@link String#intern()} 内联时, 会将字符串从实例放入字符串常量池中
 * 保证常量池中的字符串唯一
 */
public class StringTest {

    public static void main(String[] args) {
        String s = new String("1");
        s = s.intern();

        String s1 = "1";
        System.out.println(s == s1);

        String s2 = new String("1") + new String("1");
        s2 = s2.intern();
        String s3 = "11";
        System.out.println(s2 == s3);
    }
}
