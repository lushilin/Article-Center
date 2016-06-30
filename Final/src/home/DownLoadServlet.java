package home;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connect.MySQLTools;
import instance.*;

public class DownLoadServlet extends HttpServlet {

	private Connection conn;
	/**
	 * Constructor of the object.
	 */
	public DownLoadServlet() {
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

		OutputStream out;
		InputStream in;
		String filename = request.getParameter("title");
		String real = new String(filename.getBytes("ISO-8859-1"), "utf-8"); 
		String x = request.getParameter("username");
		String username = new String(x.getBytes("ISO-8859-1"),"utf-8");
		String username2 = (String) request.getSession().getAttribute("user");
		String y = (String) request.getParameter("point");
		int point = Integer.parseInt(y);
		in = getServletContext().getResourceAsStream("/store/"+real);
		int length = in.available();
		response.setContentType("application/force-download");
		response.setHeader("Content-length", String.valueOf(length));
		response.setHeader("Content-Disposition", "attachment;filename=\""+real+"\" ");
		out = response.getOutputStream();
		int bytesRead = 0;
		byte[] buffer = new byte[512];
		boolean check = false;
		boolean check2 = false;
		while((bytesRead = in.read(buffer))!=-1){
			out.write(buffer,0,bytesRead);
			check = true;
		}
		in.close();
		out.close();
		
		PreparedStatement ps_in;
		PreparedStatement ps_out;
		PreparedStatement ps_record;
		PreparedStatement ps_check;
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		String record_sql = "insert into record" +
				"(id,downuser,upuser,filename,point,time) values(?,?,?,?,?,?)";
		String in_sql = "update user set point=point+? where username=?";
		String out_sql = "update user set point=point-? where username=?";
		String check_sql = "select * from record where downuser=? and filename=?";
		try {
			ps_check = conn.prepareStatement(check_sql);
			ps_check.setString(1,username2);
			ps_check.setString(2,real);
			ResultSet rs = ps_check.executeQuery();
			if(rs.next()){
				check2 = true;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(check==true&&check2==false){
			try {
				ps_in = conn.prepareStatement(in_sql);
				ps_out = conn.prepareStatement(out_sql);
				ps_record = conn.prepareStatement(record_sql);
				ps_in.setInt(1, point);
				ps_in.setString(2,username);
				ps_out.setInt(1,point);
				ps_out.setString(2,username2);
				ps_record.setString(1, null);
				ps_record.setString(2, username2);
				ps_record.setString(3, username);
				ps_record.setString(4,real);
				ps_record.setInt(5,point);
				ps_record.setString(6,time);
				ps_in.executeUpdate();
				ps_out.executeUpdate();
				ps_record.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(check==true&&check2==true){
			try {
				ps_record = conn.prepareStatement(record_sql);
				ps_record.setString(1, null);
				ps_record.setString(2, username2);
				ps_record.setString(3, username);
				ps_record.setString(4,real);
				ps_record.setInt(5,0);
				ps_record.setString(6,time);
				ps_record.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
