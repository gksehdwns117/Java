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
		
		//������ ��� �����ϴ� ����
		String contextPath= req.getContextPath();
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
				req.setAttribute("msg", "ȸ�������� �Ϸ�Ǿ����ϴ�");
			}else req.setAttribute("msg", "�̹� ���ԵǾ� �ִ� ���̵��Դϴ�");
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
