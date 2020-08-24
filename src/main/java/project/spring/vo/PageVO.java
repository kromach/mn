package project.spring.vo;

public class PageVO {
	private int pageSize = 10;//보여줄 페이지 셋팅
	private int currPage;//현재 페이지 정보 셋팅
	private int startRow;//현재 페이지 시작
	private int endRow;//끝 게시물 번호
	private int count;//DB에 있는 게시글의 총개수를 담을 변수
	private int number;//게시판 상의 글번호 뿌려줄 변수
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public PageVO(int pageSize, int currPage,
			int startRow, int endRow, int count,
			int number) {
		super();
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.startRow = startRow;
		this.endRow = endRow;
		this.count = count;
		this.number = number;
	}
	@Override
	public String toString() {
		return "PageVO [pageSize=" + pageSize
				+ ", currPage=" + currPage
				+ ", startRow=" + startRow
				+ ", endRow=" + endRow
				+ ", count=" + count + ", number="
				+ number + "]";
	}
}
