package filter;   

import java.io.IOException;     
    
import javax.servlet.Filter;     
import javax.servlet.FilterChain;     
import javax.servlet.FilterConfig;     
import javax.servlet.ServletException;     
import javax.servlet.ServletRequest;     
import javax.servlet.ServletResponse;     
import javax.servlet.http.HttpServletRequest;     
import javax.servlet.http.HttpServletResponse;     
import javax.servlet.http.HttpSession;     
/**   
 * �û�����Ȩ�޵Ĺ�����   
 * @author viano   
 */    
public class UsersFilter implements Filter {     
    
    public void destroy() {     
        // TODO Auto-generated method stub     
             
    }     
    
    public void doFilter(ServletRequest request, ServletResponse response,     
            FilterChain chain) throws IOException, ServletException {     
        // ����������ַ�����     
        request.setCharacterEncoding("UTF-8");     
        // ���÷���������ַ�����     
        response.setCharacterEncoding("UTF-8");     
        // ת��ServletRequestΪ HttpServletRequest     
        HttpServletRequest req = (HttpServletRequest) request;     
        // ת��ServletResponseΪHttpServletRequest     
        HttpServletResponse res = (HttpServletResponse) response;     
        // ��ȡSession     
        HttpSession session = req.getSession();     
        // ��ȡSession�д洢�Ķ���     
        Object o = session.getAttribute("user");   
        Object ob = session.getAttribute("username");
        // ��ȡ��ǰ�����URI     
        String url = req.getRequestURI();     
        // �ж�Session�еĶ����Ƿ�Ϊ�գ��ж������URI�Ƿ�Ϊ��������˵�URI 
        /*if(ob == null
        	&& !url.endsWith("login_Jsp.jsp")){
        	res.sendRedirect(req.getContextPath() + "/admin/login_Jsp.jsp"); 
        }else{
            chain.doFilter(request, response);     
            res.setHeader("Cache-Control","no-store");           
            res.setDateHeader("Expires",0);        
            res.setHeader("Pragma","no-cache");      
            res.flushBuffer();   
        }*/
        boolean check = false;
        if (o == null
            && !url.endsWith("login.jsp")     // ��URL��ַΪ�˽�β���ļ�������     
            && !url.endsWith("register.jsp")
            && !url.endsWith("login_Jsp.jsp")
            && !url.endsWith("index.jsp")
            && url.indexOf("Register") < 0
            && url.indexOf("validatecode") < 0    // ��URL��ַ�а������ַ������ļ�������     
            && url.indexOf("/image/") < 0
            && url.indexOf("user_login") < 0
            && url.indexOf("Login") < 0
            && url.indexOf("Logout") < 0
            && url.indexOf("Delete") < 0
            && url.indexOf("Modify") < 0
            && url.indexOf("Modify2") < 0) {     
            res.sendRedirect(req.getContextPath() + "/home/login.jsp"); 
            check = true;
        } /*else if(ob == null
        	&& !url.endsWith("login_Jsp.jsp")
        	&& url.indexOf("Login") < 0){
        	res.sendRedirect(req.getContextPath() + "/admin/login_Jsp.jsp");
        }*/	        
        else {     
            chain.doFilter(request, response);     
            res.setHeader("Cache-Control","no-store");           
            res.setDateHeader("Expires",0);        
            res.setHeader("Pragma","no-cache");      
            res.flushBuffer();     
        }    
 
             
    }     
    
    public void init(FilterConfig filterConfig) throws ServletException {     
        // TODO Auto-generated method stub     
             
    }     
    
}    