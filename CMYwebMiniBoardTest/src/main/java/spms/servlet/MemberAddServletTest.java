package spms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MemberAddServletTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		
		String htmlStr = "";
		
		htmlStr += "<html><head><title>회원등록</title>";
		htmlStr += "<style>table {width: 100%;border: 1px solid #e3e3e3;border-collapse: collapse;}th, td {border: 1px solid #e3e3e3;padding: 10px;}</style>";
		htmlStr += "<script type='text/javascript' src='../check.js'></script>";
		htmlStr += "</head>";
		htmlStr += "<body>";
		htmlStr += "<h1>회원등록</h1>";
		
		htmlStr += "<form action='add' method='post' onsubmit='return addCheck()'>";
		htmlStr += "<table border='1'>";
		htmlStr += "<tr>";
		htmlStr += "<td style='width:20%; background: #f3f3f3'>이름</td>";
		htmlStr += "<td><input type='text' id='memberName' name='memberName'></td>";
		htmlStr += "</tr>";
		htmlStr += "<tr>";
		htmlStr += "<td style='background: #f3f3f3'>이메일</td>";
		htmlStr += "<td><input type='text' id='email' name='email'></td>";
		htmlStr += "</tr>";
		htmlStr += "<tr>";
		htmlStr += "<td style='background: #f3f3f3'>암호</td>";
		htmlStr += "<td><input type='password' id='password' name='password'></td>";
		htmlStr += "</tr>";
		htmlStr += "</table>";
		htmlStr += "<div style='padding-top: 20px;text-align: center;'>";
		htmlStr += "<input type='submit' value='추가'>&nbsp;&nbsp;";
		htmlStr += "<input type='reset' value='취소' onclick=javascript:location.href='list'>";
		htmlStr += "</div>";
		htmlStr += "</form>";
		
		htmlStr += "</body></html>";
		
		out.println(htmlStr);
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
			String pwdStr = req.getParameter("password");
			String memberNameStr = req.getParameter("memberName");
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "";
			
			sql += "INSERT INTO MEMBER";
			sql += " (MEMBER_NO, EMAIL, PWD, MEMBER_NAME, CRE_DATE, MOD_DATE)";
			sql += " VALUES(MEMBER_NO_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, SYSDATE)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, emailStr);
			pstmt.setString(2, pwdStr);
			pstmt.setString(3, memberNameStr);
			
			pstmt.executeUpdate();
			
			res.sendRedirect("./list");
			
//			res.setContentType("text/html");
//			res.setCharacterEncoding("utf-8");		
			
//			PrintWriter out = res.getWriter();
			
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
//			htmlStr += "등록 성공입니다!";
//			htmlStr += "</p>";
//			htmlStr += "</body>";
//			htmlStr += "</html>";			
//			
//			out.println(htmlStr);
//			
//			res.addHeader("Refresh", "20; url=./list");

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
