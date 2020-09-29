package project.spring.drink.vo;

import java.security.Timestamp;

public class DrinkVO {
	
	private String dkCode;
	private String dkName;
	private String dkBkind;
	private String dkBkindValue;
	private String dkSkind;
	private String dkSkindValue;
	private String dkImg;
	private int dkAlcohol;
	private String dkCountry;
	private String dkCity;
	private String dkPlace;
	private String dkFood;
	
	private String dkContent1;
	private String dkContent2;
	private String dkContent3;
	private String dkContent;
	
	private int dkLike;
	private int dkApprove;
	private String insertId;
	private Timestamp insertDay;

	public String getDkCode() {
		return dkCode;
	}
	public void setDkCode(String dkCode) {
		this.dkCode = dkCode;
	}
	public String getDkName() {
		return dkName;
	}
	public void setDkName(String dkName) {
		this.dkName = dkName;
	}
	public String getDkBkind() {
		return dkBkind;
	}
	public void setDkBkind(String dkBkind) {
		this.dkBkind = dkBkind;
	}
	public String getDkBkindValue() {
		return dkBkindValue;
	}
	public void setDkBkindValue(String dkBkindValue) {
		this.dkBkindValue = dkBkindValue;
	}
	public String getDkSkind() {
		return dkSkind;
	}
	public void setDkSkind(String dkSkind) {
		this.dkSkind = dkSkind;
	}
	public String getDkSkindValue() {
		return dkSkindValue;
	}
	public void setDkSkindValue(String dkSkindValue) {
		this.dkSkindValue = dkSkindValue;
	}
	public String getDkImg() {
		return dkImg;
	}
	public void setDkImg(String dkImg) {
		this.dkImg = dkImg;
	}
	public int getDkAlcohol() {
		return dkAlcohol;
	}
	public void setDkAlcohol(int dkAlcohol) {
		this.dkAlcohol = dkAlcohol;
	}
	public String getDkCountry() {
		return dkCountry;
	}
	public void setDkCountry(String dkCountry) {
		this.dkCountry = dkCountry;
	}
	public String getDkCity() {
		return dkCity;
	}
	public void setDkCity(String dkCity) {
		this.dkCity = dkCity;
	}
	public String getDkPlace() {
		return dkPlace;
	}
	public void setDkPlace(String dkPlace) {
		this.dkPlace = dkPlace;
	}
	public String getDkFood() {
		return dkFood;
	}
	public void setDkFood(String dkFood) {
		this.dkFood = dkFood;
	}
	public int getDkLike() {
		return dkLike;
	}
	public void setDkLike(int dkLike) {
		this.dkLike = dkLike;
	}
	public int getDkApprove() {
		return dkApprove;
	}
	public void setDkApprove(int dkApprove) {
		this.dkApprove = dkApprove;
	}
	public String getInsertId() {
		return insertId;
	}
	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}
	public String getDkContent1() {
		return dkContent1;
	}
	public void setDkContent1(String dkContent1) {
		this.dkContent1 = dkContent1;
	}
	public String getDkContent2() {
		return dkContent2;
	}
	public void setDkContent2(String dkContent2) {
		this.dkContent2 = dkContent2;
	}
	public String getDkContent3() {
		return dkContent3;
	}
	public void setDkContent3(String dkContent3) {
		this.dkContent3 = dkContent3;
	}
	public Timestamp getInsertDay() {
		return insertDay;
	}
	public void setInsertDay(Timestamp insertDay) {
		this.insertDay = insertDay;
	}
	public String getDkContent() {
		return dkContent;
	}
	public void setDkContent() {
		String c1 = getDkContent1() != null ? getDkContent1() : "";
		String c2 = getDkContent2() != null ? getDkContent2() : "";
		String c3 = getDkContent3() != null ? getDkContent3() : "";

		this.dkContent = c1+c2+c3;
	}
}
