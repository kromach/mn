package project.spring.adminEvent.vo;

import java.sql.Timestamp;

public class AdminEventVO {

	private int ed_idx;
	private String imgUrl;
	private String kind;
	private String code;
	private String eventCode;
	private String eventName;
	private String productCode;
	private String evStart;
	private String evEnd;
	private String content;
	private String thumImg;
	private String isOpen;
	private String insertId;
	private Timestamp insertDay;
	private String prCode;
	private String prName;
	
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
	public int getEd_idx() {
		return ed_idx;
	}
	public void setEd_idx(int ed_idx) {
		this.ed_idx = ed_idx;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getEvStart() {
		return evStart;
	}
	public void setEvStart(String evStart) {
		this.evStart = evStart;
	}
	public String getEvEnd() {
		return evEnd;
	}
	public void setEvEnd(String evEnd) {
		this.evEnd = evEnd;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getThumImg() {
		return thumImg;
	}
	public void setThumImg(String thumImg) {
		this.thumImg = thumImg;
	}
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
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
