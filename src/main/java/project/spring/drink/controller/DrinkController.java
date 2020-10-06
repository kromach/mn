package project.spring.drink.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.spring.beans.JsonUtil;
import project.spring.drink.service.DrinkService;
import project.spring.drink.vo.CommentVO;
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
		
		return "drink/index.mn";
	}

	@RequestMapping("detail")
	public String detailInit(HttpServletRequest request, Model model) throws SQLException {
		
		String dkCode = (String)request.getParameter("dkCode");
		
		DrinkVO drinkInfo = drinkService.selectDrinkServiceInfo(dkCode);
		drinkInfo.setDkContent();
		
		// 총 코멘트 수, 평가 평균 점수 
		HashMap commentStarInfo = drinkService.selectCommentStarServiceInfo(dkCode);
		
		String commentCount = String.valueOf(commentStarInfo.get("cmCount"));
		
		List<CommentVO> commentList = null;
		if((Integer.parseInt(commentCount)) > 1) {
			commentList = drinkService.selectCommentServiceList(dkCode);
		}
		
		//System.out.println(commentList);
		
		// 주류별 태그 정보
		List<HashMap> tagCloudInfo = drinkService.selectTagCloudServiceInfo(dkCode);
		
		//System.out.println(tagCloudInfo);
		
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
		model.addAttribute("drinkInfo", drinkInfo);
		model.addAttribute("commentStarInfo", commentStarInfo);
		model.addAttribute("commentList", commentList);
		model.addAttribute("tagCloudInfo", tagCloudInfo);
//		
//		System.out.println(selectDrinkInfo.getDkName());
//		System.out.println(selectDrinkInfo.getDkBkindValue());
		
		return "drink/detail.mn";
	}
	
	@RequestMapping("insert")
	public String InsertInit(HttpServletRequest request, Model model) throws SQLException {
		
		List<HashMap> bigCategoryList = drinkService.selectBigCategoryList();
		
		//String dkCode = (String)request.getParameter("dkCode");
		
		model.addAttribute("bigCategoryList", bigCategoryList);
//		
//		System.out.println(selectDrinkInfo.getDkName());
//		System.out.println(selectDrinkInfo.getDkBkindValue());
		
		return "drink/insert.mn";
	}
		
	// AJAX - 대분류 선택시 해당하는 소분류 리스트 리턴
	@RequestMapping("selectSmallCategory")
	public void selectSmallCategory(@RequestParam String bigCategory, HttpServletResponse response) throws SQLException, IOException {
		
		List<HashMap> smallCategoryList = drinkService.selectSmallCategoryList(bigCategory);
		System.out.println(smallCategoryList);
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(JsonUtil.ListToJson(smallCategoryList));
	}
	
	// AJAX - 대분류 선택시 해당하는 평가항목 명칭을 불러옴
	@RequestMapping("selectItemValues")
	public void selectItemValues(@RequestParam String bigCategory, HttpServletResponse response) throws SQLException, IOException {
		
		HashMap ItemValuesInfo = drinkService.selectItemValuesInfo(bigCategory);
		System.out.println(ItemValuesInfo);
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(JsonUtil.HashMapToJson(ItemValuesInfo));
	}	
}

