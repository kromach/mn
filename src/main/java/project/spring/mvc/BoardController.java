package project.spring.mvc;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/board/")
public class BoardController {
	
//	@Autowired
//	private BoardDAOimpl boardDAO = null;
//	@Autowired
//	private Pager pager = null;
	
	@RequestMapping("list.git")
	public String list(
			@RequestParam(required = false) String pageNum,
			Model model) throws SQLException {
// 		String searchSelect = request.getParameter("searchSelect");
//		String search = request.getParameter("search");
//		System.out.print("SearchData : [");
//		System.out.print(search+"\t");
//		System.out.print(searchSelect+"]\n");
		
		//페이지 정보 담기
		if(pageNum == null){
			pageNum = "1";
		}
//		List<BoardDTO> articleList = null;
//		int count = boardDAO.getArticleCount();
//		PageVO pageVO = pager.pager(pageNum,count);
//		if(count>0){
//			articleList=boardDAO.getArticle(pageVO.getStartRow(),pageVO.getEndRow());
//		}
		
//		//검색일경우
//		if(search!=null){
//			count = dao.getArticleCount(searchSelect,search);
//			System.out.println("[Selected Count : "+count + "]");
//			if(count>0){
//				articleList=dao.getArticle(startRow,endRow,searchSelect,search);
//			}
//		}
//		model.addAttribute("searchSelect", searchSelect);
//		model.addAttribute("search", search);
		
		model.addAttribute("pageNum", pageNum);
//		model.addAttribute("count", new Integer(count));
//		model.addAttribute("pageVO", pageVO);
//		model.addAttribute("articleList", articleList);
		return "board/list";
	}
}
