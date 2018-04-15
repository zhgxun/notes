package github.banana;

public class EnumRangeCase {

    public static void main(String[] args) {
        // 指定一个范围具体值
        int index = 120;
        EnumRange enumRange = EnumRange.getDesc(index);

        // 判断属于何种类型
        System.out.println(enumRange.name());

        // 是否属于PC
        System.out.println(EnumRange.PC.isPC(index));

        // 是否属于WISE
        System.out.println(EnumRange.WISE.isWISE(index));
    }
}
