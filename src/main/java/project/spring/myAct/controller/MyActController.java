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
	public Map likeDrink(String pageNum, HttpServletRequest request) {
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
	public Map allTitle(HttpServletRequest request) {
		System.out.println("GetAllTitle Controller");
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		
		List<TitleListDTO> getAllTitle = null;
		List<TitleListDTO> updateTitle = null;
		List myTitle = null;
		Map<String,List> map = new HashMap();
		
		getAllTitle = myActService.getAllTitle();
		updateTitle = myActService.updateTitle(memId); 
		myTitle = myActService.getMyTitle(memId);
		
		map.put("allTitle",getAllTitle);
		map.put("updateTitle", updateTitle);
		return map;
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
		System.out.println(myTitle.get(3));
		
		return myTitle;
	}
	

}