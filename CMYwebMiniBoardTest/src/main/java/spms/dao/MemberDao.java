package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

			sql += "SELECT MEMBER_NO, EMAIL, PWD, MEMBER_NAME, CRE_DATE, MOD_DATE";
			sql += " FROM MEMBER";
			sql += " ORDER BY MEMBER_NO DESC";

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery(sql);

			int memberNo = 0;
			String memberName = "";
			String email = "";
			String password = "";
			Date creDate;
			Date modDate;

			ArrayList<MemberDto> memberList = new ArrayList<MemberDto>();

			MemberDto memberDto = null;

			while (rs.next()) {
				
				memberNo 	= rs.getInt("MEMBER_NO");
				email 		= rs.getString("EMAIL");
				password 	= rs.getString("PWD");
				memberName 	= rs.getString("MEMBER_NAME");
				creDate 	= rs.getDate("CRE_DATE");
				modDate 	= rs.getDate("MOD_DATE");

				memberDto = new MemberDto(memberNo, email, password, memberName, creDate, modDate);

				memberList.add(memberDto);
				
			}
			
			return memberList;
			
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
	
	public int memebrInsert(MemberDto memberDto) throws Exception {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			
			String memberName = memberDto.getMemberName();
			String email = memberDto.getEmail();
			String password = memberDto.getPassword();

			String sql = "";

			sql = "INSERT INTO MEMBER";
			sql += "(MEMBER_NO, EMAIL, PWD, MEMBER_NAME, CRE_DATE, MOD_DATE)";
			sql += "VALUES(MEMBER_NO_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, SYSDATE)";

			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setString(3, memberName);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("memebrInsert 예외 발생");
				
		}finally {
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}

		return result;
	}
	
	//회원 삭제
	public int memebrDelete(int no) throws Exception {
	
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		try {

			String sql = "";

			sql += "DELETE FROM MEMBER";
			sql += " WHERE MEMBER_NO=?";

			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("memebrDelete 예외 발생");
				
		}finally {
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}

		return result;
	}
	
	//회원 상세정보 조회
	public MemberDto memebrSelectOne(int no) throws Exception {
		
		MemberDto memberDto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			String sql = "";
			
			sql = "SELECT MEMBER_NO, EMAIL, PWD, MEMBER_NAME, CRE_DATE";
			sql += " FROM MEMBER";
			sql += " WHERE MEMBER_NO = ?";
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			int memberNo 		= 0;
			String memberName 	= "";
			String email 		= "";
			String pwd 			= "";
			Date creDate 		= null;
			
			memberDto = new MemberDto();
			
			if (rs.next()) {
				
				memberNo 	= rs.getInt("MEMBER_NO");
				memberName 	= rs.getString("MEMBER_NAME");
				email 		= rs.getString("EMAIL");
				pwd 		= rs.getString("PWD");
				creDate 	= rs.getDate("CRE_DATE");
				
				memberDto.setMemberNo(memberNo);
				memberDto.setMemberName(memberName);
				memberDto.setEmail(email);
				memberDto.setPassword(pwd);
				memberDto.setCreatedDate(creDate);
				
			}else {
				
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다");
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("memebrDelete 예외 발생");
				
		}finally {
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return memberDto;
	}
	
	//회원 정보변경
	public int memebrUpdate(MemberDto memberDto) throws Exception {

		int result = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			
			int memberNo 		= memberDto.getMemberNo();
			String memberName 	= memberDto.getMemberName();
			String email 		= memberDto.getEmail();

			String sql = "";

			sql += "UPDATE MEMBER SET EMAIL=?, MEMBER_NAME=?";
			sql += " WHERE MEMBER_NO=?";

			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, memberName);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("memebrUpdate 예외 발생");
				
		}finally {
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}

		return result;
	}
	
	//사용자 존재 유무 / 없으면 null 리턴
	public MemberDto memebrExist(String email, String password) throws Exception {
		
		return null;
	}
	
	//회원 로그인
	public MemberDto memebrLogin(MemberDto memberDto) throws Exception {

		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			int memberNo 		= memberDto.getMemberNo();
			String loginEmail 	= memberDto.getEmail();
			String loginPass	= memberDto.getPassword();
			
			String email 		= "";
			String memberName 	= "";

			String sql = "";

			sql += "SELECT MEMBER_NO, EMAIL, MEMBER_NAME";
			sql += " FROM MEMBER";
			sql += " WHERE EMAIL = ? AND PWD = ?";

			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, loginEmail);
			pstmt.setString(2, loginPass);
			
			rs = pstmt.executeQuery();
			
			memberDto = new MemberDto();
			
			if(rs.next()) {
				
				memberNo 	= rs.getInt("MEMBER_NO");
				email 		= rs.getString("EMAIL");
				memberName 	= rs.getString("MEMBER_NAME");
				
				memberDto.setMemberNo(memberNo);
				memberDto.setMemberName(memberName);
				memberDto.setEmail(email);
				
			} else {
				
				memberDto = null;
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("memebrLogin 예외 발생");
				
		}finally {
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}

		return memberDto;
	}	
	
}
