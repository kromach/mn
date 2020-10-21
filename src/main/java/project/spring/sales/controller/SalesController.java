package project.spring.sales.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.spring.beans.JsonUtil;
import project.spring.beans.PageVO;
import project.spring.beans.Pager;
import project.spring.drink.vo.DrinkVO;
import project.spring.product.vo.ProductVo;
import project.spring.sales.service.SalesService;
import project.spring.sales.vo.ProductInfoDTO;

@Controller
@RequestMapping("/sales")
public class SalesController {

	@Autowired
	
	SalesService salesService = null;
	
	@RequestMapping(value ="/index")
	public String indexSs(HttpServletRequest request, String pageNum, Model model) {
		System.out.println("SalesIndex Controller");
		if(pageNum == null) {
			pageNum = "1";
		}
				
		int count = 0;
		
		Pager pager = new Pager();
		PageVO pageVO = null;
		int number = 0;
		
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		System.out.println("Session : " + memId);
		
		String userKindCh = null;
		userKindCh = salesService.userIdCh(memId);
		System.out.println("판매자니 ? "+userKindCh);
		if(!userKindCh.equals("sales")) {
			System.out.println("판매자가 아니어서 리턴하겠습니다");
			return "redirect:/";
		}
		
		count = salesService.salesCount(memId);
		System.out.println("count : " + count);
		
		List salesList = null;
		
		if(count > 0) {
			pageVO = pager.pager(pageNum, count);
			salesList = salesService.productorList(memId, pageVO.getStartRow(), pageVO.getEndRow());
			System.out.println("salesList" + salesList.toString());
		}
		number = count - (pageVO.getCurrPage() -1) * pageVO.getPageSize();
		
		model.addAttribute("number", new Integer(number));
		model.addAttribute("pageNum", new Integer(pageNum));
		model.addAttribute("count", new Integer(count));
		model.addAttribute("salesList", salesList);
		model.addAttribute("pageVO", pageVO);
		
		
		return "sales/index.mn";
	}
	
	@RequestMapping(value ="/insert")
	public String insertProduct(String pageNum, Model model) {
		model.addAttribute("pageNum", pageNum);
		
		List secondCategory = salesService.getCategory();
		model.addAttribute("secondCategory", secondCategory);
		
		return "sales/insert.mn";
	}
	
	@RequestMapping(value = "/insertPro")
	public String insertProSs(ProductVo productDTO, MultipartHttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		HttpSession session =  request.getSession();
		if(session.getAttribute("memId") != null) { // session id
			productDTO.setInsertId((String)session.getAttribute("memId"));
		}
				
			// (1) 상품 코드 생성
			String prCode = salesService.makeprCode(productDTO);
			
			// (2) 저장된 코드값으로 이미지 처리
			request.setAttribute("prCode", prCode);
			String imgPath = salesService.insertProductImg(request);
			System.out.println("이미지 경로 :" +imgPath);
			String add = "\\resources\\";
			String[] imgPath_ = imgPath.split("\\\\resources\\\\");
			System.out.println(imgPath.length());
			if(imgPath_.length==2) {
				imgPath = add.concat(imgPath_[1]);
			}
			System.out.println("IMGPATH====="+imgPath);
			
			// 업로드 이미지명 집어넣기
			
			// (3) 다른것들 insert
			productDTO.setInsertId(session.getAttribute("memId").toString());
			productDTO.setPrImg(imgPath);
						
			System.out.println(productDTO);
			int count = salesService.insertProduct(productDTO);
			
			//System.out.println(selectDrinkInfo.getDkBkindValue());
			PrintWriter printWriter = null;
			// 인코딩
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");		
			printWriter = response.getWriter();
			// 업로드시 메시지 출력
			printWriter.println("<script type='text/javascript'>"
			     + "alert('상품 정보가 등록되었습니다.')"
			     +"</script>");
			printWriter.flush();
			model.addAttribute("prCode", prCode);
			return "/sales/insertPro";
	}
	
	@RequestMapping(value = "/modifyForm")
	public String modifyFormSs(@RequestParam(name="prcode") String prCode,HttpServletRequest request, Model model)throws IOException {
		
		System.out.println("sales modifyForm controller");
		System.out.println("prCode" + prCode);
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");

		//prCode로 해당 detail 가져오기
		// 해당 판매자가 맞는지 체크 PRCODE의 insert_id와 session값이 일치하지 않으면 main으로 리턴
		ProductVo dto = salesService.getDatail(prCode);
		System.out.println("alcohol =============" +dto.getPrAlcohol());
		System.out.println(dto.getInsertId()+" =====vs===== "+ memId);
		
		if(!(dto.getInsertId().equals(memId))) {
			return "redirect:/";
		}
		
		List secondCategory = salesService.getCategory();
		model.addAttribute("secondCategory", secondCategory);
		model.addAttribute("detail", dto);
		
		return "/sales/modifyForm.mn";
	}

	@RequestMapping(value = "/modifyPro")
	public String modifyProSs(ProductVo dto, MultipartHttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		HttpSession session =  request.getSession();
		
		String prcode = request.getParameter("prcode");
		dto.setPrCode(prcode);
		String prCode = dto.getPrCode();
		System.out.println("code 제대로 왔냐???" +dto.getPrCode());
		System.out.println("oldImd" + request.getParameter("oldImg"));
		System.out.println("새 이미지" + request.getFile("primage").getOriginalFilename());
		// 이미지 미변경시 
		if(request.getFile("primage").getOriginalFilename().equals("null")|| request.getFile("primage").getOriginalFilename().equals("")) {
			String oldImg = request.getParameter("oldImg");
			dto.setPrImg(oldImg);
		}else {
			// 이미지 변경시
			System.out.println("집에가?");
			// (2) 저장된 코드값으로 이미지 처리
			request.setAttribute("prCode", prCode);
			String imgPath = salesService.insertProductImg(request);
			System.out.println("이미지 경로 :" +imgPath);
			String add = "\\resources\\";
			String[] imgPath_ = imgPath.split("\\\\resources\\\\");
			System.out.println(imgPath.length());
			if(imgPath_.length==2) {
				imgPath = add.concat(imgPath_[1]);
			}
			System.out.println("IMGPATH====="+imgPath);
			dto.setPrImg(imgPath);
		}
		// 업로드 이미지명 집어넣기
			
		// (3) 다른것들 modify
		dto.setInsertId(session.getAttribute("memId").toString());
		System.out.println(dto);
		int count = salesService.updateProduct(dto);
		System.out.println("수정되었음 : " +count);
			
		//System.out.println(selectDrinkInfo.getDkBkindValue());
		PrintWriter printWriter = null;
		// 인코딩
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");		
		printWriter = response.getWriter();
		// 업로드시 메시지 출력
		printWriter.println("<script type='text/javascript'>"
			     + "alert('상품 정보가 수정되었습니다.')"
			     +"</script>");
			printWriter.flush();
			model.addAttribute("prCode", prCode);
		
		return "/sales/modifyPro.mn";
	}

	
}