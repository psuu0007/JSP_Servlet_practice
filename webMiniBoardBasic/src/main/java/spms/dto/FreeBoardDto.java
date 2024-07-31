package spms.dto;

import java.util.Date;

public class FreeBoardDto implements BaseDto {
	private int freeBoardId;
	private int memberNo;
	private String freeBoardTitle;
	private String freeBoardContent;
	private String freeBoardWriter;
	private Date creDate;
	private Date upDate;
	private String koreaDate;
	
	public FreeBoardDto() {
		super();
	}
	
	public FreeBoardDto(int freeBoardId, String freeBoardTitle, String freeBoardContent, String freeBoardWriter,
			String koreaDate) {
		super();
		this.freeBoardId = freeBoardId;
		this.freeBoardTitle = freeBoardTitle;
		this.freeBoardContent = freeBoardContent;
		this.freeBoardWriter = freeBoardWriter;
		this.koreaDate = koreaDate;
	}

	public FreeBoardDto(int freeBoardId, String freeBoardTitle, String freeBoardContent, String freeBoardWriter,
			Date creDate, Date upDate) {
		super();
		this.freeBoardId = freeBoardId;
		this.freeBoardTitle = freeBoardTitle;
		this.freeBoardContent = freeBoardContent;
		this.freeBoardWriter = freeBoardWriter;
		this.creDate = creDate;
		this.upDate = upDate;
	}

	public FreeBoardDto(int freeBoardId, int memberNo, String freeBoardTitle, String freeBoardContent,
			String freeBoardWriter, Date creDate, Date upDate, String koreaDate) {
		super();
		this.freeBoardId = freeBoardId;
		this.memberNo = memberNo;
		this.freeBoardTitle = freeBoardTitle;
		this.freeBoardContent = freeBoardContent;
		this.freeBoardWriter = freeBoardWriter;
		this.creDate = creDate;
		this.upDate = upDate;
		this.koreaDate = koreaDate;
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
	
	public Date getCreDate() {
		return creDate;
	}
	
	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}
	
	public Date getUpDate() {
		return upDate;
	}
	
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
	
	public String getKoreaDate() {
		return koreaDate;
	}

	public void setKoreaDate(String koreaDate) {
		this.koreaDate = koreaDate;
	}

	@Override
	public String toString() {
		return "FreeBoardDto [freeBoardId=" + freeBoardId + ", memberNo=" + memberNo + ", freeBoardTitle="
				+ freeBoardTitle + ", freeBoardContent=" + freeBoardContent + ", freeBoardWriter=" + freeBoardWriter
				+ ", creDate=" + creDate + ", upDate=" + upDate + "]";
	}
}
