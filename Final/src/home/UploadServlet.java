package home;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import connect.MySQLTools;

import instance.*;

public class UploadServlet extends HttpServlet {

	private String filePath;
	private String tempFilePath;
	private Connection conn;
	String title;
	String key_word;
	String description;
	int point;
	int count;
	boolean check = false;
	/**
	 * Constructor of the object.
	 */
	public UploadServlet() {
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

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter outNet = response.getWriter();
		try{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4*1024);
			factory.setRepository(new File(tempFilePath));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(4*1024*1024);
			upload.setHeaderEncoding("utf-8");
			List items =  upload.parseRequest(request);
			Iterator iter = items.iterator();
			count = 1;
			while(iter.hasNext()){
				FileItem item = (FileItem) iter.next();
				if(item.isFormField()){
					processFormField(item,outNet);
				}else{
					processUploadedFile(item,outNet);
				}
				count++;
			}
			//outNet.close();
		}catch(Exception e){
			throw new ServletException(e);
		}
		/**
		 * 
		 *
		title = request.getParameter("title");
		key_word = request.getParameter("keyword");
		description = request.getParameter("description");
		String string_point = request.getParameter("point");
		System.out.println(title);
		System.out.println(string_point);
		point = Integer.parseInt(string_point);
		*/
		String sql = "insert into article " +
				"(id,username,title,keyword,description,point)values(?,?,?,?,?,?)";
		String username = (String) request.getSession().getAttribute("user");
		PreparedStatement ps;
		if(check==false){
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, null);
				ps.setString(2,username);
				ps.setString(3,title);
				ps.setString(4, key_word);
				ps.setString(5, description);
				ps.setInt(6, point);
				ps.execute();
				response.sendRedirect(""+request.getContextPath()+"/home/release.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			response.sendRedirect(""+request.getContextPath()+"/home/release.jsp");
		}
	}
	//上传文件部分执行
	private void processUploadedFile(FileItem item, PrintWriter outNet) throws Exception {
		// TODO Auto-generated method stub
		String filename = item.getName();
		int index = filename.lastIndexOf("\\");
		filename = filename.substring(index+1,filename.length());
		int index2 = filename.lastIndexOf(".");
		String checkname = filename.substring(index2 + 1, filename.length());
		System.out.println(checkname);
		//filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
		long fileSize = item.getSize();
		if(!checkname.equals("doc")&&!checkname.equals("pdf")&&!checkname.equals("txt")){
			System.out.println("1515151511515");
			check=true;
			return;
		}
		if(filename.equals("")&&fileSize==0){
			return;
		}
		check=false;
		File uploadedFile = new File(filePath+"/"+filename);
		item.write(uploadedFile);
		outNet.println(filename+" is saved.");
		outNet.println("The size of "+filename+" is "+fileSize+"\r\n");
	}
	//普通表单域执行
	private void processFormField(FileItem item, PrintWriter outNet) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String name = item.getFieldName();
		String value = item.getString("utf-8");
		//System.out.println(value);
		switch(count){
		case 1:title = value;break;
		case 2:key_word = value;break;
		case 3:description = value;break;
		case 4:point = Integer.parseInt(value);break;
		default:break;
		}
		System.out.println(count);
		outNet.println(name+":"+value+"\r\n");
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
		filePath = config.getInitParameter("filePath");
		tempFilePath = config.getInitParameter("tempFilePath");
		filePath = getServletContext().getRealPath(filePath);
		tempFilePath = getServletContext().getRealPath(tempFilePath);
		try {
			conn = MySQLTools.getMySQLConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
