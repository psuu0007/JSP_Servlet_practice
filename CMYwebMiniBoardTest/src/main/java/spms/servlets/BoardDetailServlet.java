package spms.servlets;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spms.dao.BoardDao;
import spms.dto.BoardDto;

@WebServlet(value = "/board/detail")
public class BoardDetailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Connection conn = null;

		try {
			
			int freeBoardId = Integer.parseInt(request.getParameter("freeBoardId"));
			
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			BoardDao boardDao = new BoardDao();
			boardDao.setConnection(conn);
			
			BoardDto boardDto = boardDao.boardDetail(freeBoardId);
			
			if (boardDto == null) {
				System.out.println("게시글 정보 조회 실패");
			}
			
			request.setAttribute("boardDto", boardDto);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("./BoardDetail.jsp");
			dispatcher.forward(request, response);
			

		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/Error.jsp");
				dispatcher.forward(request, response);
				
		}finally {
			

			
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		

		
	}
}
