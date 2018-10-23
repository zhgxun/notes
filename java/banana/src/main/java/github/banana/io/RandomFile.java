package github.banana.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class RandomFile {

    public static void main(String[] args) {
        try {
            // 打开一个文件读写
            RandomAccessFile file = new RandomAccessFile("text.txt", "rw");
            // 获取文件通道
            FileChannel channel = file.getChannel();

            int length = 32;

            // 分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(length);

            // 将通道数据读入缓冲区
            int num = channel.read(buffer);

            byte[] dest = new byte[1024];

            int count = 0;

            // 记录读取到的真正长度
            int total = 0;

            // 读完为止
            while (num != -1) {
                count++;
                total += num;

                // 每一次读取到的字节数
                System.out.println("Read bytes length: " + num);
                buffer.flip();

                while (buffer.hasRemaining()) {
                    // 将当前读取到的字符取出保存到目标处
                    byte[] src = new byte[buffer.remaining()];
                    buffer.get(src);
                    int destPos = (count - 1) * length;
                    if (destPos <= 0) {
                        destPos = 0;
                    }
                    System.arraycopy(src, 0, dest, destPos, src.length);
                }

                buffer.clear();
                num = channel.read(buffer);
            }

            // 读取完毕, 打印实际读取到的字符数, 将其按字符串打印
            if (total > dest.length) {
                System.out.println("error");
            } else {
                System.out.println(new String(dest, 0, total));
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
