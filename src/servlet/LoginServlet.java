package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.MySQLDB;


public class LoginServlet extends HttpServlet {
	//重写doGet方法
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,
            IOException {
		String phone = request.getParameter("phone");   
		String password = request.getParameter("password");   
		
		String queryUserSQL = "select COUNT(*) from user where phone = '"+phone+"'";
		String queryLoginSQL = "select COUNT(*) from user where phone = '"+phone+"' and password = '"+password+"'";
		
		MySQLDB a = new MySQLDB();
		ResultSet ret = null;  
		String result = null;
		int rowCount=0;
		try {
			ret = a.init(queryUserSQL).executeQuery();
			 while(ret.next()){  
	            rowCount = ret.getInt(1);  
	         } 
			 if(rowCount==0){
				//用户名不存在
				result = "0";
			 }else{
				 ret = a.init(queryLoginSQL).executeQuery();
				 while(ret.next()){  
			       rowCount = ret.getInt(1);  
			     } 
				 if(rowCount==0){
					//密码错误
					result = "2"; 
				 }else{
					//登录成功
					result = "1"; 
				 }
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//服务器端打印信息
		//设置编码格式
		response.setContentType("text/html;charset=utf-8");
		
		//返回html页面
		response.getWriter().write(result);
		}                 	
	//重写doPost方法
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException,
            IOException {
		doGet(request, response);               	
	}     
}
