package project.spring.myAct.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.spring.myAct.service.MyActService;

@Controller
@RequestMapping("/myAct")
public class MyActController {
	
	@Autowired
	MyActService myActService= null;
	
	@RequestMapping
	public String index(String pageNum, HttpServletRequest request, Model model) {
		System.out.println("MyActIndex Controller");
		int count = 0;				
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		List myArticle = null;
		count = myActService.myArticleCount(memId);
		System.out.println("count : " + count);
		
		model.addAttribute("count", new Integer(count));
		model.addAttribute("myArticle", myArticle);
		
		return "/myAct/index.mn";
	}
	
	
	
	
	@RequestMapping(value = "/attend", method = RequestMethod.GET)
	public String attend() {
		System.out.println("MyActAttend Controller");
		
		return "/myAct/attend.mn";
	}
	
	@RequestMapping(value = "/likeArticle", method = RequestMethod.GET)
	public String likeArticle(String pageNum, HttpServletRequest request, Model model) {
		System.out.println("MyActLikeArticle Controller");
		int count = 0;			
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		List likeArticle = null;
		count = myActService.likeArticleCount(memId);
		System.out.println("count : " + count);
		
		model.addAttribute("count", new Integer(count));
		//model.addAttribute("myLikeArticle", myLikeArticle);
		
		return "/myAct/likeArticle.mn";
	}
	
	@RequestMapping(value = "/likeDrink", method = RequestMethod.GET)
	public String likeDrink() {
		return "/myAct/likeDrink.mn";
	}
	@RequestMapping(value = "/title", method = RequestMethod.GET)
	public String title() {
		return "/myAct/title.mn";
	}
	
}