package admin;
import connect.MySQLTools;
import instance.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

    private Connection conn;                        //初始化连接
    private Statement stmt;   
    private PreparedStatement ps;//初始化数据库操作
    ResultSet rs = null;  
    ResultSet rs1 = null;
    int id = 0;
    String username1 = null;
    String password2 = null;
    int point = 0;
    int a = 1;
	/**
	 * Constructor of the object.
	 */
	public Login() {
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
		//MessageDigest md5=MessageDigest.getInstance("MD5");
        System.out.println(++a);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sql = "select * from admin where username=? and password=?";
		String select_sql = "select * from user";
		try {
			ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            stmt = conn.createStatement();
            rs1 = stmt.executeQuery(select_sql);
            List<user> list = new ArrayList<user>();
            while(rs1.next()){
            	id = rs1.getInt(1);
            	username1 = rs1.getString(2);
            	password2 = rs1.getString(3);
            	point = rs1.getInt(4);
            	user user = new user(id,username1,password2,point);
            	list.add(user);
            }
            request.setAttribute("list", list);
            String judge = (String) request.getSession().getAttribute("username");
                if(rs.next()|| judge!=null){
                	request.getSession().setAttribute("username", username);
                	request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
                }else{
                	//response.sendRedirect(""+request.getContextPath()+"/admin/login_Jsp.jsp");
                	PrintWriter out = response.getWriter();
                	response.setContentType("text/html");
                	out.print("<script language='javascript'>alert('账号密码错误!!');" +
                			"window.location.href='/Final/admin/login_Jsp.jsp';</script>");
                	out.flush();
                	out.close();
                }
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
		//PreparedStatement prestmt = null;
		
			try {
				conn = MySQLTools.getMySQLConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}

}
