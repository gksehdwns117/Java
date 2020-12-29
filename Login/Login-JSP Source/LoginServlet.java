package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Member;
import service.MemberService;

public class LoginServlet extends HttpServlet {
	
	private MemberService service;
	public LoginServlet() {
		service=new MemberService();
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProc(req,resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProc(req,resp);
	}
	protected void doProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		//들어오는 경로 저장하는 변수
		String contextPath= req.getContextPath();
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
				resp.sendRedirect("login.do");
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
			
			if(service.joinCase(id, pw, name, email)) {
				req.setAttribute("msg", "회원가입이 완료되었습니다");
			}else req.setAttribute("msg", "이미 가입되어 있는 아이디입니다");
			req.getRequestDispatcher("joinResult.jsp").forward(req, resp);
		}
		else if(reqUri.equals(contextPath+"/memberUpdate")) {
			
		}
		
		
		else if(reqUri.equals(contextPath+"/memberList.do")) {
			ArrayList<Member> memberlist=null;
			memberlist=service.searchAll();
			req.setAttribute("memberlist", memberlist);
			req.getRequestDispatcher("memberList.jsp").forward(req, resp);
		}
	}
}
