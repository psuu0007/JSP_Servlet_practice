package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/member/delete")
public class MemberDeleteServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ServletContext sc = this.getServletContext();

		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String user = sc.getInitParameter("user");
		String password = sc.getInitParameter("password");
		
		try {
			int memberNo = Integer.parseInt(req.getParameter("memberNo"));

			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);

			String sql = "";

			// 이름 조회
			sql = "SELECT MEMBER_NAME";
			sql += " FROM MEMBER";
			sql += " WHERE MEMBER_NO = ?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			String memberName = "";
			
			while (rs.next()) {
				memberName = rs.getString("MEMBER_NAME");
			}
			
			// member 삭제
			sql = "DELETE FROM MEMBER";
			sql += " WHERE MEMBER_NO = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

			pstmt.executeUpdate();

			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");

			PrintWriter out = res.getWriter();

			String htmlStr = "";

			htmlStr += "<html>";
			htmlStr += "<head>";
			htmlStr += "<title>회원 정보</title>";
			htmlStr += "</head>";
			htmlStr += "<body>";
			htmlStr += "<h1>" + memberName + " 회원이 삭제되었습니다.</h1>";
			htmlStr += "<button onclick='location.href=\"./list\"'>list 페이지로 이동</button>";
			htmlStr += "</body>";
			htmlStr += "</html>";

			out.println(htmlStr);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}	
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		} // finally end
	}
}
