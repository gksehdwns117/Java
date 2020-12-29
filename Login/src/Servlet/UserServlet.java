package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.User;
import Service.UserService;

public class UserServlet extends HttpServlet {
	private UserService service;
	
	public UserServlet() {
		service=new UserService();
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProc(req,resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProc(req,resp);
	}
	protected void doProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		��û�� ���� ������ ��θ� ����ִ� 
//		ContextPath(������Ʈ�����),requestUri(������Ʈ��+/���������)
		
		String contextPath=req.getContextPath();
		String reqUri=req.getRequestURI();
		
		
		if(reqUri.equals(contextPath+"/login.do")) {
			String id= req.getParameter("id");
			String pw= req.getParameter("pw");
			
			if(service.loginDo(id, pw)) {
				req.setAttribute("msg", "�α��� �����Դϴ�");
				req.getSession().setAttribute("id", id);
				//�����ϸ� ���̵��� ���� ȹ��
			}else {
				req.setAttribute("msg", "�α��� �����Դϴ�");
			}
			req.getRequestDispatcher("loginResult.jsp").forward(req, resp);
		}
		else if(reqUri.equals(contextPath+"/loginForm.do")) {
			req.getRequestDispatcher("loginForm.jsp").forward(req, resp);
		}
		else if(reqUri.equals(contextPath+"/main.do")) {
			if(req.getSession().getAttribute("id")==null) {
				//�α����� �����ϸ� �ٽ� �α��� â����
				resp.sendRedirect("loginForm.do");
				return;
			}
			req.getRequestDispatcher("main.jsp").forward(req, resp);
		}
		else if(reqUri.equals(contextPath+"/joinForm.do")) {
			req.getRequestDispatcher("joinForm.jsp").forward(req, resp);
		}
		else if(reqUri.equals(contextPath+"/join.do")) {
			String id=req.getParameter("id");
			String pw=req.getParameter("pw");
			String name=req.getParameter("name");
			String email=req.getParameter("email");
			if(service.joinUser(id, pw, name, email)) {
				req.setAttribute("msg", "ȸ�������� �Ϸ�Ǿ����ϴ�");
			}else req.setAttribute("msg", "�̹� ���ԵǾ� �ִ� ���̵��Դϴ�");
			req.getRequestDispatcher("joinResult.jsp").forward(req, resp);
		}
		else if(reqUri.equals(contextPath+"/userUpdateForm.do")) {
			String id=req.getParameter("id");
			User user=service.showOne(id);
			req.setAttribute("user", user);
			req.getRequestDispatcher("userUpdateForm.jsp").forward(req, resp);
		}
		else if(reqUri.equals(contextPath+"/userUpdate.do"))
		{
			String id=req.getParameter("id");
			String pw=req.getParameter("pw");
			String name=req.getParameter("name");
			String email=req.getParameter("email");
			User user=new User();
			user.setId(id);user.setPw(pw);user.setName(name);user.setEmail(email);
			service.modifyUser(user);
			resp.sendRedirect("main.do");
			return;
		}
		else if(reqUri.equals(contextPath+"/userList.do")) {
			List<User> userlist=service.showAll();
			req.setAttribute("userlist", userlist);
			req.getRequestDispatcher("userList.jsp").forward(req, resp);
		}
	}
}
