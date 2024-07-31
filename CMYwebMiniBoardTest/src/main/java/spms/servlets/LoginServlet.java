package spms.servlets;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import spms.dao.MemberDao;
import spms.dto.MemberDto;

@WebServlet(value = "/auth/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/LoginForm.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Connection conn = null;
		
		try {
			
			String loginEmail 		= request.getParameter("loginEmail");
			String loginPass 		= request.getParameter("loginPass");
			
			MemberDto memberDto = new MemberDto();
			memberDto.setMemberNo(0);
			memberDto.setEmail(loginEmail);
			memberDto.setPassword(loginPass);
			
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			memberDto = memberDao.memebrLogin(memberDto);
			
			if (memberDto == null) {
				
				System.out.println("회원 로그인 실패!");
				response.sendRedirect("./login?setLogin=fail");
			} else {
				
				System.out.println("회원 로그인 성공!");
				HttpSession session = request.getSession();
				session.setAttribute("ssoMemberNo", memberDto.getMemberNo());
				session.setAttribute("ssoMemberName", memberDto.getMemberName());
				session.setAttribute("ssoEmail", memberDto.getEmail());
				response.sendRedirect("../member/list");
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/Error.jsp");
				dispatcher.forward(request, response);
				
		}finally {
			

			
		}
	}
	
}
