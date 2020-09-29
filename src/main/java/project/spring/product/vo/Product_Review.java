package project.spring.product.vo;

import java.sql.Timestamp;

public class Product_Review {
	private int reIdx;
	private String prCode;
	private int reStar;
	private String reImg;
	private String reContent;
	private String insertId;
	private Timestamp insertDay;
	public int getReIdx() {
		return reIdx;
	}
	public void setReIdx(int reIdx) {
		this.reIdx = reIdx;
	}
	public String getPrCode() {
		return prCode;
	}
	public void setPrCode(String prCode) {
		this.prCode = prCode;
	}
	public int getReStar() {
		return reStar;
	}
	public void setReStar(int reStar) {
		this.reStar = reStar;
	}
	public String getReImg() {
		return reImg;
	}
	public void setReImg(String reImg) {
		this.reImg = reImg;
	}
	public String getReContent() {
		return reContent;
	}
	public void setReContent(String reContent) {
		this.reContent = reContent;
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
	
	
}
