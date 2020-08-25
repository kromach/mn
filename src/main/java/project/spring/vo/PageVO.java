package project.spring.vo;

public class PageVO {
	//dao.getArticle에서 사용되는 값
	private int startRow;//현재 페이지 시작
	private int endRow;//끝 게시물 번호
	//기본값
	private int pageSize = 10;//보여줄 페이지 셋팅
	private int currPage;//현재 페이지 정보 셋팅
	private int count;//DB에 있는 게시글의 총개수를 담을 변수
	private int number;//게시판 상의 글번호 뿌려줄 변수
	//View Page에서 계산되는값
	private int pageCount = 0;
	private int pageBlock = 10;
	private int startPage = 0;
	private int endPage = 0;
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
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	@Override
	public String toString() {
		return "PageVO [startRow=" + startRow
				+ ", endRow=" + endRow
				+ ", pageSize=" + pageSize
				+ ", currPage=" + currPage
				+ ", count=" + count + ", number="
				+ number + ", pageCount="
				+ pageCount + ", pageBlock="
				+ pageBlock + ", startPage="
				+ startPage + ", endPage="
				+ endPage + "]";
	}
	public PageVO(int startRow, int endRow,
			int pageSize, int currPage, int count,
			int number, int pageCount,
			int pageBlock, int startPage,
			int endPage) {
		this.startRow = startRow;
		this.endRow = endRow;
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.count = count;
		this.number = number;
		this.pageCount = pageCount;
		this.pageBlock = pageBlock;
		this.startPage = startPage;
		this.endPage = endPage;
	}
}
