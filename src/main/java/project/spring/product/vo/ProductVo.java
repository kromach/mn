package project.spring.product.vo;

import java.sql.Timestamp;

public class ProductVo {
	private String prCode;
	private String prName;
	private String prImg;
	private String prBkind;
	private String prSkind;
	private String prCountry;
	private String prCity;
	private String prAlcohol;
	private String prPlace;
	private String prFood;
	private String prContent;
	private int prPrice;
	private int prAmount;
	private int prTotalcount;
	private int prLike;
	private String prUse;
	private String insertId;
	private Timestamp insertDay;
	public String getPrCode() {
		return prCode;
	}
	public void setPrCode(String prCode) {
		this.prCode = prCode;
	}
	public String getPrName() {
		return prName;
	}
	public void setPrName(String prName) {
		this.prName = prName;
	}
	public String getPrImg() {
		return prImg;
	}
	public void setPrImg(String prImg) {
		this.prImg = prImg;
	}
	public String getPrBkind() {
		return prBkind;
	}
	public void setPrBkind(String prBkind) {
		this.prBkind = prBkind;
	}
	public String getPrSkind() {
		return prSkind;
	}
	public void setPrSkind(String prSkind) {
		this.prSkind = prSkind;
	}
	public String getPrCountry() {
		return prCountry;
	}
	public void setPrCountry(String prCountry) {
		this.prCountry = prCountry;
	}
	public String getPrCity() {
		return prCity;
	}
	public void setPrCity(String prCity) {
		this.prCity = prCity;
	}
	public String getPrAlcohol() {
		return prAlcohol;
	}
	public void setPrAlcohol(String prAlcohol) {
		this.prAlcohol = prAlcohol;
	}
	public String getPrPlace() {
		return prPlace;
	}
	public void setPrPlace(String prPlace) {
		this.prPlace = prPlace;
	}
	public String getPrFood() {
		return prFood;
	}
	public void setPrFood(String prFood) {
		this.prFood = prFood;
	}
	public String getPrContent() {
		return prContent;
	}
	public void setPrContent(String prContent) {
		this.prContent = prContent;
	}
	public int getPrPrice() {
		return prPrice;
	}
	public void setPrPrice(int prPrice) {
		this.prPrice = prPrice;
	}
	public int getPrAmount() {
		return prAmount;
	}
	public void setPrAmount(int prAmount) {
		this.prAmount = prAmount;
	}
	public int getPrTotalcount() {
		return prTotalcount;
	}
	public void setPrTotalcount(int prTotalcount) {
		this.prTotalcount = prTotalcount;
	}
	public int getPrLike() {
		return prLike;
	}
	public void setPrLike(int prLike) {
		this.prLike = prLike;
	}
	public String getPrUse() {
		return prUse;
	}
	public void setPrUse(String prUse) {
		this.prUse = prUse;
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
	public ProductVo() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProductVo [prCode=" + prCode + ", prName=" + prName + ", prImg=" + prImg + ", prBkind=" + prBkind
				+ ", prSkind=" + prSkind + ", prCountry=" + prCountry + ", prCity=" + prCity + ", prAlcohol="
				+ prAlcohol + ", prPlace=" + prPlace + ", prFood=" + prFood + ", prContent=" + prContent + ", prPrice="
				+ prPrice + ", prAmount=" + prAmount + ", prTotalcount=" + prTotalcount + ", prLike=" + prLike
				+ ", prUse=" + prUse + ", insertId=" + insertId + ", insertDay=" + insertDay + "]";
	}
}
