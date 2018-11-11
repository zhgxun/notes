package github.banana.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalTest {
    //public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final ThreadLocal<SimpleDateFormat> format = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String args[]) {
        for (int i = 0; i < 3; i++) {
            (new Thread(() -> {
                //System.out.println(format.format(new Date()));
                System.out.println(format.get().format(new Date()));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            })).start();
        }
    }
}
