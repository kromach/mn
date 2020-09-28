package project.spring.myAct.controller;

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
	public String index() {
		System.out.println("MyActIndex");
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