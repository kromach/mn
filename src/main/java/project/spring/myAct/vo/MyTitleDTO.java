package project.spring.myAct.vo;

import java.sql.Timestamp;

public class MyTitleDTO {

	private int tiIdx;
	private String id;
	private int titleIndex;
	private String chooseTitle;
	private Timestamp tiDate;
	
	public int getTiIdx() {
		return tiIdx;
	}
	public void setTiIdx(int tiIdx) {
		this.tiIdx = tiIdx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTitleIdx() {
		return titleIndex;
	}
	public void setTitleIdx(int titleIndex) {
		this.titleIndex = titleIndex;
	}
	public String getChooseTitle() {
		return chooseTitle;
	}
	public void setChooseTitle(String chooseTitle) {
		this.chooseTitle = chooseTitle;
	}
	public Timestamp getTiDate() {
		return tiDate;
	}
	public void setTiDate(Timestamp tiDate) {
		this.tiDate = tiDate;
	}
	
}
