package project.spring.event.vo;

import java.sql.Timestamp;

public class EventVO {
	
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
	private int evIdx;
	private String id;
	private Timestamp joinInsertDay;
	
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
	public int getEvIdx() {
		return evIdx;
	}
	public void setEvIdx(int evIdx) {
		this.evIdx = evIdx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getJoinInsertDay() {
		return joinInsertDay;
	}
	public void setJoinInsertDay(Timestamp joinInsertDay) {
		this.joinInsertDay = joinInsertDay;
	}
	
	

}
