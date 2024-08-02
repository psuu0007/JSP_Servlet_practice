package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import spms.dto.MemberDto;

public class MemberDao {
	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	public List<MemberDto> selectList() throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "";

			sql += "SELECT MEMBER_NO, EMAIL, PWD, MEMBER_NAME, CRE_DATE\r\n";
			sql += "FROM MEMBER\r\n";
			sql += "ORDER BY MEMBER_NO ASC";

			pstmt = connection.prepareStatement(sql);

			// db에 sql문 전달, 실행
			rs = pstmt.executeQuery(sql);

			int memberNo = 0;
			String memberName = "";
			String email = "";
			Date creDate = null;

			ArrayList<MemberDto> memberList = new ArrayList<>();

			MemberDto memberDto = null;

			while (rs.next()) {
				memberNo = rs.getInt("MEMBER_NO");
				email = rs.getString("EMAIL");
				memberName = rs.getString("MEMBER_NAME");
				creDate = rs.getDate("CRE_DATE");

				memberDto = new MemberDto(memberNo, email, memberName, creDate);

				memberList.add(memberDto);
			}

			return memberList;
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
	
	// 회원 등록
	public int memberInsert(MemberDto memberDto) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String email = memberDto.getEmail();
			String pwd = memberDto.getPassword();
			String memberName = memberDto.getMemberName();
			
			String sql = "";
			
			sql = "INSERT INTO MEMBER";
			sql += " (MEMBER_NO, EMAIL, PWD, MEMBER_NAME, CRE_DATE, MOD_DATE)";
			sql += " VALUES (MEMBER_NO_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, SYSDATE)";
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			pstmt.setString(3, memberName);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("memberDAO memberInsert() 예외 발생");
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				} 
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}	
		}
		
		return result;
	}
	
	// 회원 삭제
	public int memberDelete(int memberNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "";
			sql += "DELETE FROM MEMBER";
			sql += " WHERE MEMBER_NO= ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("memberDAO memberDelete() 예외 발생");
		}  finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				} 
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}	
		}
		
		return result;
	}
	
	// 회원 상세 정보 조회
	public MemberDto memberSelectOne(int memberNo) throws Exception {
		MemberDto memberDto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String sql = "";
		
		sql += "SELECT MEMBER_NO, EMAIL, MEMBER_NAME, CRE_DATE";
		sql += " FROM MEMBER";
		sql += " WHERE MEMBER_NO = ?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			String memberName = "";
			String email = "";
			Date creDate = null;
			
			if (rs.next()) {
				memberName = rs.getString("MEMBER_NAME");
				email = rs.getString("EMAIL");
				creDate = rs.getDate("CRE_DATE");
				
				memberDto = new MemberDto(memberNo, email, memberName, creDate);
			} else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
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
		
		return memberDto;
	}
	
	// 회원 정보 변경
	public int memberUpdate(MemberDto memberDto) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "";
			
			sql += "UPDATE MEMBER";
			sql += " SET EMAIL = ?, MEMBER_NAME = ?";
			sql += " WHERE MEMBER_NO = ?";
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, memberDto.getEmail());
			pstmt.setString(2, memberDto.getMemberName());
			pstmt.setInt(3, memberDto.getMemberNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				} 
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}	
		} // finally end
		
		return result;
	}
	
	// 사용자 존재 유무 / 없으면 null 리턴
	public MemberDto memberExist(String email, String password) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		
		sql += "SELECT MEMBER_NO, EMAIL, PWD, MEMBER_NAME";
		sql += " FROM MEMBER";
		sql += " WHERE EMAIL = ? AND PWD = ?";
		
		String memberName = "";
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			int colIndex = 1;
			
			pstmt.setString(colIndex++, email);
			pstmt.setString(colIndex, password);
			
			rs = pstmt.executeQuery();
			
			MemberDto memberDto = new MemberDto();
			
			if(rs.next()) {
				email = rs.getString("EMAIL");
				memberName = rs.getString("MEMBER_NAME");
				
				memberDto.setEmail(email);
				memberDto.setMemberName(memberName);
							
				return memberDto;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
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
				// TODO: handle exception
				e.printStackTrace();
			}	
		} // finally end
		
		return null;
	}
		
}
