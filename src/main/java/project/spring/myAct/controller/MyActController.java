package project.spring.myAct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/myAct")
public class MyActController {
	@RequestMapping
	public String index() {
		System.out.println("MyActIndex");
		return "/myAct/index.mn";
	}
}