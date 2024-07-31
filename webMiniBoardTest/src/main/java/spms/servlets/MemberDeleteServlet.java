package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

@WebServlet(value = "/member/delete")
public class MemberDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ServletContext sc = this.getServletContext();

		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String user = sc.getInitParameter("user");
		String password = sc.getInitParameter("password");
		
		int memberNo = Integer.parseInt(req.getParameter("memberNo"));
		
		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);

			String sql = "";
			String deSql = "";

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
			deSql += "DELETE FROM MEMBER";
			deSql += " WHERE MEMBER_NO = ?";

			pstmt = conn.prepareStatement(deSql);

			pstmt.setInt(1, memberNo);

			pstmt.executeUpdate();

			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");

			req.setAttribute("memberName", memberName);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/member/MemberDeleteView.jsp");

			dispatcher.forward(req, res);

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
