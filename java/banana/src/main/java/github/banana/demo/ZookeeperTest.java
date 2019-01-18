package github.banana.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Zookeeper 简单调用
 */
@Slf4j
public class ZookeeperTest {

    // 信号量, 用户阻塞线程执行
    private static final CountDownLatch latch = new CountDownLatch(1);

    // zk 实例
    private static ZooKeeper zk = null;

    /**
     * 创建连接, 相当耗时
     *
     * @throws IOException IOException
     */
    private void connection() throws IOException {
        // Zookeeper 监听采用子线程方式, 需要确保主线程得到同步信息
        if (zk == null) {
            zk = new ZooKeeper("127.0.0.1:2181", 2000, (event) -> {
                // 当前zk状态
                Watcher.Event.KeeperState state = event.getState();
                // zk状态建立连接
                if (state == Watcher.Event.KeeperState.SyncConnected) {
                    // 当前连接路径
                    String path = event.getPath();
                    // 当前事件通知类型
                    Watcher.Event.EventType type = event.getType();
                    // 启动连接
                    if (type == Watcher.Event.EventType.None) {
                        log.info("Zookeeper 已经启动...");
                        latch.countDown();
                    } else if (type == Watcher.Event.EventType.NodeCreated) {
                        log.info("新建节点: " + path);
                    } else if (type == Watcher.Event.EventType.NodeDataChanged) {
                        log.info("节点发生变更: " + path);
                    } else if (type == Watcher.Event.EventType.NodeDeleted) {
                        log.info("节点被删除: " + path);
                    }
                }
            });
        }
    }

    private String createNode(String path, String content) throws KeeperException, InterruptedException {
        return zk.create(path, content.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    private Stat updateNode(String path, String content) throws KeeperException, InterruptedException {
        return zk.setData(path, content.getBytes(), -1);
    }

    private void deleteNode(String path) throws KeeperException, InterruptedException {
        zk.delete(path, -1);
    }

    private Stat exist(String path, boolean isWatcher) throws KeeperException, InterruptedException {
        return zk.exists(path, isWatcher);
    }

    private void close() throws InterruptedException {
        if (zk != null) {
            zk.close();
        }
    }

    public static void main(String[] args) {
        ZookeeperTest test = new ZookeeperTest();
        try {
            // 1. 创建 zk 连接
            log.info("1. 创建 zk 连接");
            test.connection();

            // 2. 确保 zk 创建完成
            log.info("2. 确保 zk 创建完成");
            latch.await();

            String path = "/test_node";
            String content = "this is a test node";
            boolean watcher = true;

            // 3. 删除节点如果存在
            // 如果节点存在则返回节点信息, 节点不存在返回null
            log.info("删除节点如果存在: {}", path);
            if (test.exist(path, watcher) != null) {
                test.deleteNode(path);
                log.info("节点 {} 删除完毕", path);
            }

            // 4. 新增节点
            log.info("4. 新增节点, path= {}, content= {}", path, content);
            Stat stat = test.exist(path, watcher);
            if (stat == null) {
                String nodeDesc = test.createNode(path, content);
                log.info("新增节点: {}", nodeDesc);
            }

            // 5. 修改节点
            String content1 = "update node";
            log.info("5. 修改节点, path= {}, update content= {}", path, content1);
            if (test.exist(path, watcher) != null) {
                Stat stat1 = test.updateNode(path, content1);
                log.info("更新后的节点信息: {}", stat1);
            }

            // 6. 关闭zk
            test.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
