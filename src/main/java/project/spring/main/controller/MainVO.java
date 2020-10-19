package project.spring.main.controller;

public class MainVO {
	private String aLinkUri;
	private String imgUri;
	public String getaLinkUri() {
		return aLinkUri;
	}
	public void setaLinkUri(String aLinkUri) {
		this.aLinkUri = aLinkUri;
	}
	public String getImgUri() {
		return imgUri;
	}
	public void setImgUri(String imgUri) {
		this.imgUri = imgUri;
	}
	@Override
	public String toString() {
		return "MainVO [aLinkUri=" + aLinkUri + ", imgUri=" + imgUri + "]";
	}
}
