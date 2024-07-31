package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import spms.dto.BoardDto;
import spms.dto.MemberDto;

public class BoardDao {

	private Connection connection;
	
	public void setConnection(Connection conn) {
		
		this.connection = conn;
	}
	
	public List<BoardDto> selectList() throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "";

			sql += "SELECT FREE_BOARD_ID, FREE_BOARD_TITLE, FREE_BOARD_CONTENT, FREE_BOARD_WRITER, CREATE_DATE, UPDATE_DATE";
			sql += " FROM FREE_BOARD";
			sql += " ORDER BY FREE_BOARD_ID DESC";

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery(sql);
			
			int freeBoardId = 0;
			String freeBoardTitle = "";
			String freeBoardContent = "";
			String freeBoardWriter = "";
			Date createdDate;
			Date updateDate;

			ArrayList<BoardDto> boardList = new ArrayList<BoardDto>();

			BoardDto boardDto = null;

			while (rs.next()) {
				
				freeBoardId 		= rs.getInt("FREE_BOARD_ID");
				freeBoardTitle 		= rs.getString("FREE_BOARD_TITLE");
				freeBoardContent 	= rs.getString("FREE_BOARD_CONTENT");
				freeBoardWriter 	= rs.getString("FREE_BOARD_WRITER");
				createdDate 		= rs.getDate("CREATE_DATE");
				updateDate 			= rs.getDate("UPDATE_DATE");

				boardDto = new BoardDto(freeBoardId, freeBoardTitle, freeBoardContent, freeBoardWriter, createdDate, updateDate);

				boardList.add(boardDto);
				
			}
			
			return boardList;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;

		} finally {

			try {
				if(rs != null) {
					rs.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pstmt != null) {
					pstmt.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} 			
	}
	
	//게시글 상세정보 조회
	public BoardDto boardDetail(int id) throws Exception {
		
		BoardDto boardDto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			String sql = "";
			
			sql = "SELECT TA.FREE_BOARD_ID, TA.FREE_BOARD_TITLE, TA.FREE_BOARD_CONTENT, TA.FREE_BOARD_WRITER, TO_CHAR(TA.CREATE_DATE, '\"\"YYYY\"년\"MM\"월\"DD\"일 \"HH24\":\"MI\":\"SS\"') AS CREATE_DATE, TB.EMAIL";
			sql += " FROM FREE_BOARD TA, MEMBER TB";
			sql += " WHERE TA.MEMBER_NO = ? AND TA.MEMBER_NO = TB.MEMBER_NO";			
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			int freeBoardId 			= 0;
			String freeBoardTitle 		= "";
			String freeBoardContent		= "";
			String freeBoardWriter 		= "";
			String createdDate 			= null;
			String email 				= "";
			
			boardDto = new BoardDto();
			
			if (rs.next()) {
				
				freeBoardId 		= rs.getInt("FREE_BOARD_ID");
				freeBoardTitle 		= rs.getString("FREE_BOARD_TITLE");
				freeBoardContent 	= rs.getString("FREE_BOARD_CONTENT");
				freeBoardWriter 	= rs.getString("FREE_BOARD_WRITER");
				createdDate 		= rs.getString("CREATE_DATE");
				email 				= rs.getString("EMAIL");
				
				boardDto.setFreeBoardId(freeBoardId);
				boardDto.setFreeBoardTitle(freeBoardTitle);
				boardDto.setFreeBoardContent(freeBoardContent);
				boardDto.setFreeBoardWriter(freeBoardWriter);
				boardDto.setCreatedDateN(createdDate);
				boardDto.setEmail(email);
				
			}else {
				
				throw new Exception("해당 번호의 게시글을 찾을 수 없습니다");
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("boardDetail 예외 발생");
				
		}finally {
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return boardDto;
	}	
}
