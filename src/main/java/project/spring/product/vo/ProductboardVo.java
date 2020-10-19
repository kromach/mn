package project.spring.product.vo;

import java.security.Timestamp;

public class ProductboardVo {
	private int prBoardIdex;
	private String prCode;
	private String insertId;
	private String prBoardTitle;
	private String prBoardKind;
	private int prBoardReadcount;
	private int prBoardLike;
	private String prBoardContent;
	private Timestamp insertDay;
	private String isDelete;
	public int getPrBoardIdex() {
		return prBoardIdex;
	}
	public void setPrBoardIdex(int prBoardIdex) {
		this.prBoardIdex = prBoardIdex;
	}
	public String getPrCode() {
		return prCode;
	}
	public void setPrCode(String prCode) {
		this.prCode = prCode;
	}
	public String getInsertId() {
		return insertId;
	}
	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}
	public String getPrBoardTitle() {
		return prBoardTitle;
	}
	public void setPrBoardTitle(String prBoardTitle) {
		this.prBoardTitle = prBoardTitle;
	}
	public String getPrBoardKind() {
		return prBoardKind;
	}
	public void setPrBoardKind(String prBoardKind) {
		this.prBoardKind = prBoardKind;
	}
	public int getPrBoardReadcount() {
		return prBoardReadcount;
	}
	public void setPrBoardReadcount(int prBoardReadcount) {
		this.prBoardReadcount = prBoardReadcount;
	}
	public int getPrBoardLike() {
		return prBoardLike;
	}
	public void setPrBoardLike(int prBoardLike) {
		this.prBoardLike = prBoardLike;
	}
	public String getPrBoardContent() {
		return prBoardContent;
	}
	public void setPrBoardContent(String prBoardContent) {
		this.prBoardContent = prBoardContent;
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
	
	
}
