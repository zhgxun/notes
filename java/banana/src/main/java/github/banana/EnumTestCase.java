package github.banana;

public class EnumTestCase {

    public static void main(String[] args) {

        // 遍历枚举类型的标识
        for (EnumTest enumTest : EnumTest.values()) {
            System.out.println(enumTest.name());
        }

        // 获取枚举类型的描述
        System.out.println(EnumTest.A.getIndex());
    }
}
