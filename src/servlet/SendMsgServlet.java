package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.sendsms;


public class SendMsgServlet extends HttpServlet {
	sendsms s = new sendsms();
	//��дdoGet����
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		String phone = request.getParameter("phone");   
		String msg_user = request.getParameter("msg_user")==""?null:request.getParameter("msg_user");  
		String msg_password = request.getParameter("msg_password")==""?null:request.getParameter("msg_user");
		
		String msg_code = s.getmsg(phone, msg_user, msg_password);
		//���ñ����ʽ
		response.setContentType("text/html;charset=UTF-8");
		
		//����htmlҳ��
		response.getWriter().write(msg_code);
	}                 	
	//��дdoPost����
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException,
            IOException {
		doGet(request, response);               	
	}     
}
