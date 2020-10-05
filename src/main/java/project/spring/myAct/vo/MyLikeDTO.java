package project.spring.myAct.vo;

public class MyLikeDTO {
	private int mlIdx;
	private String mlUrl;
	private String mlDiff;
	private String insertId;
	public int getMlIdx() {
		return mlIdx;
	}
	public void setMlIdx(int mlIdx) {
		this.mlIdx = mlIdx;
	}
	public String getMlUrl() {
		return mlUrl;
	}
	public void setMlUrl(String mlUrl) {
		this.mlUrl = mlUrl;
	}
	public String getMlDiff() {
		return mlDiff;
	}
	public void setMlDiff(String mlDiff) {
		this.mlDiff = mlDiff;
	}
	public String getInsertId() {
		return insertId;
	}
	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}
	@Override
	public String toString() {
		return "MyLikeDTO [mlIdx=" + mlIdx + ", mlUrl=" + mlUrl + ", mlDiff=" + mlDiff + ", insertId=" + insertId + "]";
	}
}

