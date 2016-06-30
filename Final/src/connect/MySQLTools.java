package connect;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTools {

	/**
	 * 记录数据库的链接地址 用户名 口令 这样做并不规范，真正的项目应当将这些信息放在配置文件中
	 */
	private static String dbUrl = "jdbc:mysql://localhost:3306/paper";
	//private static String dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	
	private static String dbUser = "root";
	//private static String dbUser = "test";
	
	private static String dbPwd = "";
	
	//private static String dbPwd = "test";
	

	static {
		try {
			System.out.println("dbUrl="+dbUrl);
			System.out.println("dbUser="+dbUser);
			System.out.println("dbPwd="+dbPwd);
			// 注册驱动
			java.sql.DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//java.sql.DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
	}

	// 
	/**
	 * 建立数据库连接 这样做会有性能问题，真正的项目一般采用连接池的方式
	 */
	public static Connection getMySQLConnection() throws SQLException {
		Connection con = java.sql.DriverManager.getConnection(dbUrl, dbUser,
				dbPwd);
		//con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		con.setAutoCommit(false);
		
		return con;
	}

	/**
	 * 释放数据库连接 真正的项目一般采用连接池的方式
	 * 
	 * @param con
	 */
	public static void closeConnection(Connection con) {
		if (con == null) {
			return;
		} else {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 回滚事务
	 * 
	 * @param con
	 */
	public static void rollback(Connection con) {
		if (con == null) {
			return;
		} else {
			try {
				con.rollback();
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭游标
	 * 
	 * @param stmt
	 */
	public static void closeStatement(Statement stmt) {
		if (stmt == null) {
			return;
		} else {

			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResultSet(ResultSet reset) {
		if (reset == null) {
			return;
		} else {
			try {
				reset.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
