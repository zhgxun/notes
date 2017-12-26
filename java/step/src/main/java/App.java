import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class App {
    public static void main(String[] args) {
        // 获取一个日志工厂类, 需要配置日志文件
        Log log = LogFactory.getLog(App.class);
        log.info("Hello, world!");
    }
}
