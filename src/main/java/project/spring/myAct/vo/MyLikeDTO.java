package project.spring.myAct.vo;

public class MyLikeDTO {
	private int mlIdx;
	private String mlUrl;
	private String kind;
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
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getInsertId() {
		return insertId;
	}
	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}
	
	@Override
	public String toString() {
		return "MyLikeDTO [mlIdx=" + mlIdx + ", mlUrl=" + mlUrl + ", kind=" + kind + ", insertId=" + insertId + "]";
	}
	
	
	
	
}