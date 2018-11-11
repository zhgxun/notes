package github.banana.demo;

/**
 * 范围类型枚举
 */
public enum EnumRange {

    // 定义枚举类型所属的范围
    PC(100, 500), WISE(501, 1000);

    // 范围的起止
    private int start;
    private int end;

    EnumRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    // 判断是否在该范围内
    public static EnumRange getDesc(int index) {
        for (EnumRange enumRange : EnumRange.values()) {
            if (index >= enumRange.start && index <= enumRange.end) {
                return enumRange;
            }
        }
        throw new IndexOutOfBoundsException("未知的范围");
    }

    // 也可以实现各种枚举类型的具体判断, 比如PC
    public boolean isPC(int index) {
        return index >= PC.start && index <= PC.end;
    }

    public boolean isWISE(int index) {
        return index >= WISE.start && index <= WISE.end;
    }
}
