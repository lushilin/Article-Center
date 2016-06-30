package home;
import java.util.ArrayList;
import java.sql.*;
import connect.MySQLTools;
import instance.*;
public class PageBean {

    private int curPage = 1; //当前是第几页
    private int maxPage; //一共有多少页
    private int maxRowCount; //一共有多少行
    public int rowsPerPage = 3; //每页多少行

    Connection conn;
    public ArrayList data;

    public PageBean() throws Exception {
        this.setPageBean();
    }
    public int getCurPage() {
        return curPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getMaxRowCount() {
        return maxRowCount;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public void setMaxRowCount(int maxRowCount) {
        this.maxRowCount = maxRowCount;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    //得到要显示于本页的数据
    public PageBean getResult(String page) throws Exception {
        try {
            PageBean pageBean = new PageBean();
            ArrayList list = new ArrayList();
            int pageNum = Integer.parseInt(page);
            int pageNum2 = pageNum-1;
            System.out.println(pageNum);
            Statement stmt = conn.createStatement();
            //String strSql = "select top " + pageNum * pageBean.rowsPerPage +
                           // " * from article";//改成你的表
            String strSql = "select * from article limit "
                           +pageNum2 *pageBean.rowsPerPage + ","
                            + pageBean.rowsPerPage;
            ResultSet rset = stmt.executeQuery(strSql);
            int i = 0;
            while (rset.next()) {

                    //这里要和表的字段对应起来！！！！
                    article item = new article();
                    item.setId(rset.getInt("id"));
                    item.setUsername(rset.getString("username"));
                    item.setTitle(rset.getString("title"));
                    item.setKey(rset.getString("keyword"));
                    item.setDescription(rset.getString("description"));
                    item.setPoint(rset.getInt("point"));
                    list.add(item);
                    System.out.print("love");
                    i++;
            }
            //MySQLTools.closeResultSet(rset);
            //MySQLTools.closeStatement(stmt);
            pageBean.setCurPage(pageNum);
            pageBean.data = list;
            return pageBean;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
    }
    public PageBean getSelectResult(String page,String keyword,String title) 
    		throws Exception{
    	try{
    		PageBean pageBean = new PageBean();
        	ArrayList list = new ArrayList();
        	int pageNum = Integer.parseInt(page);
        	PreparedStatement ps = null;
        	String strSql = null;
        	if(title==null&&keyword!=null){
        		strSql = "select * from article where keyword=? limit "
                        +(pageNum-1) *pageBean.rowsPerPage + ","
                        + pageBean.rowsPerPage;
        		ps = conn.prepareStatement(strSql);
        		ps.setString(1, keyword);
        	}
        	if(title!=null&&keyword==null){
        		strSql = "select * from article where title=? limit "
                        +(pageNum-1) *pageBean.rowsPerPage + ","
                         + pageBean.rowsPerPage;
        		ps = conn.prepareStatement(strSql);
        		ps.setString(1, title);
        	}
        	if(title!=null&&keyword!=null){
        		strSql = "select * from article where title=? and keyword=? limit "
                        +(pageNum-1) *pageBean.rowsPerPage + ","
                        + pageBean.rowsPerPage;
        		ps = conn.prepareStatement(strSql);
        		ps.setString(1, title);
        		ps.setString(2, keyword);
        	}
        	ResultSet rset = ps.executeQuery();
        	int i = 0;
        	while(rset.next()){
        		article item = new article();
                item.setId(rset.getInt("id"));
                item.setTitle(rset.getString("title"));
                item.setKey(rset.getString("keyword"));
                item.setDescription(rset.getString("description"));
                item.setPoint(rset.getInt("point"));
                list.add(item);
                i++;
        	}
        	pageBean.setCurPage(pageNum);
            pageBean.data = list;
            return pageBean;
    	} catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    //获取总行数
    public int getAvailableCount() throws Exception {
        int ret = 0;
        conn = MySQLTools.getMySQLConnection();
        Statement stmt = conn.createStatement();
        String strSql = "select * from article";//改成你的表
        ResultSet rset = stmt.executeQuery(strSql);
        while (rset.next()) {
            ret++;
        }
        return ret;
    }
    //初始化时对PageBean进行设置
    public void setPageBean() throws Exception {

        //得到总行数
        this.setMaxRowCount(this.getAvailableCount());

        if (this.maxRowCount % this.rowsPerPage == 0) { //根据总行数计算总页数
            this.maxPage = this.maxRowCount / this.rowsPerPage;
        } else {
            this.maxPage = this.maxRowCount / this.rowsPerPage + 1;
        }
    }

}