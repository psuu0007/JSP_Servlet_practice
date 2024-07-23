package spms.servlet;

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


/**
 * alt + shift + j : api 주석
 * 회원 목록 조회
 */
@SuppressWarnings("serial")
@WebServlet(value="/membertest/list")
public class MemberListServletTest extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ServletContext  sc = this.getServletContext();
		
		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String user = sc.getInitParameter("user");
		String password = sc.getInitParameter("password");
		
		try {
			Class.forName(driver);
			System.out.println("드라이버 로딩 완료");
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "";
			
			sql += "SELECT MEMBER_NO, EMAIL, PWD, MEMBER_NAME, CRE_DATE, MOD_DATE";
			sql += " FROM MEMBER";
			sql += " ORDER BY MEMBER_NO DESC";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			res.setContentType("text/html");
			res.setCharacterEncoding("utf-8");
			
			PrintWriter out = res.getWriter();
			
			String htmlStr = "";
			
			htmlStr += "<div style='text-align:center;padding-top:20px;'>";
				htmlStr += "<button class='btn btn-dark' onclick=javascript:location.href='add'>신규 회원 등록</button>";
			htmlStr += "</div>";
			
			RequestDispatcher dispatcher;
			
			dispatcher = sc.getRequestDispatcher("/include/incHead.jsp");
			dispatcher.include(req, res);
			dispatcher = sc.getRequestDispatcher("/include/incTop.jsp");
			dispatcher.include(req, res);
			
			out.println("<h2 class='mb-4 fw-bold'>회원목록</h2>");
			
			out.println("<table class='table table-bordered'>");
			out.println("<tr style='background: #f3f3f3'>");
			out.println("<td style='width: 10%;'>번호</td>");
			out.println("<td style='width: 20%;'>이름</td>");
			out.println("<td style='width: 20%;'>이메일</td>");
			out.println("<td style='width: 20%;'>등록일</td>");
			out.println("<td style='width: 20%;'>수정일</td>");
			out.println("<td style='width: 10%;'>비고</td>");
			out.println("</tr>");			
			
			while(rs.next() == true) {
				
				out.println("<tr>");
				out.println("<td>" + rs.getInt("MEMBER_NO") + "</td>");
				out.println("<td><a href='update?memberNo=" + rs.getInt("MEMBER_NO") + "'>" + rs.getString("MEMBER_NAME") + "</a></td>");
				out.println("<td>" + rs.getString("EMAIL") + "</td>");
				out.println("<td>" + rs.getDate("CRE_DATE") + "</td>");
				out.println("<td>" + rs.getString("MOD_DATE") + "</td>");
				out.println("<td><button class='btn btn-danger btn-sm' onclick=javascript:memberDelete(" + rs.getInt("MEMBER_NO") + ",'" + rs.getString("MEMBER_NAME") + "');>삭제</button></td>");
				out.println("</tr>");
			
			}
			
			out.println("</table>");
			out.println(htmlStr);
			
			out.println("<form id='memberForm' action='delete' method='post' onsubmit='return false'>");
			out.println("<input type='hidden' id='memberNo' name='memberNo' value=''>");
			out.println("<input type='hidden' id='memberName' name='memberName' value=''>");
			out.println("</form>");
			
			dispatcher = sc.getRequestDispatcher("/include/incFooter.jsp");
			dispatcher.include(req, res);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//db 객체 메모리 해제
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
		
		
	}

	
}
