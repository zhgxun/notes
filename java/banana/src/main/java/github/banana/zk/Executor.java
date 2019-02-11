package github.banana.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * 一个zk监视器的简单实例
 * <p>
 * 参考官网: https://zookeeper.apache.org/doc/r3.4.13/javaExample.html
 * 管理zk连接
 * 使用IDE开发工具直接运行类似如下命令即可, 部署的话需要指定主类或者具有启动功能的程序去执行
 * 127.0.0.1:2181 /test /Users/baidu/Desktop/zk.txt ["/bin/sh", "-c", "java -version"] 单引号需要小心，Java解析需要支持到该级别
 * 也就是说 {@link Runtime#getRuntime()#exec} 执行的命令参数格式需要满足 String[] exec = {"/bin/sh", "-c", "java -version"} 否则无法执行
 * <p>
 * 节点的内容有变化, 就会重新执行用户命令
 */
@Slf4j
public class Executor implements Watcher, Runnable, DataMonitor.DataMonitorListener {
    private DataMonitor dm;
    private String filename;
    private String[] exec;
    private Process child;

    /**
     * 实例化主机等信息
     *
     * @param hostPort 主机端口
     * @param znode    节点
     * @param filename 文件名
     * @param exec     执行的命令
     * @throws IOException 异常
     */
    private Executor(String hostPort, String znode, String filename, String[] exec) throws IOException {
        log.info("Start New Instance host: {}, znode: {}, fileName: {}, exec: {}", hostPort, znode, filename, exec);
        this.filename = filename;
        this.exec = exec;
        // 实例化zk, 这是异步的连接, 同时注册Watcher机制
        /** @see Executor#process(WatchedEvent) 回掉该方法 */
        ZooKeeper zk = new ZooKeeper(hostPort, 3000, this);
        // 进行节点内容监听处理, 这中间可能会出现一点问题, 如果注册足够慢的话
        dm = new DataMonitor(zk, znode, null, this);
    }

    public static void main(String[] args) {
        log.info("Service Start...");
        if (args.length < 4) {
            System.err.println("USAGE: Executor hostPort znode filename program [args ...]");
            System.exit(2);
        }

        String hostPort = args[0];
        String znode = args[1];
        String filename = args[2];
        // 解析后会去除两边空格, 如果有特殊字符需要自行处理
        // args[3] = [/bin/sh,-c,java -version], 需要将其解析为字符串数组格式
        String[] exec = args[3].substring(1, args[3].length() - 1).split(",");

        try {
            // 启动执行zk链接保持和管理
            new Executor(hostPort, znode, filename, exec).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see Watcher#process(WatchedEvent)
     */
    @Override
    public void process(WatchedEvent event) {
        log.info("Executor Watcher process...");
        dm.process(event);
    }

    public void run() {
        log.info("Service Running...");
        try {
            // 服务未停止则一直运行
            synchronized (this) {
                while (!dm.dead) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 关闭时通知所有线程
     *
     * @param rc the ZooKeeper reason code
     */
    public void closing(int rc) {
        synchronized (this) {
            notifyAll();
        }
    }

    /**
     * 数据的读取和写入输出
     */
    static class StreamWriter extends Thread {
        OutputStream os;
        InputStream is;

        StreamWriter(InputStream is, OutputStream os) {
            this.is = is;
            this.os = os;
            start();
        }

        @Override
        public void run() {
            byte[] b = new byte[80];
            int rc;
            try {
                while ((rc = is.read(b)) > 0) {
                    os.write(b, 0, rc);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理节点的数据
     *
     * @param data 节点内容
     */
    public void exists(byte[] data) {
        // 当前节点数据为空, 可能节点不存在了
        if (data == null) {
            // 用户输入执行的命令结果, 如果挂起则需要等待停止后再置为空
            if (child != null) {
                // 直接等待停止正在运行的命令
                System.out.println("Killing process");
                child.destroy();
                try {
                    child.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            child = null;
        } else {
            // 如果不等于null, 说明上一个命令正在执行, 需要等待执行完毕后再继续执行当前待执行的命令
            if (child != null) {
                System.out.println("Stopping child");
                child.destroy();
                try {
                    child.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 将数据写入文件中保存
            try {
                FileOutputStream fos = new FileOutputStream(filename);
                fos.write(data);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                // 开始执行用户输入的命令
                System.out.println("Starting child, exec: " + Arrays.toString(exec));
                child = Runtime.getRuntime().exec(exec);
                // 将输出信息输出的标准输出和标准错误输出
                new StreamWriter(child.getInputStream(), System.out);
                new StreamWriter(child.getErrorStream(), System.err);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
