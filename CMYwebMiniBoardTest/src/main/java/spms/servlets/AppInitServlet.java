package spms.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class AppInitServlet extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {

		System.out.println("AppInitServlet 준비...");
		super.init(config);
		

		try {
			
			ServletContext sc = this.getServletContext();
			
			String driver = "";
			String url = "";
			String user = "";
			String password = ""; 
			
			Connection conn = null;
			
			driver = sc.getInitParameter("driver");
			url = sc.getInitParameter("url");
			user = sc.getInitParameter("user");
			password = sc.getInitParameter("password");
			
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, password);
			
			sc.setAttribute("conn", conn);
			System.out.println("DB 연결 성공");
			
		}catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}
	
	@Override
	public void destroy() {

		System.out.println("AppInitServlet 마무리...");
		super.destroy();
		
		ServletContext sc = this.getServletContext();
		
		Connection conn = (Connection)sc.getAttribute("conn");
		
		try {
			
			if(conn != null) {
				conn.close();
				System.out.println("DB 연결 해제");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	
	}
	
}
