package home;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import instance.*;

import connect.MySQLTools;

public class user_login extends HttpServlet {

	private Connection conn;
	String username=null;
	String password;
	String string_code;
	ResultSet rs;
	//ThreadLocal<Integer> tl = new ThreadLocal<Integer>();
	int a = 1;
	/**
	 * Constructor of the object.
	 */
	public user_login() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int b=1;
		System.out.println(username);
		System.out.println("*******************");
		System.out.println("b="+(++b));
		System.out.println(++a);
		System.out.println("++++++++++++++++++++++++++++++++");
		username = request.getParameter("username");
		password = request.getParameter("password");
		string_code = request.getParameter("code");
		String value = request.getSession().getAttribute("ValidateCode").toString();
		String sql = "select * from user where username=? and password=md5(?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			String judge = (String) request.getSession().getAttribute("user");
			/*if(judge != null){
				request.getRequestDispatcher("/home/user_index.jsp")
				.forward(request, response);
			}else{*/
				if(rs.next()&&string_code.equals(value)){
					int point = 0;
					point = rs.getInt(4);
					System.out.println(point);
					request.getSession().setAttribute("user_point", point);
					request.getSession().setAttribute("user", username);
	            	request.getRequestDispatcher("/home/user_index.jsp")
	            	.forward(request, response);
				}else{
					PrintWriter out = response.getWriter();
	            	response.setContentType("text/html");
	            	out.print("<script language='javascript'>alert('login failed!!');" +
	            			"window.location.href='/Final/home/login.jsp';</script>");
	            	out.flush();
	            	out.close();
				}
			//}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		try {
			conn = (Connection) MySQLTools.getMySQLConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
