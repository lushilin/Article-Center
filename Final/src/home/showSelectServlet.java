package home;

import instance.article;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connect.MySQLTools;

public class showSelectServlet extends HttpServlet {

	Connection conn;
	List<article> list = new ArrayList<article>();
	/**
	 * Constructor of the object.
	 */
	public showSelectServlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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

		String keyword = (String) request.getSession().getAttribute("keyword");
		String title = (String) request.getSession().getAttribute("title");
		System.out.println(title);
		String sql="";
		PreparedStatement ps;
		ResultSet rs = null;
		if(keyword!=""&&title!=""){
			sql = "select * from article where keyword=? and title=?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, keyword);
				ps.setString(2,title);
				rs = ps.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(keyword==""&&title!=""){
			sql = "select * from article where title=?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, title);
				rs = ps.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(keyword!=""&&title==""){
			sql = "select * from article where keyword=?";
			System.out.print(1);
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, keyword);
				rs = ps.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			list.clear();
			while(rs.next()){
                article item = new article();
                item.setId(rs.getInt("id"));
                item.setUsername(rs.getString("username"));
                item.setTitle(rs.getString("title"));
                item.setKey(rs.getString("keyword"));
                item.setDescription(rs.getString("description"));
                item.setPoint(rs.getInt("point"));
                list.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/home/viewpsge2.jsp").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		try {
			conn = MySQLTools.getMySQLConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
