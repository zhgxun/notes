package github.banana.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Arrays;

/**
 * 监视zk树中的节点数据变化
 * <p>
 * 关于回调, 有以下几种状态
 * {@link StatCallback} 节点发生变化时的回调
 * {@link org.apache.zookeeper.AsyncCallback.DataCallback} 节点内容发生变化时的回调
 * {@link org.apache.zookeeper.AsyncCallback.ACLCallback} acl回调
 * {@link org.apache.zookeeper.AsyncCallback.ChildrenCallback} 子节点回调
 * {@link org.apache.zookeeper.AsyncCallback.Children2Callback} 子节点发生变化时, 状态也一同回调
 * {@link org.apache.zookeeper.AsyncCallback.StringCallback} 节点的名称发生变化时回调
 * {@link org.apache.zookeeper.AsyncCallback.VoidCallback} 此回调不会从节点中检索任何内容, 它对一些不希望任何内容返回的API很有用
 * {@link org.apache.zookeeper.AsyncCallback.MultiCallback} 此回调用于处理来自单个多个调用的多个结果
 * <p>
 * 关于Watcher监视器, 有以下几种
 * {@link org.apache.zookeeper.Watcher.Event} 此接口定义事件可能表示的状态
 * {@link org.apache.zookeeper.Watcher.Event.KeeperState} 连接状态枚举, 目前还有以下几种状态, 有一些已经被标记为废弃，后续不可在使用了
 * {@link org.apache.zookeeper.Watcher.Event.KeeperState#Unknown} 未知, 已标记为废弃
 * {@link org.apache.zookeeper.Watcher.Event.KeeperState#Disconnected} 客户端断开, 不连接到任何的服务器
 * {@link org.apache.zookeeper.Watcher.Event.KeeperState#SyncConnected} 已经连接成功
 * {@link org.apache.zookeeper.Watcher.Event.KeeperState#AuthFailed} 认证失败
 * {@link org.apache.zookeeper.Watcher.Event.KeeperState#Expired} 过期等等, 还有几个, 不再列举, 常用的是连接断开, 连接建立, 过期或者认证失败
 * <p>
 * 与连接状态相对应的是, 连接状态事件类型, 这个很重要
 * {@link org.apache.zookeeper.Watcher.Event.EventType#None} None比较特殊, 可能包括几种情形
 * 比如SyncConnected, 客户端与服务器成功建立会话, 会话是连接状态; Disconnected 客户端与服务端断开; Expired和AuthFailed认证失败
 * {@link org.apache.zookeeper.Watcher.Event.EventType#NodeCreated} 节点创建事件
 * {@link org.apache.zookeeper.Watcher.Event.EventType#NodeDeleted} 节点删除事件
 * {@link org.apache.zookeeper.Watcher.Event.EventType#NodeDataChanged} 节点的数据被改变事件
 * {@link org.apache.zookeeper.Watcher.Event.EventType#NodeChildrenChanged} 子节点改变事件
 * <p>
 * 注意:
 * 一旦watcher被触发, ZK都会从相应的存储中移除, 因此在使用Watcher时需要谨记使用前一定要注册, 编码中会多次注册, 即每次有逻辑需要都需要调用
 * 客户端Watcher回调的过程是一个串行同步的过程, 这是为了保证顺序, 同时需要谨记千万不要因为一个Watcher的处理逻辑影响了整个客户端的Watcher回调
 * WatchedEvent是ZK整个Watcher通知机制的最小通知单元, 从上文已经介绍了这个数据结构中只包含三部分: 通知状态, 事件类型, 节点路径. 也就是说, Watcher通知仅仅告诉客户端发生了什么事情, 而不会说明事件的具体内容
 * 因此事件的具体内容需要借助回调来完成
 */
@Slf4j
public class DataMonitor implements Watcher, StatCallback {

    private ZooKeeper zk;
    private String znode;
    private Watcher chainedWatcher;
    boolean dead;
    private DataMonitorListener listener;
    // 临时保存节点数据
    private byte[] prevData;

    DataMonitor(ZooKeeper zk, String znode, Watcher chainedWatcher, DataMonitorListener listener) {
        log.info("DataMonitor Start... zk: {}, znode: {}", zk.getState().toString(), znode);
        this.zk = zk;
        this.znode = znode;
        this.chainedWatcher = chainedWatcher;
        this.listener = listener;
        // 检查节点是否存在
        zk.exists(znode, true, this, null);
    }

    /**
     * 监听器接口
     */
    public interface DataMonitorListener {
        /**
         * The existence status of the node has changed.
         */
        void exists(byte[] data);

        /**
         * The ZooKeeper session is no longer valid.
         *
         * @param rc the ZooKeeper reason code
         */
        void closing(int rc);
    }

    /**
     * 节点变更回到
     *
     * @param event 节点事件
     */
    @Override
    public void process(WatchedEvent event) {
        log.info("DataMonitor process path: {}", event.getPath());
        // 回调事件的路径
        String path = event.getPath();
        // None类型会在Session expired / connection loss / auth failed 时得到对应的触发, 对应的触发path为null
        if (event.getType() == Event.EventType.None) {
            switch (event.getState()) {
                case SyncConnected:
                    break;
                case Expired:
                    // 过期说明服务不存在或者连接已经丢失, 关闭即可
                    dead = true;
                    listener.closing(Code.SESSIONEXPIRED.intValue());
                    break;
            }
        } else {
            // 路径是我们当前关心的路径时
            if (path != null && path.equals(znode)) {
                zk.exists(znode, true, this, null);
            }
        }
        // 该方法未实现, 不需关心
        if (chainedWatcher != null) {
            chainedWatcher.process(event);
        }
    }

    /**
     * 节点内容发生变化时会回调该方法, 该方法需要对数据内容的变化做出响应
     *
     * @param rc
     * @param path
     * @param ctx
     * @param stat
     */
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        log.info("DataMonitor processResult path: {}", path);
        boolean exists;
        switch (rc) {
            // 节点存在
            case Code.Ok:
                exists = true;
                break;
            // 节点不存在
            case Code.NoNode:
                exists = false;
                break;
            // 节点过期或者认证失败, 通知关闭服务
            case Code.SessionExpired:
            case Code.NoAuth:
                dead = true;
                listener.closing(rc);
                return;
            default:
                // 重试检查
                zk.exists(znode, true, this, null);
                return;
        }

        byte[] b = null;
        if (exists) {
            try {
                // 获取节点下面的数据内容
                b = zk.getData(znode, false, null);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                return;
            }
        }
        // prevData初始可能为null, 上一次是有值的, 或者是值存在差异, 则需要保存最新的值
        if ((b == null && b != prevData) || (b != null && !Arrays.equals(prevData, b))) {
            // 该方法相当重要, 节点内容发生变化时意味着上游服务有更新, 不管是删除还是新增等等
            // 方法会被执行, 相当于注册中心的生产者发生了变化, 消费者需要更新本地数据
            listener.exists(b);
            // 如果当前为null上一次不为null的话, 之后就被初始化为null了
            prevData = b;
        }
    }
}
