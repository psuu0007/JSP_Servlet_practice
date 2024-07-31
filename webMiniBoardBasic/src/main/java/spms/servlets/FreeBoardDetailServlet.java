package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spms.dao.FreeBoardDao;
import spms.dto.BaseDto;
import spms.dto.FreeBoardDto;
import spms.dto.MemberDto;

/**
 * ALT + SHITF + J: API 주석 회원 목록 조회 구현
 * 
 */
@WebServlet(value = "/freeBoard/detail")
public class FreeBoardDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// JDBC 실행 순서
		// DB 객체 준비
		Connection conn = null;
		
		try {	
			int freeBoardId = Integer.parseInt(req.getParameter("boardId"));
			
			ServletContext sc = this.getServletContext();

			// 미리 준비된 DB 객체 불러오기
			conn = (Connection) sc.getAttribute("conn");

			FreeBoardDao freeBoardDao = new FreeBoardDao();
			freeBoardDao.setConnection(conn);

			ArrayList<BaseDto> boardDetailInfo = (ArrayList<BaseDto>) freeBoardDao.boardSelectOne(freeBoardId);

			FreeBoardDto freeBoardDto = null;
			MemberDto memberDto = null;
			
			for (int i = 0; i < boardDetailInfo.size(); i++) {
				
				if (boardDetailInfo.get(i) instanceof FreeBoardDto) {
					freeBoardDto = (FreeBoardDto) boardDetailInfo.get(i);
				}
				else if (boardDetailInfo.get(i) instanceof MemberDto) {
					memberDto = (MemberDto) boardDetailInfo.get(i);
				}
			}

			req.setAttribute("freeBoardDto", freeBoardDto);
			req.setAttribute("memberDto", memberDto);

			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/freeBoard/FreeBoardDetailView.jsp");

			dispatcher.forward(req, res);

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

			// throw new ServletException(e);

			req.setAttribute("error", e);
			req.setAttribute("caseByCase", "상황에 맞는 처리 부탁");

			RequestDispatcher dispatcher = req.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(req, res);
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
