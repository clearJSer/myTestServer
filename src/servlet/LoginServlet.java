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
	//��дdoGet����
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
				//�û���������
				result = "0";
			 }else{
				 ret = a.init(queryLoginSQL).executeQuery();
				 while(ret.next()){  
			       rowCount = ret.getInt(1);  
			     } 
				 if(rowCount==0){
					//�������
					result = "2"; 
				 }else{
					//��¼�ɹ�
					result = "1"; 
				 }
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//�������˴�ӡ��Ϣ
		//���ñ����ʽ
		response.setContentType("text/html;charset=utf-8");
		
		//����htmlҳ��
		response.getWriter().write(result);
		}                 	
	//��дdoPost����
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException,
            IOException {
		doGet(request, response);               	
	}     
}
