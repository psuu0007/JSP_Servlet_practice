package spms.dto;

import java.util.Date;

public class BoardDto {

	private int freeBoardId;
	private int memberNo; 
	private String freeBoardTitle;        
	private String freeBoardContent;          
	private String freeBoardWriter;  
	private Date createdDate;     
	private Date updateDate;
	private String email;
	private String createdDateN;
	
	public BoardDto() {
		super();
	}
	
	public BoardDto(int freeBoardId, String freeBoardTitle, String freeBoardContent, String freeBoardWriter, Date createdDate, Date updateDate) {
		super();
		this.freeBoardId 		= freeBoardId;
		this.freeBoardTitle 	= freeBoardTitle;
		this.freeBoardContent 	= freeBoardContent;
		this.freeBoardWriter 	= freeBoardWriter;
		this.createdDate 		= createdDate;
		this.updateDate 		= updateDate;
	}
	
	public BoardDto(int freeBoardId, String freeBoardTitle, String freeBoardContent, String freeBoardWriter, String createdDateN, String email) {
		super();
		this.freeBoardId 		= freeBoardId;
		this.freeBoardTitle 	= freeBoardTitle;
		this.freeBoardContent 	= freeBoardContent;
		this.freeBoardWriter 	= freeBoardWriter;
		this.createdDateN 		= createdDateN;
		this.email 				= email;
	}
	
	public int getFreeBoardId() {
		return freeBoardId;
	}
	public void setFreeBoardId(int freeBoardId) {
		this.freeBoardId = freeBoardId;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getFreeBoardTitle() {
		return freeBoardTitle;
	}
	public void setFreeBoardTitle(String freeBoardTitle) {
		this.freeBoardTitle = freeBoardTitle;
	}
	public String getFreeBoardContent() {
		return freeBoardContent;
	}
	public void setFreeBoardContent(String freeBoardContent) {
		this.freeBoardContent = freeBoardContent;
	}
	public String getFreeBoardWriter() {
		return freeBoardWriter;
	}
	public void setFreeBoardWriter(String freeBoardWriter) {
		this.freeBoardWriter = freeBoardWriter;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCreatedDateN() {
		return createdDateN;
	}
	public void setCreatedDateN(String createdDateN) {
		this.createdDateN = createdDateN;
	}	
	
	@Override
	public String toString() {
		return "BoardDto [freeBoardId=" + freeBoardId + ", memberNo=" + memberNo + ", freeBoardTitle=" + freeBoardTitle
				+ ", freeBoardContent=" + freeBoardContent + ", freeBoardWriter=" + freeBoardWriter + ", createdDate="
				+ createdDate + ", updateDate=" + updateDate + "]";
	}
}
