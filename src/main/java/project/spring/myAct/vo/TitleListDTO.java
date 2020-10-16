package project.spring.myAct.vo;

public class TitleListDTO {
	
	private int titleIdx;
	private String titleName;
	private String titleDetail;
	private String titleKey;
	private int titleValue;
	private String chooseTitle;
	
	public int getTitleIdx() {
		return titleIdx;
	}
	public void setTitleIdx(int titleIdx) {
		this.titleIdx = titleIdx;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getTitleDetail() {
		return titleDetail;
	}
	public void setTitleDetail(String titleDetail) {
		this.titleDetail = titleDetail;
	}
	public String getTitleKey() {
		return titleKey;
	}
	public void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}
	public int getTitleValue() {
		return titleValue;
	}
	public void setTitleValue(int titleValue) {
		this.titleValue = titleValue;
	}
	public String getChooseTitle() {
		return chooseTitle;
	}
	public void setChooseTitle(String chooseTitle) {
		this.chooseTitle = chooseTitle;
	}

	@Override
	public String toString() {
		return "TitleListDTO [titleIdx=" + titleIdx + ", titleName=" + titleName + ", titleDetail=" + titleDetail
				+ ", titleKey=" + titleKey + ", titleValue=" + titleValue + "]";
	}
}	
