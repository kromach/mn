package project.spring.drink.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.spring.drink.service.DrinkService;

@Controller
@RequestMapping("/drink/")
public class DrinkController {
	
	private static final Logger logger = LoggerFactory.getLogger(DrinkController.class);
	
	@RequestMapping("index.do")
	public String index(HttpServletRequest request, Model model) {
		
	
		return "/drink/index.mn";
	}
//	
//	@RequestMapping("smallCategory.do")
//	public void smallCategory(@RequestParam String bigCategoryCode) throws Exception {
//		
//		List<HashMap> smallCategoryList = DrinkService.selectSmallCategoryList(bigCategoryCode);
//	}
//	
}
