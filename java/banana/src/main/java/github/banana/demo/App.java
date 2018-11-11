package github.banana.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // 实例化一个日志工厂
        Log log = LogFactory.getLog(App.class);
        log.info("Hello, world!");
        log.info(2 - 1.1);

        String url = "http://322385841218.form";
        log.info(Long.valueOf(url.replace("http://", "").replace(".form", "")));

        System.out.println(TEnum.MERGED.name() + "_" + 1);
        System.out.println(TEnum.BOOKING.name() + "_" + 2);
        System.out.println(TEnum.AGODA.name() + "_" + 3);
        System.out.println(TEnum.MERGED.getUniqueLoc(123456));
    }
}
enum TEnum {
    MERGED, BOOKING, AGODA, HOTELS, CTRIP;

    public String getUniqueLoc(Integer uniqueCode) {
        switch (this) {
            case MERGED:
                return String.format("http://carp.baidu.com/%s/",  uniqueCode);
            case BOOKING:
                return String.format("http://www.booking.com/%s/", uniqueCode);
            case AGODA:
                return String.format("http://www.agoda.com/%s/",  uniqueCode);
            case HOTELS:
                return String.format("http://www.hotels.com/%s/", uniqueCode);
            case CTRIP:
                return String.format("http://www.ctrip.com/%s/", uniqueCode);

            default:
                return null;
        }
    }
}
