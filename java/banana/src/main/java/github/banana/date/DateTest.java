package github.banana.date;

import java.util.Calendar;
import java.util.Date;

/**
 * 该包的大部分方法已经被标注为@Deprecated弃用, 不再推荐使用
 */
public class DateTest {

    public static void main(String[] args) {
        // 默认时底层调用当前时间戳 System.currentTimeMillis() 转化为 Tue Apr 03 09:07:15 CST 2018 格式时间
        Date date = new Date();
        System.out.println("默认时间: " + date);

        // 指定一个时间戳长整型, 注意长整型的书写
        date = new Date(1522634734000L);
        System.out.println("直接转化指定的时间戳为日期格式: " + date);

        // 比较两个Date日期的先后
        Date date1 = new Date();
        Date date2 = new Date(1522634734000L);
        if (date1.before(date2)) {
            System.out.printf("%s 在 %s 之前", date1, date2);
        }
        if (date1.after(date2)) {
            System.out.printf("%s 在 %s 之后", date1, date2);
        }

        // 打印当前时间毫秒数的三种方法
        System.out.println("打印当前时间毫秒数的三种方法");
        System.out.println("系统: " + System.currentTimeMillis());
        System.out.println("日期: " + new Date().getTime());
        System.out.println("日历: " + Calendar.getInstance().getTimeInMillis());
    }
}
