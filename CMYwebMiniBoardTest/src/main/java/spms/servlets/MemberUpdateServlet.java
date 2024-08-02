package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spms.dao.MemberDao;
import spms.dto.MemberDto;

@WebServlet(value = "/member/update")
public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Connection conn = null;

		try {
			
			int memberNo = Integer.parseInt(request.getParameter("memberNo"));
			
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			MemberDto memberDto = memberDao.memebrSelectOne(memberNo);
			
			if (memberDto == null) {
				System.out.println("회원정보 조회 실패");
			}
			
			request.setAttribute("memberDto", memberDto);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("./MemberUpdateForm.jsp");
			dispatcher.forward(request, response);
			

		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/Error.jsp");
				dispatcher.forward(request, response);
				
		}finally {
			

			
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Connection conn = null;

		try {
			
			int memberNo 			= Integer.parseInt(request.getParameter("memberNo"));
			String emailStr 		= request.getParameter("email");
			String memberNameStr 	= request.getParameter("memberName");
			
			MemberDto memberDto = new MemberDto();
			memberDto.setMemberNo(memberNo);
			memberDto.setEmail(emailStr);
			memberDto.setMemberName(memberNameStr);
			
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			int result;
			
			result = memberDao.memebrUpdate(memberDto);
			
			if (result == 0) {
				System.out.println("회원정보 수정 실패");
			}
			
			response.sendRedirect("./update?memberNo=" + memberNo);
			

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
