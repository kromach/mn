package project.spring.myAct.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.spring.myAct.service.MyActService;

@Controller
@RequestMapping("/myAct")
public class MyActController {
	
	@Autowired
	MyActService myActService= null;
	
	@RequestMapping
	public String index(String pageNum, HttpSession session) {
		System.out.println("MyActIndex");
		//String memId = (String)session.getAttribute("memId");
		
		if(pageNum == null) {
			pageNum = "1";
		}
		int pageSize=10;
		int currPage = Integer.parseInt(pageNum);	
		int startRow = (currPage -1) * pageSize + 1;
		int endRow = currPage * pageSize;		
		int count = 0;			
		int number = 0;	
		
		String memId = "abc";
		count = myActService.myArticleCount(memId);
		System.out.println(count);
		
		return "/myAct/index.mn";
	}
	
	@RequestMapping(value = "/attend", method = RequestMethod.GET)
	public String attend() {
		return "/myAct/attend.mn";
	}
	
	@RequestMapping(value = "/likeArticle", method = RequestMethod.GET)
	public String likeArticle() {
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