package github.banana.demo;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 打印当月日历
 *
 * @author zhgxun
 */
public class Calendar {

    public static void main(String[] args) {
        // 本地日期对象
        LocalDate date = LocalDate.now();
        // 指定一个日期对象
        // LocalDate date = LocalDate.of(2017, 10, 5);
        // 当前月份值
        int month = date.getMonthValue();
        // 当月所在的天
        int today = date.getDayOfMonth();

        // 重置月的天到起始位置, 减去指定的天数, 即是回复到月初第一天
        date = date.minusDays(today - 1);

        // 当前时间对应的周
        DayOfWeek weekday = date.getDayOfWeek();
        // 对应周的枚举
        int value = weekday.getValue();

        // 打印日历标题
        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        // 在第一天的空白处打印空白
        for (int i = 1; i < value; i++) {
            System.out.printf("%4s", " ");
        }

        // 遍历当月
        while (date.getMonthValue() == month) {
            System.out.printf("%3d", date.getDayOfMonth());
            // 当天着重标注
            if (date.getDayOfMonth() == today) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
            // 每一次循环递增一天
            date = date.plusDays(1);
            // 如果是每周的第一天则换行
            if (date.getDayOfWeek().getValue() == 1) {
                System.out.println();
            }
        }

        // 如果不是每周的第一天无需处理
        if (date.getDayOfWeek().getValue() != 1) {
            System.out.println();
        }
    }

}
