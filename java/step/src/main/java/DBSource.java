import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库连接池
 */
public class DBSource {
    // 配置MySQL连接属性
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    /**
     * 使用连接池查询数据
     *
     * @param args 输入参数
     */
    public static void main(String[] args) {
        // 创建连接池
        DataSource dataSource = create();
        // 保持线程实例
        List<Thread> threads = new ArrayList<>();
        // 启动4个线程来查询用户列表
        for (int i = 1; i <= 4; i++) {
            // 准备线程池
            Thread thread = new Thread() {
                public void run() {
                    // 暂缓一段时间
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        List<User> users = getUsers(dataSource);
                        for (User user : users) {
                            System.out.println(user);
                        }
                    } catch (SQLException e) {
                        e.getStackTrace();
                    }
                }
            };
            threads.add(thread);
        }
        // 启动线程
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
        }
        // 关闭连接池
        ((HikariDataSource) dataSource).close();
    }

    /**
     * 配置数据库连接池
     *
     * @return DataSource
     */
    private static DataSource create() {
        // HikariCP是JDBC连接池组件
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(JDBC_USER);
        config.setPassword(JDBC_PASSWORD);
        // 连接超时
        config.addDataSourceProperty("connectionTimeout", 1000);
        // 空闲超时
        config.addDataSourceProperty("idleTimeout", 1000);
        // 最大连接数
        config.addDataSourceProperty("maximumPoolSize", 10);

        return new HikariDataSource(config);
    }

    /**
     * 通过连接池获取用户列表
     *
     * @param dataSource 数据库连接池
     * @return List<User>
     * @throws SQLException 异常处理
     */
    private static List<User> getUsers(DataSource dataSource) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("Using connection: " + conn);
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM user")) {
                try (ResultSet rs = ps.executeQuery()) {
                    return handleUsers(rs);
                }
            }
        }
    }

    /**
     * 获取用户列表
     *
     * @param rs ResultSet 结果集
     * @return List<User>
     * @throws SQLException 异常处理
     */
    private static List<User> handleUsers(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            users.add(new User(id, name, age));
        }
        return users;
    }
}
