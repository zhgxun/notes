package github.banana.view;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 字节相关
 * <p>
 * 1. char 能存储中文吗
 * char 可以存储一个中文汉字, 因为 java 中使用的编码是 Unicode, 不选择任何特定的编码, 直接使用字符在字符集中的编码, 这是统一的唯一方法
 * 一个 char 类型占2个字节, 16比特, 所以放一个中文没问题
 * <p>
 * 使用 Unicode 意味着字符在 JVM 内部和外部有不同的表现形式, 在 JVM 内部都是 Unicode, 当这个字符被从 JVM 内存转移到外部时, 例如存入
 * 文件系统中, 都需要进行编码转换
 * <p>
 * 所以 Java 中有字节流和字符流, 以及在字符流和字节流之间进行转换的转换流, 如 {@link java.io.InputStreamReader} 和
 * {@link java.io.OutputStreamWriter}, 这两个类是字节流和字符流之间的适配器类, 承担了编码转换的任务
 */
public class CharTest {

    public static void main(String[] args) {

        // 从文件中读取内容
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("text.txt"), Charset.forName("UTF-8")));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
