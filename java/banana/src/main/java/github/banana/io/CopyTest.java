package github.banana.io;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 调用更能充分利用操作系统底层机制的操作, 可能更快些
 */
public class CopyTest {

    public static void main(String[] args) {
        try {
            FileChannel sourceChannel = new RandomAccessFile("/Users/baidu/Public/test/java/notes/java/banana/data.json", "r").getChannel();
            FileChannel destChannel = new RandomAccessFile("/Users/baidu/Public/test/java/notes/java/banana/data_new.json", "rw").getChannel();
            for (long count = sourceChannel.size(); count > 0; ) {
                long trans = sourceChannel.transferTo(sourceChannel.position(), count, destChannel);
                sourceChannel.position(sourceChannel.position() + trans);
                count -= trans;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
