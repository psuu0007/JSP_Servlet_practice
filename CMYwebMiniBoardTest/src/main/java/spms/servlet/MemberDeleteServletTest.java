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
public class MemberDeleteServletTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {



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

			String memberNameStr = req.getParameter("memberName");
			int memberNo = Integer.parseInt(req.getParameter("memberNo"));
			
			Class.forName(driver);
			System.out.println("드라이버 로딩 완료");
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "";
			
			sql += "DELETE FROM MEMBER";
			sql += " WHERE MEMBER_NO=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			pstmt.executeUpdate();
			
			//res.sendRedirect("./list");
			
			res.setContentType("text/html");
			res.setCharacterEncoding("utf-8");		
			
			PrintWriter out = res.getWriter();
			
			String htmlStr = "";
			
			RequestDispatcher dispatcher;
			
			dispatcher = sc.getRequestDispatcher("/include/incHead.jsp");
			dispatcher.include(req, res);
			dispatcher = sc.getRequestDispatcher("/include/incTop.jsp");
			dispatcher.include(req, res);			
			
			htmlStr += "<H2>";
			htmlStr += memberNameStr + "님의 회원 정보가 성공적으로 삭제 되었습니다!";
			htmlStr += "</H2>";
			htmlStr += "<br><br><button class='btn btn-secondary' onclick=javascript:location.href='list'>회원목록</button>";
			htmlStr += "</body>";
			htmlStr += "</html>";			
			
			out.println(htmlStr);
			
			dispatcher = sc.getRequestDispatcher("/include/incFooter.jsp");
			dispatcher.include(req, res);

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
