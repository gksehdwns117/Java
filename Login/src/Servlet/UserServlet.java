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
//		요청에 의해 들어오는 경로를 잡아주는 
//		ContextPath(프로젝트명까지),requestUri(프로젝트명+/서블릿명까지)
		
		String contextPath=req.getContextPath();
		String reqUri=req.getRequestURI();
		
		
		if(reqUri.equals(contextPath+"/login.do")) {
			String id= req.getParameter("id");
			String pw= req.getParameter("pw");
			
			if(service.loginDo(id, pw)) {
				req.setAttribute("msg", "로그인 성공입니다");
				req.getSession().setAttribute("id", id);
				//성공하면 아이디의 세션 획득
			}else {
				req.setAttribute("msg", "로그인 실패입니다");
			}
			req.getRequestDispatcher("loginResult.jsp").forward(req, resp);
		}
		else if(reqUri.equals(contextPath+"/loginForm.do")) {
			req.getRequestDispatcher("loginForm.jsp").forward(req, resp);
		}
		else if(reqUri.equals(contextPath+"/main.do")) {
			if(req.getSession().getAttribute("id")==null) {
				//로그인이 실패하면 다시 로그인 창으로
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
				req.setAttribute("msg", "회원가입이 완료되었습니다");
			}else req.setAttribute("msg", "이미 가입되어 있는 아이디입니다");
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
