package project.spring.beans;
import project.spring.vo.PageVO;
public class Pager {
	private int pageSize = 10;//보여줄 페이지 셋팅
	private int currPage;//현재 페이지 정보 셋팅
	private int startRow;//현재 페이지 시작
	private int endRow;//끝 게시물 번호
	private int count;//DB에 있는 게시글의 총개수를 담을 변수
	private int number;//게시판 상의 글번호 뿌려줄 변수
	public Pager(){
	}
	public PageVO Pager(String pageNum) {
		currPage = Integer.parseInt(pageNum);
		this.currPage = currPage;
		startRow = (currPage-1)*pageSize +1;
		endRow = currPage*pageSize;
		
		PageVO pager = new PageVO(pageSize,
				currPage, startRow, endRow, count,
				number);
		return pager;
	}
}
