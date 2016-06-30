package home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public void init() throws ServletException {
	  }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws
    ServletException, IOException {
		this.doPost(request, response);
	}

	  //Process the HTTP Post request
	  public void doPost(HttpServletRequest request, HttpServletResponse response) throws
	      ServletException, IOException {
	    try
	      {
	    	PageBean page1 = new PageBean();
	    	//PageBean page1=new PageBean();
	    	PageBean page2=page1.getResult((String)request.getParameter("jumpPage"));
	    	//把PageBean保存到request对象中。注意：viewpage.jsp里jsp:useBean id必须为才可以"page2"！！！
	    	request.setAttribute("page2",page2);
	      }
	      catch(Exception e)
	      {
	              e.printStackTrace();
	      }

	          /**
	       *把视图派发到viewForum.jsp
	       */
	      javax.servlet.RequestDispatcher dis=request.getRequestDispatcher("/home/viewpage.jsp");
	      dis.forward(request,response);

	 }

}
