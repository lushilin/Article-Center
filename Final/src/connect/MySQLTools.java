package connect;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTools {

	/**
	 * ��¼���ݿ�����ӵ�ַ �û��� ���� �����������淶����������ĿӦ������Щ��Ϣ���������ļ���
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
			// ע������
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
	 * �������ݿ����� �����������������⣬��������Ŀһ��������ӳصķ�ʽ
	 */
	public static Connection getMySQLConnection() throws SQLException {
		Connection con = java.sql.DriverManager.getConnection(dbUrl, dbUser,
				dbPwd);
		//con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		con.setAutoCommit(false);
		
		return con;
	}

	/**
	 * �ͷ����ݿ����� ��������Ŀһ��������ӳصķ�ʽ
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
	 * �ع�����
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
	 * �ر��α�
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
