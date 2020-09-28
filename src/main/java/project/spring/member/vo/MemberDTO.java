package project.spring.member.vo;
import java.sql.Timestamp;
public class MemberDTO {
	private String id;
	private String pw;
	private String userKind;
	private String name;
	private String birth;
	private String tel;
	private String address;
	private String licenseNum;
	private String licenseImg;
	private int reportCount;
	private String reportNumber;
	private String isBan;
	private String isApprove;
	private Timestamp insertDate;
	private String isExit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getUserKind() {
		return userKind;
	}
	public void setUserKind(String userKind) {
		this.userKind = userKind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLicenseNum() {
		return licenseNum;
	}
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}
	public String getLicenseImg() {
		return licenseImg;
	}
	public void setLicenseImg(String licenseImg) {
		this.licenseImg = licenseImg;
	}
	public int getReportCount() {
		return reportCount;
	}
	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}
	public String getReportNumber() {
		return reportNumber;
	}
	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}
	public String getIsBan() {
		return isBan;
	}
	public void setIsBan(String isBan) {
		this.isBan = isBan;
	}
	public String getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}
	public Timestamp getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}
	public String getIsExit() {
		return isExit;
	}
	public void setIsExit(String isExit) {
		this.isExit = isExit;
	}
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw="
				+ pw + ", userKind=" + userKind
				+ ", name=" + name + ", birth="
				+ birth + ", tel=" + tel
				+ ", address=" + address
				+ ", licenseNum=" + licenseNum
				+ ", licenseImg=" + licenseImg
				+ ", reportCount=" + reportCount
				+ ", reportNumber=" + reportNumber
				+ ", isBan=" + isBan
				+ ", isApprove=" + isApprove
				+ ", insertDate=" + insertDate
				+ ", isExit=" + isExit + "]";
	}
	public MemberDTO(String id, String pw,
			String userKind, String name,
			String birth, String tel,
			String address, String licenseNum,
			String licenseImg, int reportCount,
			String reportNumber, String isBan,
			String isApprove,
			Timestamp insertDate, String isExit) {
		super();
		this.id = id;
		this.pw = pw;
		this.userKind = userKind;
		this.name = name;
		this.birth = birth;
		this.tel = tel;
		this.address = address;
		this.licenseNum = licenseNum;
		this.licenseImg = licenseImg;
		this.reportCount = reportCount;
		this.reportNumber = reportNumber;
		this.isBan = isBan;
		this.isApprove = isApprove;
		this.insertDate = insertDate;
		this.isExit = isExit;
	}
}
