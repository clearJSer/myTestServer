package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.sendsms;


public class SendMsgServlet extends HttpServlet {
	sendsms s = new sendsms();
	//重写doGet方法
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		String phone = request.getParameter("phone");   
		String msg_user = request.getParameter("msg_user")==""?null:request.getParameter("msg_user");  
		String msg_password = request.getParameter("msg_password")==""?null:request.getParameter("msg_user");
		
		String msg_code = s.getmsg(phone, msg_user, msg_password);
		//设置编码格式
		response.setContentType("text/html;charset=UTF-8");
		
		//返回html页面
		response.getWriter().write(msg_code);
	}                 	
	//重写doPost方法
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException,
            IOException {
		doGet(request, response);               	
	}     
}
