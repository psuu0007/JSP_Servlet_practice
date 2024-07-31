package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spms.dao.MemberDao;

@WebServlet(value = "/member/delete")
public class MemberDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int memberNo = Integer.parseInt(req.getParameter("memberNo"));

		try {

			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.serConnection(conn);

			int result;
			
			result = memberDao.memberDelete(memberNo);
			
			// 0: 추가한 회원 X
			// 1: 추가한 회원 1명
			if (result == 0) {
				System.out.println("회원 삭제 실패");
				System.out.println("실패에 관련된 로직 처리해야 함");
			}
			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");
			
			res.sendRedirect("./list");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			req.setAttribute("error", e);
			req.setAttribute("caseByCase", "상황에 맞는 처리 부탁");

			RequestDispatcher dispatcher = req.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(req, res);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}
}
