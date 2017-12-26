package github.banana;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.zip.GZIPInputStream;

/**
 * 文件IO
 * 
 * @author zhgxun
 *
 */
public class IO {

    public static void main(String[] args) throws IOException {
        // IO: Input / Output
        // 从外部读入数据
        // 输出内容在某处
        // IO流是一种顺序读写数据的模式
        // 单向流动，以byte字节为最小单位

        // IO流是一种流式的数据输入/输出模型：
        // 二进制数据以byte为最小单位在InputStream / OutputStream中单向流动
        // 字符数据以char为最小单位在Reader / Writer中单向流动
        // JDK的java.io包提供了同步IO功能 - 编写简单, CPU执行效率低
        // JDK的java.nio包提供了异步IO功能 - 编写复杂, CPU执行效率高

        // Java的IO流的接口和实现分离：
        // 字节流接口：InputStream / OutputStream
        // 字符流接口：Reader / Writer

        // 文件对象
        // 表示文件或目录
        // 构造方法File(String pathname)，可传入绝对路径或相对路劲
        String pathName = "/Users/zhgxun/go/src/notes/java/banana";
        File file = new File(pathName);
        System.out.println(pathName + ": is directory: " + file.isDirectory());
        System.out.println(pathName + ": is file: " + file.isFile());
        if (file.isFile()) {
            System.out.println(file.getName());
        }
        if (file.isDirectory()) {
            System.out.println(pathName + " 该路径是一个目录");
            System.out.println("String[] list()：列出目录下的文件和子目录名");
            // String[] list()：列出目录下的文件和子目录名
            for (String path : file.list()) {
                System.out.println(path);
            }
            // File[] listFiles()：列出目录下的文件和子目录名
            System.out.println("File[] listFiles()：列出目录下的文件和子目录名");
            for (File path : file.listFiles()) {
                System.out.println(path);
            }
            // File[] listFiles(FilenameFilter filter) 过滤掉部分目录或文件
            System.out.println("只获取以xml结尾的文件");
            // 添加一个回调函数处理
            File[] files = file.listFiles(new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".xml");
                }
            });
            for (File file1 : files) {
                System.out.println(file1);
            }

            // 创建File对象本身不涉及IO操作
            // 获取路径 即是传入构造函数本身的路径
            System.out.println("获取路径 即是传入构造函数本身的路径: " + file.getPath());
            // 绝对路径
            System.out.println("绝对路径: " + file.getAbsolutePath());
            // 规范路径
            System.out.println("规范路径: " + file.getCanonicalPath());
        }

        // 按字节读入文件流
        System.out.println("按字节读入文件流");
        // try () {} 写法能正确的关闭资源, 也是推荐的写法
        try (InputStream input = new FileInputStream(
                "/Users/zhgxun/go/src/notes/java/banana/src/main/java/github/banana/IO.java")) {
            int n = 0;
            // 设置一个缓存, 一次读入多个字节
            byte[] buffer = new byte[1024];
            // 读入结束返回-1, 并且该方法是阻塞读取的
            while ((n = input.read(buffer)) != -1) {
                System.out.println(n);
            }
        }

        // OutputStream是所有输出流的超类
        System.out.println("OutputStream是所有输出流的超类");
        try (OutputStream output = new FileOutputStream("output.txt")) {
            // write(int b)写入一个字节
            // write(byte[])写入byte[]数组的所有字节
            // flush()方法将缓冲器内容输出
            // write()方法是阻塞（blocking）的
            // 通常在调用close()方法前都会刷新缓冲区
            // 字节数据都要处理编码
            byte[] b1 = "你好".getBytes("UTF-8");
            output.write(b1);
            byte[] b2 = "世界，这里是中国，绵延长远的万里长城".getBytes("UTF-8");
            output.write(b2);
            System.out.println("写入完毕");
        }

        // Filter模式是为了解决子类数量爆炸的问题
        // Java IO 使用Filter模式为InputStream / OutputStream 增加功能
        // 可以把一个InputStream和任意的FilterInputStream组合
        // 可以把一个OutputStream和任意的FilterOutputStream组合
        // Filter模式可以在运行期动态增加功能, 又称Decorator模式, 通过少量的类实现了各种功能的组合
        // InputStream
        // FileImputStream FilterInputStream
        // ByteArrayInputStream BufferedInputStream
        // ServletInputStream DataInputStream, CheckedInputStream
        //
        // DigestInputStream 签名
        // CipherInputStream 加解密
        // GZIPInputStream gzip压缩处理
        System.out.println("Filter模式是为了解决子类数量爆炸的问题");
        System.out.println("把一个.gz压缩的文件以字符串读取出来");
        // 尝试读取一个Yii框架的压缩更新日志记录文件
        // 如果想统计读取到的字符个数, 可以新增一个实现类
        // 需要注意的是如果此时要打印新增的实现类, 则要用新增类的类型, 不能再用原始的InputStream类型
        try (CountInputStream input = new CountInputStream(
                new GZIPInputStream(new BufferedInputStream(new FileInputStream("yii_framework_change_log.tar.gz"))))) {
            // 读入到一个输出缓冲中
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            int n = 0;
            // 一次读取4096个字节
            byte[] bytes = new byte[4096];
            while ((n = input.read(bytes)) != -1) {
                // 将每次读取到的字节写入到缓冲中
                output.write(bytes, 0, n);
            }
            // 将字节转换为字节数组
            byte[] data = output.toByteArray();
            // 将字节数组转换为字符串
            String text = new String(data, "UTF-8");
            System.out.println(text);
            System.out.println("该次读取到的字节个数为：" + input.count);
        }

        // 序列化
        // 序列化是指把一个Java对象变成二进制内容（byte[]）
        // Java对象实现序列化必须实现Serializable接口, 是一个空接口, 可不实现任何方法
        // 反序列化是指把一个二进制内容（byte[]）变成Java对象
        // 使用ObjectOutputStream和ObjectInputStream实现序列化和反序列化
        // readObject()可能抛出的异常
        // ClassNotFoundException：没有找到对应的Class
        // InvalidClassException：Class不匹配
        // 反序列化由JVM直接构造出Java对象，不调用构造方法
        // 可设置serialVersionUID作为版本号（非必需）, 设置后如果修改版本号则会抛出InvalidClassException Class异常
        // Java的序列化机制仅适用于Java, 如果需要与其它语言交换数据, 必须使用通用的序列化方法, 比如JSON
        System.out.println("序列化");
        // 序列化内容到该文件
        String dataFile = "output.txt";
        try (ObjectOutputStream output = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(dataFile)))) {
            // 序列化一个整数
            output.writeInt(1024);
            // 序列化一个字符串
            output.writeUTF("小王子和喜欢他的人们");
            // 序列化一个对象
            output.writeObject(new User(20, "周迅", 18));
            System.out.println("序列化完毕");
        }
        // 反序列化
        System.out.println("反序列化");
        try (ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
            // 根据约定依次读入已经序列化的内容
            System.out.println(input.readInt());
            System.out.println(input.readUTF());
            User user = (User) input.readObject();
            System.out.println(user);
            System.out.println("反序列化结束");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Reader以字符为最小单位实现了字符流输入
        // int read() 读取下一个字符
        // int read(char[]) 读取若干字符并填充到char[]数组
        // FileReader：从文件读取
        // CharArrayReader：从char[]数组读取
        // Reader是基于InputStream构造的，任何InputStream都可指定编码并通过InputStreamReader转换为Reader
        // Reader reader = new InputStreamReader(input, "UTF-8")
        // 注意这里的input是InputStream类型
        System.out.println("Reader以字符为最小单位实现了字符流输入");
        System.out.println("按单个字符读取");
        try (Reader reader = new FileReader("text.txt")) {
            int n = 0;
            while ((n = reader.read()) != -1) {
                System.out.println((char) n);
            }
        }
        System.out.println("一次性尽可能读取更多字符并且转换编码");
        try (Reader reader = new InputStreamReader(new FileInputStream("text.txt"), "UTF-8")) {
            char[] chars = new char[4096];
            while (reader.read(chars) != -1) {
                System.out.println(chars);
            }
        }

        // Writer以字符为最小单位实现了字符流输出
        // write(int c) 写入下一个字符
        // write(char[]) 写入char[]数组的所有字符
        // Writer是基于OutputStream构造的，任何OutputStream都可指定编码并通过OutputStreamWriter转换为Writer
        // Writer writer = new OutputStreamWriter(output, "UTF-8")
        // output 为outputStream类型
    }

}
