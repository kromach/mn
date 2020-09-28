package project.spring.myAct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/myAct")
public class MyActController {

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String index() {
		System.out.println("MyActIndex");
		return "/myAct/index.mn";
	}
	
	@RequestMapping(value = "/likeDrink", method = RequestMethod.POST)
	public String likeDrink() {
		return "/myAct/likeDrink.mn";
	}
	
	@RequestMapping(value = "/likeArticle", method = RequestMethod.POST)
	public String likeArticle() {
		return "/myAct/likeArticle.mn";
	}
	
	@RequestMapping(value = "/title", method = RequestMethod.POST)
	public String title() {
		return "/myAct/title.mn";
	}
	
	@RequestMapping(value = "/attend", method = RequestMethod.POST)
	public String attend() {
		return "/myAct/attend.mn";
	}	

}
