package spms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MemberUpdateServletTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ServletContext  sc = this.getServletContext();
		
		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String user = sc.getInitParameter("user");
		String password = sc.getInitParameter("password");
		
		int memberNo = Integer.parseInt(req.getParameter("memberNo"));
		
		try {
			
			Class.forName(driver);
			System.out.println("드라이버 로딩 완료");
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "";
			
			sql += "SELECT MEMBER_NO, EMAIL, PWD, MEMBER_NAME, CRE_DATE";
			sql += " FROM MEMBER";
			sql += " WHERE MEMBER_NO=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			String memberName = "";
			String email = "";
			Date creDate = null;
			
			while (rs.next()) {
				
				memberName = rs.getString("MEMBER_NAME");
				email = rs.getString("EMAIL");
				password = rs.getString("PWD");
				creDate = rs.getDate("CRE_DATE");
			}
			
			res.setContentType("text/html");
			res.setCharacterEncoding("utf-8");		
			
			PrintWriter out = res.getWriter();
			
			String htmlStr = "";
			
			RequestDispatcher dispatcher;
			
			dispatcher = sc.getRequestDispatcher("/include/incHead.jsp");
			dispatcher.include(req, res);
			dispatcher = sc.getRequestDispatcher("/include/incTop.jsp");
			dispatcher.include(req, res);
			
			htmlStr += "<h1 class='mb-4 fw-bold'>회원상세정보</h1>";
			
			htmlStr += "<form action='update' method='post' onsubmit='return passCheck()'>";
			htmlStr += "<table class='table table-bordered'>";
			htmlStr += "<input type='hidden' id='passN' name='password' value='" + password + "'>";
			htmlStr += "<tr>";
			htmlStr += "<td style='width:10%; background: #f3f3f3'>번호</td>";
			htmlStr += "<td><input type='text' name='memberNo' class='form-control' value='" + memberNo + "' readonly></td>";
			htmlStr += "</tr>";
			htmlStr += "<tr>";
			htmlStr += "<td style='background: #f3f3f3'>이름</td>";
			htmlStr += "<td><input type='text' name='memberName' class='form-control' value='" + memberName + "'></td>";
			htmlStr += "</tr>";
			htmlStr += "<tr>";
			htmlStr += "<td style='background: #f3f3f3'>이메일</td>";
			htmlStr += "<td><input type='text' name='email' class='form-control' value='" + email + "'></td>";
			htmlStr += "</tr>";
			htmlStr += "<tr>";
			htmlStr += "<tr>";
			htmlStr += "<td style='background: #f3f3f3'>이전 비밀번호</td>";
			htmlStr += "<td><input type='password' id='pass1' name='password1' class='form-control' value=''></td>";
			htmlStr += "</tr>";
			htmlStr += "<tr>";
			htmlStr += "<td style='background: #f3f3f3'>새로운 비밀번호</td>";
			htmlStr += "<td><input type='password' id='pass2' name='password2' class='form-control' value=''></td>";
			htmlStr += "</tr>";
			htmlStr += "<tr>";			
			htmlStr += "<td style='background: #f3f3f3'>가입일</td>";
			htmlStr += "<td>" + creDate + "</td>";
			htmlStr += "</tr>";			
			htmlStr += "</table>";
			htmlStr += "<div style='padding-top: 20px;text-align: center;'>";
			htmlStr += "<input type='submit' value='정보수정' class='btn btn-primary'>&nbsp;&nbsp;";
			htmlStr += "<input type='button' value='정보삭제' class='btn btn-danger' onclick=javascript:memberDelete(" + memberNo + ",'" + memberName + "');>&nbsp;&nbsp;";
			htmlStr += "<input type='reset' value='취소' class='btn btn-secondary' onclick=javascript:location.href='list'>";
			htmlStr += "</div>";
			htmlStr += "</form>";
			
			htmlStr += "<form id='memberForm' action='delete' method='post' onsubmit='return false'>";
			htmlStr += "<input type='hidden' id='memberNo' name='memberNo' value=''>";
			htmlStr += "<input type='hidden' id='memberName' name='memberName' value=''>";
			htmlStr += "</form>";
			
			htmlStr += "</body></html>";
			
			out.println(htmlStr);
			
			dispatcher = sc.getRequestDispatcher("/include/incFooter.jsp");
			dispatcher.include(req, res);


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ServletContext  sc = this.getServletContext();
		
		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String user = sc.getInitParameter("user");
		String password = sc.getInitParameter("password");	
		
		try {
			String emailStr = req.getParameter("email");
			String passwordStr = req.getParameter("password");
			String memberNameStr = req.getParameter("memberName");
			
			int memberNo = Integer.parseInt(req.getParameter("memberNo"));
			
			Class.forName(driver);
			System.out.println("드라이버 로딩 완료");
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "";
			
			sql += "UPDATE MEMBER SET EMAIL=?, PWD=?, MEMBER_NAME=?";
			sql += " WHERE MEMBER_NO=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, emailStr);
			pstmt.setString(2, passwordStr);
			pstmt.setString(3, memberNameStr);
			pstmt.setInt(4, memberNo);
			
			pstmt.executeUpdate();
			
			res.sendRedirect("./list");
			
//			res.setContentType("text/html");
//			res.setCharacterEncoding("utf-8");		
//			
//			PrintWriter out = res.getWriter();
//			
//			String htmlStr = "";
//			
//			htmlStr += "<html lang='ko'>";
//			htmlStr += "<head>";
//			//htmlStr += "<meta charset='UTF-8' http-equiv='Refresh' content='1; url=list'>";
//			htmlStr += "<meta charset='UTF-8'>";
//			htmlStr += "<title>회원목록</title>";
//			htmlStr += "</head>";
//			htmlStr += "<body>";
//			htmlStr += "<p>";
//			htmlStr += "변경 성공입니다!";
//			htmlStr += "</p>";
//			htmlStr += "</body>";
//			htmlStr += "</html>";			
//			
//			out.println(htmlStr);
//			
//			res.addHeader("Refresh", "2; url=./list");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		}

	}
}
