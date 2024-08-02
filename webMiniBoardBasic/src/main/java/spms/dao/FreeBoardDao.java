package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import spms.dto.BaseDto;
import spms.dto.FreeBoardDto;
import spms.dto.MemberDto;

public class FreeBoardDao {
	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	public List<FreeBoardDto> selectList() throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";

			sql += "SELECT FREE_BOARD_ID, FREE_BOARD_TITLE, FREE_BOARD_CONTENT,";
			sql += " FREE_BOARD_WRITER, CREATE_DATE, UPDATE_DATE";
			sql += " FROM FREE_BOARD";
			sql += " ORDER BY FREE_BOARD_ID ASC";

			pstmt = connection.prepareStatement(sql);

			// db에 sql문 전달, 실행
			rs = pstmt.executeQuery(sql);

			int freeBoardId = 0;
			String freeBoardTitle = "";
			String freeBoardContent = "";
			String freeBoardWriter = "";
			Date creDate = null;
			Date upDate = null;

			ArrayList<FreeBoardDto> freeBoardList = new ArrayList<>();

			FreeBoardDto freeBoardDto = null;
			
			while (rs.next()) {
				freeBoardId = rs.getInt("FREE_BOARD_ID");
				freeBoardTitle = rs.getString("FREE_BOARD_TITLE");
				freeBoardContent = rs.getString("FREE_BOARD_CONTENT");
				freeBoardWriter = rs.getString("FREE_BOARD_WRITER");
				creDate = rs.getDate("CREATE_DATE");
				upDate = rs.getDate("UPDATE_DATE");

				freeBoardDto = new FreeBoardDto(freeBoardId, freeBoardTitle, freeBoardContent, freeBoardWriter, creDate, upDate);

				freeBoardList.add(freeBoardDto);
			}

			return freeBoardList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// db 객체 메모리 해제
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // finally 종료
	}

	// 게시글 정보 조회
	public ArrayList<BaseDto> boardSelectOne(int freeBoardId) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		try {
			sql += "SELECT F.FREE_BOARD_ID, F.MEMBER_NO, F.FREE_BOARD_TITLE, F.FREE_BOARD_CONTENT, "
					+ "F.FREE_BOARD_WRITER, TO_CHAR(F.CREATE_DATE, 'YYYY\"년\"MM\"월\"DD\"일\" HH24:MI:SS') AS FORMAT_DATE, M.EMAIL";
			sql += " FROM FREE_BOARD F INNER JOIN MEMBER M";
			sql += " ON F.MEMBER_NO = M.MEMBER_NO";
			sql += " WHERE FREE_BOARD_ID = ?";
			
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, freeBoardId);

			rs = pstmt.executeQuery();

			int freeboardId = 0;
			String freeBoardTitle = "";
			String freeBoardContent = "";
			String freeBoardWriter = "";
			String koreaDate = null;
			String email = "";
			
			ArrayList<BaseDto> boardDetailInfo = new ArrayList<>();
			
			if (rs.next()) {
				freeboardId = rs.getInt("FREE_BOARD_ID");
				freeBoardTitle = rs.getString("FREE_BOARD_TITLE");
				freeBoardContent = rs.getString("FREE_BOARD_CONTENT");
				freeBoardWriter = rs.getString("FREE_BOARD_WRITER");
				koreaDate = rs.getString("FORMAT_DATE");
				email = rs.getString("EMAIL");

				FreeBoardDto freeBoardDto = new FreeBoardDto(freeboardId, freeBoardTitle, freeBoardContent, freeBoardWriter,
						koreaDate);
				
				MemberDto memberDto = new MemberDto(email);
				
				boardDetailInfo.add(freeBoardDto);
				boardDetailInfo.add(memberDto);

				return boardDetailInfo;
				
			} else {
				throw new Exception("해당 게시글을 찾을 수 없습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
