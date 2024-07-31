package spms.servlets;

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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spms.dto.MemberDto;

@WebServlet(urlPatterns = {"/member/update"})
public class MemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ServletContext sc = this.getServletContext();
		
		String driver = sc.getInitParameter("driver");
		String url =  sc.getInitParameter("url");
		String user =  sc.getInitParameter("user");
		String password =  sc.getInitParameter("password");
		
		// DB 조회를 위해, 화면을 데이터 기반으로 구성하기 위해 필요한 데이터
		// Cannot parse null string
		int memberNo = Integer.parseInt(req.getParameter("memberNo"));
		
		String sql = "";
		
		try {
			
			Class.forName(driver);
			System.out.println("오라클 드라이버 로드");
			conn = DriverManager.getConnection(url, user, password);
			
			sql = "SELECT MEMBER_NO, EMAIL, PWD, MEMBER_NAME, CRE_DATE";
			sql += " FROM MEMBER";
			sql += " WHERE MEMBER_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			// 사용자에게 백단에서 무슨 일이 벌어진 건지 알려주는 화면을 제작해야 함
			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");
			
			String memberName = "";
			String email = "";
			String pwd = "";
			Date creDate = null;
			
			MemberDto memberDto = null;
			
			while (rs.next()) {
				memberName = rs.getString("MEMBER_NAME");
				email = rs.getString("EMAIL");
				pwd = rs.getString("PWD");
				creDate = rs.getDate("CRE_DATE");
				
				memberDto = new MemberDto();

				memberDto.setMemberNo(memberNo);
				memberDto.setMemberName(memberName);
				memberDto.setPassword(pwd);
				memberDto.setEmail(email);
				memberDto.setCreatedDate(creDate);
			}
			
			req.setAttribute("memberDto", memberDto);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/member/MemberUpdateView.jsp");

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
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}	
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}	
			}
		} // finally end
		
		res.setContentType("text/html");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ServletContext sc = this.getServletContext();
		
		String driver = sc.getInitParameter("driver");
		String url =  sc.getInitParameter("url");
		String user =  sc.getInitParameter("user");
		String password =  sc.getInitParameter("password");
		
		try {
			String emailStr = req.getParameter("email");
			String memberNameStr = req.getParameter("memberName");
			String pwdStr = req.getParameter("memberPwd");
			int memberNo = Integer.parseInt(req.getParameter("memberNo"));
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "";
			
			sql += "UPDATE MEMBER";
			sql += " SET EMAIL = ?, PWD = ?, MEMBER_NAME = ?, MOD_DATE = SYSDATE";
			sql += " WHERE MEMBER_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, emailStr);
			pstmt.setString(2, pwdStr);
			pstmt.setString(3, memberNameStr);
			pstmt.setInt(4, memberNo);
			
			pstmt.executeUpdate();
			
			res.sendRedirect("list");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}	
			}
			
			if(conn != null) {
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
