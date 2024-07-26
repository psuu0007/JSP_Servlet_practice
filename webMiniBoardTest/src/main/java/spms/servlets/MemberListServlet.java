package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spms.dto.MemberDto;

/**
 * ALT + SHIFT + J : API 주석 회 원목록 조회 구현
 * 
 */
@WebServlet(value = "/member/list")
public class MemberListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String driver = "";
		String url = "";
		String user = "";
		String password = "";

		// String contextPath = req.getContextPath();

		try {
			ServletContext sc = this.getServletContext();

			driver = sc.getInitParameter("driver");
			url = sc.getInitParameter("url");
			user = sc.getInitParameter("user");
			password = sc.getInitParameter("password");

			// 오라클 객체 불러오기
			Class.forName(driver);
			// 드라이브매니저에 jdbc 등록 -> db 연결 -> db 객체
			conn = DriverManager.getConnection(url, user, password);

			String sql = "";

			sql += "SELECT MEMBER_NO, EMAIL, MEMBER_NAME,"
					+ "CRE_DATE, TO_CHAR(MOD_DATE, 'YYYY-MM-DD HH24:MI:SS') as FORMAT_DATE";
			sql += " FROM MEMBER";
			sql += " ORDER BY MEMBER_NO ASC";

			// db에 sql문 결과 전달
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");

			ArrayList<MemberDto> memberList = new ArrayList<MemberDto>();

			int memberNo = 0;
			String memberName = "";
			String email = "";
			Date creDate = null;

			MemberDto memberDto = null;

			while (rs.next()) {
				memberNo = rs.getInt("MEMBER_NO");
				memberName = rs.getString("MEMBER_NAME");
				email = rs.getString("EMAIL");
				creDate = rs.getDate("CRE_DATE");

				memberDto = new MemberDto();

				memberDto.setMemberNo(memberNo);
				memberDto.setMemberName(memberName);
				memberDto.setEmail(email);
				memberDto.setCreatedDate(creDate);

				memberList.add(memberDto);
			}

			req.setAttribute("memberList", memberList);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/member/MemberListView.jsp");

			dispatcher.include(req, res);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// db 객체 메모리 해제
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}