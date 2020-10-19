package project.spring.drink.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String indexInit(Model model) throws SQLException {
		
		// 대분류 가져오기
		List<HashMap> bigCategoryList = drinkService.selectBigCategoryList();
		model.addAttribute("bigCategoryList", bigCategoryList);		
		
		return "drink/index.mn";
	}
	
	//AJAX 주류 정보 가져오기 (검색어, 페이지번호)
	@RequestMapping("drinkList")
	public void drinkList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String schDkBkind = null;
		String schDkSkind = null;
		String schDkVal = null;
		String[] schDkAlcohol = null;
		String schDkAlcoholMin = null;
		String schDkAlcoholMax = null;
		String schDkCountry = null;
		int pageNum = 1;
				
		if ((String)request.getParameter("isSearch") != null && ((String)request.getParameter("isSearch")).length() > 0) {
			schDkBkind = (String)request.getParameter("schDkBkind");
			schDkSkind = (String)request.getParameter("schDkSkind");
			schDkVal = (String)request.getParameter("schDkVal");
			System.out.println(request.getParameter("schDkAlcohol"));
			schDkAlcohol = request.getParameter("schDkAlcohol").split(";");
			
			schDkAlcoholMin = schDkAlcohol[0];
			schDkAlcoholMax = schDkAlcohol[1];
			schDkCountry = (String)request.getParameter("schDkCountry");
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			System.out.println("schDkBkind : " + schDkBkind);
			//System.out.println("schDkSkind : " + schDkSkind);
			System.out.println("schDkAlcohol : " + schDkAlcohol);
			System.out.println("schDkAlcoholmin : " + schDkAlcoholMin);
			System.out.println("schDkAlcoholmax : " + schDkAlcoholMax);
			//System.out.println("schDkCountry : " + schDkCountry);
		}
		
		HttpSession session =  request.getSession();
		
		String memId = null;
		String memKind = null;

		if(session.getAttribute("memId") != null) { // session id
			memId = (String)session.getAttribute("memId");
			memKind = (String)session.getAttribute("memKind");
		}
		System.out.println("memId");
		HashMap<String, Object> schMap = new HashMap<String, Object>();
		schMap.put("schDkBkind", schDkBkind);
		schMap.put("schDkSkind", schDkSkind);
		schMap.put("schDkVal", schDkVal);
		schMap.put("schDkAlcoholMin", schDkAlcoholMin);
		schMap.put("schDkAlcoholMax", schDkAlcoholMax);
		schMap.put("schDkCountry", schDkCountry);
		schMap.put("memId", memId);
		schMap.put("memKind", memKind);
		schMap.put("pageNum", pageNum);
		
		//model.addAttribute("schMap", schMap);
		//검색 정보
		/*
		model.addAttribute("schDkBkind", schDkBkind);
		model.addAttribute("schDkSkind", schDkSkind);
		model.addAttribute("schDkVal", schDkVal);
		model.addAttribute("schDkAlcohol", schDkAlcohol);
		model.addAttribute("schDkCountry", schDkCountry);
		*/
		
		// 검색결과
		List<DrinkVO> drinkList = drinkService.selectDrinkServiceList(schMap);

		response.setCharacterEncoding("utf-8");
		response.getWriter().write(JsonUtil.ListToJson(drinkList));
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
		
		// 주류 정보 좋아요 정보 
		HttpSession session =  request.getSession();
		
		String memId = null;
		String drinkLikeInfo = null;
		if(session.getAttribute("memId") != null) { // session id
			memId = (String)session.getAttribute("memId");
			
			// 아이디의 현재 좋아요 여부 가져오기 
			HashMap drinkLikeMap = new HashMap();
			
			drinkLikeMap.put("dkCode", dkCode);
			drinkLikeMap.put("memId", memId);
			
			drinkLikeInfo = drinkService.selectDrinkLikeInfo(drinkLikeMap);
		}
		
		//System.out.println(drinkLikeInfo);
		
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
		model.addAttribute("drinkLikeInfo", drinkLikeInfo);
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
	
	@RequestMapping("modify")
	public String modifyInit(HttpServletRequest request, Model model) throws SQLException {
		
		String dkCode = (String)request.getParameter("dkCode");
		//System.out.println(dkCode);
		
		DrinkVO drinkInfo = drinkService.selectDrinkServiceInfo(dkCode);
		drinkInfo.setDkContent();
		
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
		}
		
		model.addAttribute("schDkBkind", schDkBkind);
		model.addAttribute("schDkSkind", schDkSkind);
		model.addAttribute("schDkAlcohol", schDkAlcohol);
		model.addAttribute("schDkCountry", schDkCountry);
		model.addAttribute("drinkInfo", drinkInfo);
		
		List<HashMap> bigCategoryList = drinkService.selectBigCategoryList();
		model.addAttribute("bigCategoryList", bigCategoryList);
		
		return "drink/modify.mn";
	}
	
	// 변경 처리
	@RequestMapping("modifyPro")
	public String ModifyProInit(DrinkVO drinkVo, MultipartHttpServletRequest request, HttpServletResponse response, Model model) throws SQLException, IOException {
		
		HttpSession session =  request.getSession();
		if(session.getAttribute("memId") != null) { // session id
			drinkVo.setInsertId((String)session.getAttribute("memId"));
		}
		
		String dkCode = drinkVo.getDkCode();
		
		// 대표 이미지 변경시 기존 파일 삭제 및 신규 업로드 처리
		MultipartFile mf = null;
		try {
			mf = request.getFile("dkimage"); 
			System.out.println(mf.getOriginalFilename());
			if(mf != null && mf.getOriginalFilename() != null) {
				String dkImg = drinkVo.getDkImg();
				
				// 파일의 확장자만 추출
				String ext = dkImg.substring(dkImg.lastIndexOf("."));
				
				String path = request.getRealPath("resources/img/drink") + File.separator;
//				System.out.println("req :" + request.getRealPath("resources/img/upload"));
				// System.out.println(path + newName);
				File file = new File(path + dkCode + ext);

				if(file.exists()) { 
					if(file.delete()) { 
						System.out.println("파일삭제 성공"); 
					} else { System.out.println("파일삭제 실패"); } 
				} else { 
					System.out.println("파일이 존재하지 않습니다."); 
				}

				// 저장된 코드값으로 이미지 처리
				request.setAttribute("dkCode", dkCode);
				String imgPath = drinkService.insertDrinkImg(request);
				System.out.println("컨트롤러 이미지 경로 : " + imgPath);
				
				drinkVo.setDkImg(imgPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// (1) 주류 정보 저장 (생성된 코드값 가져오기)
		String result = drinkService.modifyDrink(drinkVo);
		
		// (2) 태그 정보 입력 & 업데이트
		if (dkCode != null && drinkVo.getDkTags() != null && drinkVo.getDkTags().length() > 0 ) {
			HashMap tagInfo = new HashMap();
			tagInfo.put("dkCode", dkCode);
			tagInfo.put("dkTags", drinkVo.getDkTags());
			
			drinkService.updateDrinkTag(tagInfo);
		}
		
		//System.out.println(selectDrinkInfo.getDkBkindValue());
		PrintWriter printWriter = null;
		
		// 인코딩
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");		

		printWriter = response.getWriter();

		// 업로드시 메시지 출력
		printWriter.println("<script type='text/javascript'>"
		     + "alert('"+ result +"')"
		     +"</script>");
		
		printWriter.flush();
		
		model.addAttribute("dkCode", dkCode);
		//request.setAttribute("dkCode", dkCode);

		return "drink/insertPro";
	}
	
	// AJAX - 주류 정보 좋아요 / 좋아요 취소
	@RequestMapping("like")
	@ResponseBody
	public String drinkLikeSs(@RequestParam String dkCode, @RequestParam String memId) throws SQLException, IOException {
		
		HashMap drinkLikeMap = new HashMap();
		
		drinkLikeMap.put("dkCode", dkCode);
		drinkLikeMap.put("memId", memId);
		
		//model.addAttribute("drinkLikeInfo", drinkLikeInfo);		
		
		String drinkLikeInfo =  drinkService.updateDrinkLikeInfo(drinkLikeMap);
		
		return drinkLikeInfo;
	}

}

