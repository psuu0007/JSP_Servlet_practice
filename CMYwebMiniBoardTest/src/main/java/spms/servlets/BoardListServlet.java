package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spms.dao.BoardDao;
import spms.dto.BoardDto;

@WebServlet(value = "/board/list")
public class BoardListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn = null;
		
		try {
			
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			BoardDao boardDao = new BoardDao();
			boardDao.setConnection(conn);

			ArrayList<BoardDto> boardList = (ArrayList<BoardDto>) boardDao.selectList();

			request.setAttribute("boardList", boardList);

			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			RequestDispatcher dispatcher =
				request.getRequestDispatcher("/board/BoardListView.jsp");
			
			dispatcher.include(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			request.setAttribute("error", e);
			request.setAttribute("caseByCase", "상황에 맞는 처리 부탁");
			
			RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request, response);
			
		} 
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		super.doPost(req, res);
	}
}
