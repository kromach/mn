package project.spring.myAct.vo;

import java.sql.Timestamp;

public class AttendDTO {
	private int attIdx;
	private String id;
	private String attendYear;
	private String attendMonth;
	private String attendDay;
	private Timestamp insertDay;
	
	public int getAttIdx() {
		return attIdx;
	}
	public void setAttIdx(int attIdx) {
		this.attIdx = attIdx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAttendYear() {
		return attendYear;
	}
	public void setAttendYear(String attendYear) {
		this.attendYear = attendYear;
	}
	public String getAttendMonth() {
		return attendMonth;
	}
	public void setAttendMonth(String attendMonth) {
		this.attendMonth = attendMonth;
	}
	public String getAttendDay() {
		return attendDay;
	}
	public void setAttendDay(String attendDay) {
		this.attendDay = attendDay;
	}
	public Timestamp getInsertDay() {
		return insertDay;
	}
	public void setInsertDay(Timestamp insertDay) {
		this.insertDay = insertDay;
	}
	
	
}
