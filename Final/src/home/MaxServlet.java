package home;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connect.MySQLTools;

public class MaxServlet extends HttpServlet {

	Connection conn;
	/**
	 * Constructor of the object.
	 */
	public MaxServlet() {
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

		String from_time = request.getParameter("from");
		String to_time = request.getParameter("to");
		String sql = "select filename,count(*) from record " +
				"where time between ? and ? group by  filename";
		String sql2 = "select upuser from record where time between ? and ? " +
				"group by upuser";
		PreparedStatement ps;
		PreparedStatement ps2;
		PreparedStatement ps3;
		ArrayList<String> filename_Array = new ArrayList<String> ();
		ArrayList<Integer> count_Array = new ArrayList<Integer> ();
		ArrayList<String> upuser_Array = new ArrayList<String> ();
		ArrayList<Integer> sum_Array = new ArrayList<Integer> ();
		try {
			ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, from_time);
			ps2.setString(2,to_time);
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()){
				String upuser = rs2.getString(1);
				upuser_Array.add(upuser);
				String sql3 = "select point from record " +
						"where upuser=? and time between ? and ?";
				ps3 = conn.prepareStatement(sql3);
				ps3.setString(1,upuser);
				ps3.setString(2,from_time);
				ps3.setString(3, to_time);
				ResultSet rs3 = ps3.executeQuery();
				int sum = 0;
				while(rs3.next()){
					sum += rs3.getInt(1);
				}
				//System.out.println(sum);
				sum_Array.add(sum);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,from_time);
			ps.setString(2,to_time);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				filename_Array.add(rs.getString(1));
				count_Array.add(rs.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("filename_Array", filename_Array);
		request.setAttribute("count_Array", count_Array);
		request.setAttribute("upuser_Array", upuser_Array);
		request.setAttribute("sum_Array", sum_Array);
		//filename_Array.clear();
		//count_Array.clear();
		request.getRequestDispatcher("/home/Max.jsp").forward(request, response);
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
