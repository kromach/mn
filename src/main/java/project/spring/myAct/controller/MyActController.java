package project.spring.myAct.controller;

import java.util.ArrayList;
import java.util.List;

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
import project.spring.myAct.service.MyActService;
import project.spring.myAct.vo.TitleListDTO;

@Controller
@RequestMapping("/myAct")
public class MyActController {
	
	@Autowired
	MyActService myActService= null;
	
	@RequestMapping
	public String index(HttpServletRequest request, Model model) {
		System.out.println("MyActIndex Controller");
		int count = 0;				
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		List<ArticleDTO> myArticle = null;
		count = myActService.myArticleCount(memId);
		System.out.println("count : " + count);
		
		myArticle = myActService.getMyArticle(memId);
		System.out.println("size======"+myArticle.size());		
		model.addAttribute("count", new Integer(count));
		model.addAttribute("myArticle", myArticle);
		for(int i =0 ; i<myArticle.size();i++) {
			System.out.println("======================================++");
			myArticle.get(i).toString();
		}
		return "/myAct/index.mn";
	}
	
	@RequestMapping(value = "/attend", method = RequestMethod.GET)
	public String attend() {
		System.out.println("MyActAttend Controller");
		
		return "/myAct/attend.mn";
	}
	
	@RequestMapping(value = "/likeArticle")
	@ResponseBody
	public List likeArticle(String id, HttpServletRequest request, Model model) {
		System.out.println("MyActLikeArticle Controller");
		System.out.println("id : "+id);
		int count = 0;			
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		List likeArticle =null;
		
		likeArticle = myActService.myLikeArticle(memId);
		count = myActService.likeArticleCount(memId);
		System.out.println("count : " + count);
		System.out.println(likeArticle.toString());
		
		return likeArticle;
	}
	 
	@RequestMapping(value = "/likeDrink")
	@ResponseBody
	public List likeDrink(String pageNum, HttpServletRequest request) {
		System.out.println("MyActLikeDrink Controller");
		int count = 0;
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		List likeDrink = null;
		count = myActService.likeArticleCount(memId);
		System.out.println("count : " + count);
		
		return likeDrink;
	}
	
	@RequestMapping(value = "/getAllTitle")
	@ResponseBody
	public List allTitle() {
		System.out.println("GetAllTitle Controller");
		List getAllTitle = null;
		getAllTitle = myActService.getAllTitle();
		System.out.println(getAllTitle.toString());
		return getAllTitle;
	}
	
	@RequestMapping(value = "/myTitle")
	@ResponseBody
	public List title(String id, HttpServletRequest request) {
		System.out.println("Title Controller");
		System.out.println("id : "+id);
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");

		List myTitle = null;
		myTitle = myActService.getMyTitle(memId);
		System.out.println(myTitle.toString());
		
		return myTitle;
	}
	
	@RequestMapping(value = "/selectTitle")
	public int selectTitle(String id, HttpServletRequest request) {
		System.out.println("SelectTitle Controller");
		System.out.println("id : " + id);
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		//TitleListDTO updateTitle = myActService.updateTitle(); 
		
		int count =0;
		return count;
		
	}
}