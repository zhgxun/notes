package github.banana.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 事务处理
 * 
 * @author zhgxun
 *
 */
public class DBTx {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    public static void main(String[] args) {
        // 打印用户列表
        List<User> users = null;
        try {
            users = getUsers();
            for (User user : users) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 开启更新事务
        System.out.println("开启更新事务");
        Connection conn = null;
        try {
            // 连接数据库
            conn = getConnection();
            // 设置事务隔离级别为提交读
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            // 开启事务, 开启事务即是关闭自动提交即可
            conn.setAutoCommit(false);
            // 更新用户信息
            update(conn, new User(users.get(0).id, "杨幂", 25));
            update(conn, new User(users.get(1).id, "李思思", 20));
            // 提交事务
            conn.commit();
            // 事务执行成功
            System.out.println("事务执行成功");
        } catch (SQLException e) {
            // 回滚事务
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("回滚事务失败");
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // 尝试关闭数据库连接
            try {
                // 恢复数据库自动提交记录
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                System.out.println("数据库关闭失败");
                e.printStackTrace();
            }
        }

        // 事务后重新打印所有用户列表
        System.out.println("事务后重新打印所有用户列表");
        try {
            users = getUsers();
            for (User user : users) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 模拟事务回滚
        System.out.println("模拟事务回滚");
        try {
            // 连接数据库
            conn = getConnection();
            // 设置事务隔离级别为提交读
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            // 开启事务, 开启事务即是关闭自动提交即可
            conn.setAutoCommit(false);
            // 更新用户信息
            update(conn, new User(users.get(0).id, "周迅", 28));
            update(conn, new User(users.get(1).id, "卓依婷", 24));
            // 提交事务
            conn.commit();
            // 人为制造异常终止事务使其回滚, 不过后续代码也无法正常执行了
            throw new RuntimeException("强制数据库回滚");
        } catch (SQLException e) {
            // 回滚事务
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("回滚事务失败");
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // 尝试关闭数据库连接
            try {
                // 恢复数据库自动提交记录
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                System.out.println("数据库关闭失败");
                e.printStackTrace();
            }
        }
    }

    /**
     * 数据库连接
     * 
     * @return
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    /**
     * 获取用户列表
     * 
     * @return
     * @throws SQLException
     */
    private static List<User> getUsers() throws SQLException {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM user")) {
                try (ResultSet rs = ps.executeQuery()) {
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
        }
    }

    /**
     * 根据id更新用户姓名
     * 
     * @param conn
     * @param user
     * @throws SQLException
     */
    private static void update(Connection conn, User user) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE user SET name = ? WHERE id = ?")) {
            ps.setObject(1, user.name);
            ps.setObject(2, user.id);
            ps.executeUpdate();
        }
    }

}
