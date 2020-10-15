package project.spring.sales.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import project.spring.beans.PageVO;
import project.spring.beans.Pager;
import project.spring.sales.service.SalesService;

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
		
		return "sales/insert.mn";
	}
	
	@RequestMapping(value ="/insert")
	public List productDetail(String pageNum, Model model) {
		
		List detail =salesService.productDetail(pageNum);
		System.out.println(detail.toString());
		
		return detail;
	}
	
}