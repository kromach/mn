package project.spring.drink.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	public String indexInit(HttpServletRequest request, Model model) throws SQLException {
		
		String schDkBkind = null;
		String schDkSkind = null;
		String[] schDkAlcohol = null;
		String schDkCountry = null;
				
		if ((String)request.getParameter("isSearch") != null && ((String)request.getParameter("isSearch")).length() > 0) {
			schDkBkind = (String)request.getParameter("schDkBkind");
			schDkSkind = (String)request.getParameter("schDkSkind");
			schDkAlcohol = request.getParameterValues("schDkAlcohol");
			schDkCountry = (String)request.getParameter("schDkCountry");
				
			//System.out.println("schDkBkind : " + schDkBkind);
			//System.out.println("schDkSkind : " + schDkSkind);
	//		System.out.println("schDkAlcohol : " + schDkAlcohol);
			//System.out.println("schDkCountry : " + schDkCountry);
		}
		
		List<HashMap> bigCategoryList = drinkService.selectBigCategoryList();
		model.addAttribute("bigCategoryList", bigCategoryList);		
		
		return "drink/index.mn";
	}

	@RequestMapping("detail")
	public String detailInit(HttpServletRequest request, Model model) throws SQLException {
		
		String dkCode = (String)request.getParameter("dkCode");
		System.out.println(dkCode);
		
		DrinkVO drinkInfo = drinkService.selectDrinkServiceInfo(dkCode);
		drinkInfo.setDkContent();
		
		// 총 코멘트 수, 평가 평균 점수 
		HashMap commentStarInfo = drinkService.selectCommentStarServiceInfo(dkCode);
		
		String commentCount = "0";
		if(commentStarInfo != null) {
			commentCount = String.valueOf(commentStarInfo.get("cmCount"));
		}
		
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
	
	// 입력 페이지
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
	
	// 입력 처리
	@RequestMapping("insertPro")
	public String InsertProInit(DrinkVO drinkVo, MultipartHttpServletRequest request, HttpServletResponse response, Model model) throws SQLException, IOException {
		
		// 인증 글 여부 
		drinkVo.setDkApprove(0);
		HttpSession session =  request.getSession();
		if(session.getAttribute("userKind") != null) {
			
			// 세션으로 userKind 체크 후 admin 인 경우 승인여부 값을 1로 업데이트
			if (((String)session.getAttribute("userKind")).equals("admin")) {
				drinkVo.setDkApprove(1);
			}
		}
		
		if(session.getAttribute("memId") != null) { // session id
			drinkVo.setInsertId((String)session.getAttribute("memId"));
		}
		
		// 업로드 이미지명 집어넣기
		MultipartFile mf = null;
		mf = request.getFile("dkimage"); 
		drinkVo.setDkImg(mf.getOriginalFilename());

		// (1) 주류 정보 저장 (생성된 코드값 가져오기)
		String dkCode = drinkService.insertDrink(drinkVo);
		
		// (2) 태그 정보 입력 & 업데이트
		if (dkCode != null && drinkVo.getDkTags() != null && drinkVo.getDkTags().length() > 0 ) {
			HashMap tagInfo = new HashMap();
			tagInfo.put("dkCode", dkCode);
			tagInfo.put("dkTags", drinkVo.getDkTags());
			
			drinkService.updateDrinkTag(tagInfo);
		}
		
		// (2) 저장된 코드값으로 이미지 처리
		request.setAttribute("dkCode", dkCode);
		String imgPath = drinkService.insertDrinkImg(request);
		
		//System.out.println(selectDrinkInfo.getDkBkindValue());
		PrintWriter printWriter = null;
		
		// 인코딩
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");		

		printWriter = response.getWriter();

		// 업로드시 메시지 출력
		printWriter.println("<script type='text/javascript'>"
		     + "alert('주류 정보가 등록되었습니다. 관리자 확인 후에 사이트에 게재됩니다.')"
		     +"</script>");
		
		printWriter.flush();
		
		model.addAttribute("dkCode", dkCode);
		//request.setAttribute("dkCode", dkCode);

		return "drink/insertPro";
	}
	
	// AJAX - 대분류 선택시 해당하는 소분류 리스트 리턴
	@RequestMapping("selectSmallCategory")
	public void selectSmallCategory(@RequestParam String bigCategory, HttpServletResponse response) throws SQLException, IOException {
		
		List<HashMap> smallCategoryList = drinkService.selectSmallCategoryList(bigCategory);
		//System.out.println(smallCategoryList);
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(JsonUtil.ListToJson(smallCategoryList));
	}
	
	// AJAX - 대분류 선택시 해당하는 평가항목 명칭을 불러옴
	@RequestMapping("selectItemValues")
	public void selectItemValues(@RequestParam String bigCategory, HttpServletResponse response) throws SQLException, IOException {
		
		// HashMap ItemValuesInfo = drinkService.selectItemValuesInfo(bigCategory);
		List<String> ItemValuesList = drinkService.selectItemValuesList(bigCategory);
		//System.out.println(ItemValuesList);
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(JsonUtil.ListToJson(ItemValuesList));
	}	
}

