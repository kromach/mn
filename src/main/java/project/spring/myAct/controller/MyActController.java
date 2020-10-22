package project.spring.myAct.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.spring.article.vo.ArticleDTO;
import project.spring.beans.PageVO;
import project.spring.beans.Pager;
import project.spring.myAct.service.MyActService;
import project.spring.myAct.vo.TitleListDTO;

@Controller
@RequestMapping("/myAct")
public class MyActController {
	
	@Autowired
	MyActService myActService= null;
	
	@RequestMapping
	public String indexSs(HttpServletRequest request, Model model) {
		System.out.println("MyActIndex Controller");
		String pageNum;
		if(request.getParameter("pageNum") != null) {
			pageNum = request.getParameter("pageNum");
		}else{
			pageNum = "1";
		}
				
		int count = 0;
		
		Pager pager = new Pager();
		PageVO pageVO = null;
		int number = 0;			
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		
		//myArticle = myActService.getMyArticle(memId);
		//System.out.println("size======"+myArticle.size());	
		
		count = myActService.myArticleCount(memId);
		System.out.println("count : " + count);
		
		List<ArticleDTO> myArticle = null;
		if(count > 0) {
			pageVO = pager.pager(pageNum, count);
			myArticle = myActService.getMyArticle(memId, pageVO.getStartRow(), pageVO.getEndRow());
			System.out.println("myArticle" + myArticle.toString());
		}
		number = count - (pageVO.getCurrPage() -1) * pageVO.getPageSize();
		
		model.addAttribute("myArticle", myArticle);
		model.addAttribute("count", new Integer(count));
		model.addAttribute("number", new Integer(number));
		model.addAttribute("pageNum", new Integer(pageNum));
		model.addAttribute("pageVO", pageVO);
		
		for(int i =0 ; i<myArticle.size();i++) {
			System.out.println("======================================++");
			myArticle.get(i).toString();
		}
		return "/myAct/index.mn";
	}
	
	@RequestMapping(value = "/index")
	public String index2(HttpServletRequest request, Model model) {
		
		String pageNum;
		if(request.getParameter("pageNum") != null) {
			pageNum = request.getParameter("pageNum");
		}else{
			pageNum = "1";
		}
				
		int count = 0;
		
		Pager pager = new Pager();
		PageVO pageVO = null;
		int number = 0;			
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		
		//myArticle = myActService.getMyArticle(memId);
		//System.out.println("size======"+myArticle.size());	
		
		count = myActService.myArticleCount(memId);
		System.out.println("count : " + count);
		
		List<ArticleDTO> myArticle = null;
		if(count > 0) {
			pageVO = pager.pager(pageNum, count);
			myArticle = myActService.getMyArticle(memId, pageVO.getStartRow(), pageVO.getEndRow());
			System.out.println("myArticle" + myArticle.toString());
		}
		number = count - (pageVO.getCurrPage() -1) * pageVO.getPageSize();
		
		model.addAttribute("myArticle", myArticle);
		model.addAttribute("count", new Integer(count));
		model.addAttribute("number", new Integer(number));
		model.addAttribute("pageNum", new Integer(pageNum));
		model.addAttribute("pageVO", pageVO);
		
		for(int i =0 ; i<myArticle.size();i++) {
			System.out.println("======================================++");
			myArticle.get(i).toString();
		}
		
		return "/myAct/index.mn";
	}
	
	@RequestMapping(value = "/attend", method = RequestMethod.GET)
	public String attendSs(HttpServletRequest request) {
		System.out.println("MyActAttend Controller");
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		int result = myActService.attendent(memId);
		System.out.println("result : " + result);
		
		return "/myAct/attend.mn";
	}
	
	@RequestMapping(value = "/likeArticle")
	@ResponseBody
	public Map likeArticleSs(String id, HttpServletRequest request) {
		System.out.println("MyActLikeArticle Controller");
		System.out.println("id : "+id);
		
		String pageNum;
		if(request.getParameter("pageNum") != null) {
			pageNum = request.getParameter("pageNum");
		}else{
			pageNum = "1";
		}
				
		Pager pager = new Pager();
		PageVO pageVO = null;
		int number = 0;			
		int count = 0;			
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		List<ArticleDTO> likeArticle =null;
		
		
		count = myActService.likeArticleCount(memId);
		System.out.println("count : " + count);
		
		if(count > 0) {
			pageVO = pager.pager(pageNum, count);
			likeArticle = myActService.myLikeArticle(memId, pageVO.getStartRow(), pageVO.getEndRow());
			System.out.println("likeArticle" + likeArticle.toString());
		}
		System.out.println(count);
		number = count - (pageVO.getCurrPage() -1) * pageVO.getPageSize();
		
		Map map = new HashMap();
		map.put("likeArticle", likeArticle);
		map.put("pageVO", pageVO);
		map.put("count", new Integer(count));
		map.put("pageNum", new Integer(pageNum));
		map.put("number", new Integer(number));
		
		return map;
	}
	 
	@RequestMapping(value = "/likeDrink")
	@ResponseBody
	public Map likeDrinkSs(String pageNum, HttpServletRequest request) {
		System.out.println("MyActLikeDrink Controller");
		
		Map<String,List> map = new HashMap();
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		List likeDrink = null;
		List likeProduct = null;
		likeDrink = myActService.myLikeDrink(memId);
		likeProduct = myActService.myLikeProduct(memId);
		
		map.put("likeDrink", likeDrink);
		map.put("likeProduct", likeProduct);
		
		System.out.println(likeDrink.toString());
		System.out.println(likeProduct.toString());
		
		return map;
	}
	
	@RequestMapping(value = "/getAllTitle")
	@ResponseBody
	public Map allTitleSs(HttpServletRequest request) {
		System.out.println("GetAllTitle Controller");
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		
		List<TitleListDTO> getAllTitle = null;
		List<TitleListDTO> updateTitle = null;
		//List myTitle = null;
		Map<String,List> map = new HashMap();
		
		//모든 Title 뿌려주고
		//getAllTitle = myActService.getAllTitle();
		getAllTitle = myActService.getMyTitle(memId); // 모든 타이틀 + 획득한 칭호 + 선택한 칭호
		// 그중 내가 획득한 칭호는 색을 바꿔줌
		updateTitle = myActService.updateTitle(memId);
		
		// SELECT * FROM MY_TITLE mt , TITLE_LIST tl WHERE mt.TITLE_INDEX = tl.TITLE_IDX AND mt.ID='admin';
		//사용하지 않음
		//myTitle = myActService.getMyTitle(memId);
		
		map.put("allTitle",getAllTitle);
		map.put("updateTitle", updateTitle);
		return map;
	}
	
	//사용하지 않음
	@RequestMapping(value = "/myTitle")
	@ResponseBody
	public List titleSs(String id, HttpServletRequest request) {
		System.out.println("Title Controller");
		System.out.println("id : "+id);
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");

		List myTitle = null;
		myTitle = myActService.getMyTitle(memId);
		System.out.println(myTitle.toString());
		System.out.println(myTitle.get(3));
		
		return myTitle;
	}
	
	
	@RequestMapping(value ="/chooseTitle")
	@ResponseBody
	public int chooseTitle(@RequestParam(name = "titleIdx") int idx,HttpServletRequest request) {
		System.out.println("왔냐?");
		System.out.println("idx : " + idx);
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		
		int chooseTitle =0;
		chooseTitle = myActService.choose(idx, memId);
		
		return chooseTitle;
	} 
	
	
	
	
	
	
	
}