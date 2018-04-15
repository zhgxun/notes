package github.banana;

/**
 * 定义一个枚举类
 */
public enum  EnumTest {

    // 枚举类内容, 通常由大写字母标识, 每个枚举类型的说明直接写在括号中
    A("字母A"), B("字母B"), C("字母C"), D("字母D");

    // 枚举类型的说明
    private String index;

    // 枚举类型说明的赋值, 该修饰符默认是private, 不允许其他类型修饰符
    EnumTest(String index) {
        this.index = index;
    }

    // 前端直接通过该公开方法获取枚举类型的值
    public String getIndex() {
        return index;
    }
}
