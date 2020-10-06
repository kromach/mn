package project.spring.drink.vo;

import java.sql.Timestamp;

public class CommentVO {
	
	private int cmIdx;
	private String dkCode;
	private int cmLike;
	private int cmUnLike;
	private String cmComment;
	private int item1;
	private int item2;
	private int item3;
	private int item4;
	private int item5;
	private String insertId;
	private String nickname;
	private int writerTitleCnt;
	private String writerTitleName;
	private Timestamp insertDay;
	private String isDelete;
	
	public int getCmIdx() {
		return cmIdx;
	}
	public void setCmIdx(int cmIdx) {
		this.cmIdx = cmIdx;
	}
	public String getDkCode() {
		return dkCode;
	}
	public void setDkCode(String dkCode) {
		this.dkCode = dkCode;
	}
	public int getCmLike() {
		return cmLike;
	}
	public void setCmLike(int cmLike) {
		this.cmLike = cmLike;
	}
	public int getCmUnLike() {
		return cmUnLike;
	}
	public void setCmUnLike(int cmUnLike) {
		this.cmUnLike = cmUnLike;
	}
	public String getCmComment() {
		return cmComment;
	}
	public void setCmComment(String cmComment) {
		this.cmComment = cmComment;
	}
	public int getItem1() {
		return item1;
	}
	public void setItem1(int item1) {
		this.item1 = item1;
	}
	public int getItem2() {
		return item2;
	}
	public void setItem2(int item2) {
		this.item2 = item2;
	}
	public int getItem3() {
		return item3;
	}
	public void setItem3(int item3) {
		this.item3 = item3;
	}
	public int getItem4() {
		return item4;
	}
	public void setItem4(int item4) {
		this.item4 = item4;
	}
	public int getItem5() {
		return item5;
	}
	public void setItem5(int item5) {
		this.item5 = item5;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getWriterTitleCnt() {
		return writerTitleCnt;
	}
	public void setWriterTitleCnt(int writerTitleCnt) {
		this.writerTitleCnt = writerTitleCnt;
	}
	public String getWriterTitleName() {
		return writerTitleName;
	}
	public void setWriterTitleName(String writerTitleName) {
		this.writerTitleName = writerTitleName;
	}
	
}
