package project.spring.product.vo;

import java.sql.Timestamp;

public class OrderVo {
	private String orCode;
	private String orDay;
	private int orSeq;
	private String prCode;
	private String prName;
	private int orStatus;
	private int prPrice;
	private int prCount;
	private String userId;
	private String receiverName;
	private String receiverTel;
	private String receiverAddr;
	private String deliveryCompany;
	private String deliveryNum;
	private Timestamp insertDay;
	private Timestamp modifyDay;
	

	private String orStatusValue;
	
	public String getOrStatusValue() {
		return orStatusValue;
	}
	public void setOrStatusValue(String orStatusValue) {
		this.orStatusValue = orStatusValue;
	}
	public String getOrCode() {
		return orCode;
	}
	public void setOrCode(String orCode) {
		this.orCode = orCode;
	}
	public String getOrDay() {
		return orDay;
	}
	public void setOrDay(String orDay) {
		this.orDay = orDay;
	}
	public int getOrSeq() {
		return orSeq;
	}
	public void setOrSeq(int orSeq) {
		this.orSeq = orSeq;
	}
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
	public int getOrStatus() {
		return orStatus;
	}
	public void setOrStatus(int orStatus) {
		this.orStatus = orStatus;
	}
	public int getPrPrice() {
		return prPrice;
	}
	public void setPrPrice(int prPrice) {
		this.prPrice = prPrice;
	}
	public int getPrCount() {
		return prCount;
	}
	public void setPrCount(int prCount) {
		this.prCount = prCount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverTel() {
		return receiverTel;
	}
	public void setReceiverTel(String receiverTel) {
		this.receiverTel = receiverTel;
	}
	public String getReceiverAddr() {
		return receiverAddr;
	}
	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}
	public String getDeliveryCompany() {
		return deliveryCompany;
	}
	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}
	public String getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(String deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public Timestamp getInsertDay() {
		return insertDay;
	}
	public void setInsertDay(Timestamp insertDay) {
		this.insertDay = insertDay;
	}
	public Timestamp getModifyDay() {
		return modifyDay;
	}
	public void setModifyDay(Timestamp modifyDay) {
		this.modifyDay = modifyDay;
	}
	
	
	
}
