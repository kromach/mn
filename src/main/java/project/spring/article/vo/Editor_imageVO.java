package project.spring.article.vo;

public class Editor_imageVO {
	private int ed_idx;
	private String img_url;
	private String kind;
	private String code;
	public int getEd_idx() {
		return ed_idx;
	}
	public void setEd_idx(int ed_idx) {
		this.ed_idx = ed_idx;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
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
	@Override
	public String toString() {
		return "Editor_imageVO [ed_idx=" + ed_idx + ", img_url=" + img_url + ", kind=" + kind + ", code=" + code + "]";
	}
}
