package project.spring.article.vo;

import java.sql.Timestamp;

public class ArticleDTO {
	private int bnIdx;
	private String bnTitle;
	private String kind;
	private String dkCode;
	private int readcount;
	private int heart;
	private int report;
	private String content;
	private String insertId;
	private Timestamp insertDay;
	private String isDelete;
	private String thumbNail;
	private String nickname;
	private String name;
	private int writerTitleCnt;
	private String writerTitleName;
	
	public int getBnIdx() {
		return bnIdx;
	}
	public void setBnIdx(int bnIdx) {
		this.bnIdx = bnIdx;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getDkCode() {
		return dkCode;
	}
	public void setDkCode(String dkCode) {
		this.dkCode = dkCode;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getHeart() {
		return heart;
	}
	public void setHeart(int heart) {
		this.heart = heart;
	}
	public int getReport() {
		return report;
	}
	public void setReport(int report) {
		this.report = report;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getInsertId() {
		return insertId;
	}
	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}
	public Timestamp getInsertDay() {
		return insertDay;
	}
	public void setInsertDay(Timestamp insertDay) {
		this.insertDay = insertDay;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	public String getBnTitle() {
		return bnTitle;
	}
	public void setBnTitle(String bnTitle) {
		this.bnTitle = bnTitle;
	}
	
	public String getThumbNail() {
		return thumbNail;
	}
	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWriterTitleCnt() {
		return writerTitleCnt;
	}
	public void setWriterTitleCnt(
			int writerTitleCnt) {
		this.writerTitleCnt = writerTitleCnt;
	}
	public String getWriterTitleName() {
		return writerTitleName;
	}
	public void setWriterTitleName(
			String writerTitleName) {
		this.writerTitleName = writerTitleName;
	}
	@Override
	public String toString() {
		return "ArticleDTO [bnIdx=" + bnIdx
				+ ", bnTitle=" + bnTitle
				+ ", kind=" + kind + ", dkCode="
				+ dkCode + ", readcount="
				+ readcount + ", heart=" + heart
				+ ", report=" + report
				+ ", content=" + content
				+ ", insertId=" + insertId
				+ ", insertDay=" + insertDay
				+ ", isDelete=" + isDelete
				+ ", thumbNail=" + thumbNail
				+ ", nickname=" + nickname
				+ ", name=" + name
				+ ", writerTitleCnt="
				+ writerTitleCnt
				+ ", writerTitleName="
				+ writerTitleName + "]";
	}
}
