package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.MySQLDB;


public class RgisterServlet extends HttpServlet {
	//重写doGet方法
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");   
		String password = request.getParameter("password"); 
		String phone = request.getParameter("phone");   
		String email = request.getParameter("email");   
		
		String queryUserSQL = "select COUNT(*) from user where phone = '"+phone+"'";
		String insertUserSQL = "insert into user(username,password,phone,email) values('"+username+"','"+password+"','"+phone+"','"+email+"')";
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
				 //该用户不存在 ，可以注册  false 代表更新 或者DDL
				 boolean addUser = a.init(insertUserSQL).execute();
				 //注册成功
				result="1";
			 }else{
				 //该用户已存在
				 result="0";
			 }
		} catch (SQLException e) {
			e.printStackTrace();
			result="2";
		}finally{
			//设置编码格式
			response.setContentType("text/html;charset=UTF-8");
			//返回html页面
			response.getWriter().write(result);
		}
	}                 	
	//重写doPost方法
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException,
            IOException {
		doGet(request, response);               	
	}     
}
