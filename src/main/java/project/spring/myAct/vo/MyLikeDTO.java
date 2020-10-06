package project.spring.myAct.vo;

public class MyLikeDTO {
	private int mlIdx;
	private String mlKind;
	private String mlValue;
	private String insertId;
	public int getMlIdx() {
		return mlIdx;
	}
	public void setMlIdx(int mlIdx) {
		this.mlIdx = mlIdx;
	}
	public String getMlKind() {
		return mlKind;
	}
	public void setMlKind(String mlKind) {
		this.mlKind = mlKind;
	}
	public String getMlValue() {
		return mlValue;
	}
	public void setMlValue(String mlValue) {
		this.mlValue = mlValue;
	}
	public String getInsertId() {
		return insertId;
	}
	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}
	@Override
	public String toString() {
		return "MyLikeDTO [mlIdx=" + mlIdx + ", mlKind=" + mlKind + ", mlValue=" + mlValue + ", insertId=" + insertId
				+ "]";
	}
	
	
	
	
}