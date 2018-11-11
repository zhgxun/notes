package github.banana.demo;

public class Week {

    public static void main(String[] args) {
        // 枚举WeekDay中所有的常量
        for (WeekDay day : WeekDay.values()) {
            // 打印每个常量的名字
            System.out.println(day.name());
        }

        // 获取一个常量的引用类型
        WeekDay fri = WeekDay.FRI;
        System.out.println("FRI.name() = " + fri.name());
        // 获取定义时的序号
        System.out.println("FRI.ordinal() = " + fri.ordinal());
        System.out.println(WeekDay.valueOf("FRI").name());

        // 获取中文描述
        System.out.println(fri.toChinese());
        System.out.println(WeekDay.SAT.name());
        System.out.println(WeekDay.SAT.toChinese());
    }

}
