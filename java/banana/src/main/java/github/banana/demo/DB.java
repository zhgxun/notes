package github.banana.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用JDBC连接MySQL数据库
 * 
 * @author zhgxun
 *
 */
public class DB {

	static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
	static final String JDBC_USER = "root";
	static final String JDBC_PASSWORD = "";

	public static void main(String[] args) {
		try {
			// 获取所有用户信息
			List<User> users = new ArrayList<>();
			users = getAllUsers();
			for (User user : users) {
				System.out.println(user);
			}

			// 更新数据记录
			User u = users.get(0);
			u.name = "张十三";
			update(u);

			// 删除跟更新数据类似

			// 新增数据
			long id = insert(new User("关晓彤", 50));
			System.out.printf("新增数据主键：%d\n", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取用户信息, 注意try()和try...catch() {}的语法格式
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static List<User> getAllUsers() throws SQLException {
		try (Connection conn = getConnection()) {
			// 总是优先使用PreparedStatement
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM user")) {
				// 查询结果是ResultSet
				try (ResultSet rs = ps.executeQuery()) {
					List<User> list = new ArrayList<>();
					while (rs.next()) {
						long id = rs.getLong("id");
						String name = rs.getString("name");
						int age = rs.getInt("age");
						User std = new User(id, name, age);
						list.add(std);
					}
					return list;
				}
			}
		}
	}

	/**
	 * 更新记录
	 * 
	 * @param u
	 * @throws SQLException
	 */
	private static void update(User u) throws SQLException {
		try (Connection conn = getConnection()) {
			// 使用PreparedStatement的executeUpdate()进行更新
			try (PreparedStatement p = conn.prepareStatement("UPDATE user SET name=? WHERE id = ?")) {
				// 占位符从1开始
				p.setObject(1, u.name);
				p.setObject(2, u.id);
				// 更新操作包括UPDATE、INSERT和DELETE语句
				// 更新结果是int
				int n = p.executeUpdate();
				System.out.println("Update rows: " + n);
			}
		}
	}

	/**
	 * 新增数据
	 * 
	 * @param u
	 * @return
	 * @throws SQLException
	 */
	private static long insert(User u) throws SQLException {
		try (Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO user (name, age) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS)) {
				ps.setObject(1, u.name);
				ps.setObject(2, u.age);
				int n = ps.executeUpdate();
				System.out.println(n + " inserted.");
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						long id = rs.getLong(1);
						u.id = id;
						return id;
					}
				}
			}
		}
		return 0;
	}

	/**
	 * 连接数据库
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}

}
