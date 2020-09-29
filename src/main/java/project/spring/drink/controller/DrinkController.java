package project.spring.drink.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.spring.drink.dao.DrinkDAO;
import project.spring.drink.service.DrinkService;
import project.spring.drink.vo.DrinkVO;

@Controller
@RequestMapping("/drink/")
public class DrinkController {
	
	private static final Logger logger = LoggerFactory.getLogger(DrinkController.class);
	
	private DrinkService drinkService;
	
	@Autowired
	public DrinkController(DrinkService drinkService) {
		this.drinkService = drinkService;
	}
	
	@RequestMapping("index")
	public String indexInit(HttpServletRequest request, Model model) {
		
		String schDkBkind = null;
		String schDkSkind = null;
		String[] schDkAlcohol = null;
		String schDkCountry = null;
				
		if ((String)request.getParameter("isSearch") != null && ((String)request.getParameter("isSearch")).length() > 0) {
			schDkBkind = (String)request.getParameter("schDkBkind");
			schDkSkind = (String)request.getParameter("schDkSkind");
			schDkAlcohol = request.getParameterValues("schDkAlcohol");
			schDkCountry = (String)request.getParameter("schDkCountry");
				
			System.out.println("schDkBkind : " + schDkBkind);
			System.out.println("schDkSkind : " + schDkSkind);
	//		System.out.println("schDkAlcohol : " + schDkAlcohol);
			System.out.println("schDkCountry : " + schDkCountry);
		}
		
		return "/drink/index.mn";
	}
	
	@RequestMapping("detail")
	public String detailInit(HttpServletRequest request, Model model) throws SQLException {
		
		String dkCode = (String)request.getParameter("dkCode");
		
		DrinkVO selectDrinkInfo = drinkService.selectDrinkServiceInfo(dkCode);
		
		// request에 담긴 검색 결과 뽑아내기 
		String schDkBkind = null;
		String schDkSkind = null;
		String[] schDkAlcohol = null;
		String schDkCountry = null;
				
		if ((String)request.getParameter("isSearch") != null && ((String)request.getParameter("isSearch")).length() > 0) {
			schDkBkind = (String)request.getParameter("schDkBkind");
			schDkSkind = (String)request.getParameter("schDkSkind");
			schDkAlcohol = request.getParameterValues("schDkAlcohol");
			schDkCountry = (String)request.getParameter("schDkCountry");
			
//			System.out.println("dkCode : " + dkCode);
//			System.out.println("schDkBkind : " + schDkBkind);
//			System.out.println("schDkSkind : " + schDkSkind);
//			if (schDkAlcohol != null && schDkAlcohol.length > 0) {
//				for(String s : schDkAlcohol) {
//					System.out.println("schDkAlcohol : " + s);
//				}
//			}
//			System.out.println("schDkCountry : " + schDkCountry);
		}
		
		model.addAttribute("schDkBkind", schDkBkind);
		model.addAttribute("schDkSkind", schDkSkind);
		model.addAttribute("schDkAlcohol", schDkAlcohol);
		model.addAttribute("schDkCountry", schDkCountry);
		model.addAttribute("selectDrinkInfo", selectDrinkInfo);
		
		//System.out.println(selectDrinkInfo.getDkName());
		//System.out.println(selectDrinkInfo.getDkBkindValue());
		
		return "/drink/detail.mn";
	}
//	
//	@RequestMapping("smallCategory.do")
//	public void smallCategory(@RequestParam String bigCategoryCode) throws Exception {
//		
//		List<HashMap> smallCategoryList = DrinkService.selectSmallCategoryList(bigCategoryCode);
//	}
//	
}
