package project.spring.beans;

public class Pager {
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
	public Pager(){
	}
	public PageVO pager(String pageNum,int count) {
		
		//기본셋팅 로직
		currPage = Integer.parseInt(pageNum);
		startRow = (currPage-1)*pageSize +1;
		endRow = currPage*pageSize;
		//계산되는 로직
		number = count -(currPage-1)*pageSize;
		System.out.println("VOCOUNTCHECK="+count);
		if(count>0) {
			pageCount = (count/pageSize) +(count%pageSize == 0 ?0 :1);
		}
		startPage = ((currPage-1)/pageBlock)*pageBlock+1;
		endPage = startPage+pageBlock-1;
		if(endPage>pageCount) {
			endPage = pageCount;
		}
		PageVO pager = new PageVO(startRow,
				endRow, pageSize, currPage, count,
				number, pageCount, pageBlock,
				startPage, endPage);
		System.out.println("VOCHECK="+pager.getNumber());
		return pager;
	}
}
