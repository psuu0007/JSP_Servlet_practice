package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spms.dao.MemberDao;
import spms.dto.MemberDto;

@WebServlet(value = "/member/add")
public class MemberAddServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {

		response.sendRedirect("./MemberForm.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Connection conn = null;

		try {
			
			String emailStr = request.getParameter("email");
			String pwdStr = request.getParameter("password");
			String memberNameStr = request.getParameter("memberName");
			
			MemberDto memberDto = new MemberDto();
			memberDto.setEmail(emailStr);
			memberDto.setPassword(memberNameStr);
			memberDto.setMemberName(memberNameStr);
			
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			int result;
			
			result = memberDao.memebrInsert(memberDto);
			
			if (result == 0) {
				System.out.println("회원가입 실패");
			}
			
			response.sendRedirect("./list");
			

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
