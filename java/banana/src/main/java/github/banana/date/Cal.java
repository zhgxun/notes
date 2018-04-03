package github.banana.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @link https://docs.oracle.com/javase/8/docs/api/index.html
 * <p>
 * 时间存储
 * <p>
 * 将时间存入DB会涉及到跨时区的问题(同一个UTC时间在各个时区显示不同的数值).因此,在设计数据库表结构时需要小心谨慎,不能简单使用数据库提供的TIMESTAMP或DATETIME(详见:廖雪峰博客-如何正确地处理时间),比较推荐的是选用一个整数类型(如BIGINT64位)来存储从1970-01-01 00:00:00到当前时间所经过毫秒数的一个绝对数值.
 * <p>
 * 优点:
 * 读取的时间(Long整数)只需要按照用户所在的时区格式化为字符串就能正确地显示出来.
 * 缺点
 * 当开发人员、DBA直接查看DB时,只能看到一串数字.
 * <p>
 * 时间的格式化和解析格式在一个项目中往往只用一种,因此没有必要每次使用时都new出一个Formatter来. 但SimpleDateFormat的format()与parse()并非线程安全(详见: 关于SimpleDateFormat的非线程安全问题及其解决方案), 因此在并发环境中要注意同步或使用ThreadLocal控制
 * @link https://my.oschina.net/leejun2005/blog/152253
 */
public class Cal {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        // 默认日历类型 gregory
        System.out.println(calendar.getCalendarType());

        // Date日期类型
        Date date = calendar.getTime();
        System.out.println(date);

        // 往后推3个月
        calendar.add(Calendar.MONTH, 3);
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        // 往前推3个月
        calendar.add(Calendar.MONTH, -3);
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        // 创建SimpleDateFormat对象时需要传入一个pattern字符串, 这个pattern不是正则表达式, 而是一个日期模板字符串
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // Date -> String
        System.out.println(simpleDateFormat.format(new Date()));

        // String -> Date
        try {
            System.out.println("将字符串模板日期转化为Date对象: " + simpleDateFormat.parse("2018-04-02 10:05:30"));
            System.out.println("从Date对象中获取长整型的时间戳格式: " + simpleDateFormat.parse("2018-04-02 10:05:30").getTime());
        } catch (ParseException e) {
            e.getErrorOffset();
        }
    }
}
