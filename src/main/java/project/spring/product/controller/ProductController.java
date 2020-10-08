package project.spring.product.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import project.spring.member.service.MemberServiceImpl;
import project.spring.member.vo.MemberDTO;
import project.spring.product.service.ProductService;
import project.spring.product.vo.OrderVo;
import project.spring.product.vo.ProductVo;

@Controller
@EnableWebMvc
@RequestMapping("/product/")
public class ProductController {
	
	@Autowired
	private ProductService productservice = null;
	@Autowired
	private MemberServiceImpl memberservice = null;
	
	
	@RequestMapping("productlist")
		public String productList(Model model) throws SQLException {
		List productlist =null;
		int count = 0;
		
		count= productservice.getproductcount();
		if(count>0) {
			productlist = productservice.getproduct();
		}
		System.out.println(productlist);
		
		model.addAttribute("productlist", productlist);
		model.addAttribute("count", count);
		return "product/productList.mn";
	}
	
	
	
	@RequestMapping("productdetail")
		public String productdetail	(HttpServletRequest request, Model model) throws SQLException {
		String prcode = request.getParameter("prcode");
		
		ProductVo info =productservice.getproductinfo(prcode);
		model.addAttribute("info", info);
		System.out.println(info);
		
		// 총 코멘트 수 / 좋아요 수 
		HashMap commmetStarinfo = null;
	
		
		return "product/productdetail.mn";
	}
	
	@RequestMapping("myorderlist")
	public String myorderlistSs (Model model, HttpSession session) throws SQLException {
	List myorderlist = null;
	int myordercount = 0;
	
	String id = (String)session.getAttribute("memId");
	myordercount = productservice.myordercount(id);
	
	if(myordercount>0) {
		myorderlist = productservice.myorderlist(id);
	}
	System.out.println(myorderlist);
	model.addAttribute("myordercount",myordercount);
	model.addAttribute("myorderlist",myorderlist);
	
	
	return "product/myorderlist.mn";
	}
	
	@RequestMapping("productorder")
	public String productorderSs (HttpServletRequest request, HttpSession session, Model model )  throws SQLException{
		
		String amount = request.getParameter("amount");
		String prcode = request.getParameter("prcode");
		String id = (String)session.getAttribute("memId");
		
		
		
		
		
		MemberDTO meminfo = memberservice.readItem();
		ProductVo info =productservice.getproductinfo(prcode);
		model.addAttribute("info", info);
		model.addAttribute("amount", amount);
		model.addAttribute("meminfo", meminfo);
		
		return "product/productorder.mn";
	}
	
	@RequestMapping("myorderdetail")
	public String myorderdetail(HttpServletRequest request, Model model) throws SQLException{
		String orcode = request.getParameter("orcode");
		System.out.println(orcode);
		
		
		return "product/myorderdetail.mn";
	}
	
	@RequestMapping("insertOrder")
	public String insertOrder(Model model, OrderVo ordervo) throws SQLException{
		System.out.println(ordervo.getReceiverTel());
		
		String[] receiverTels = ordervo.getReceiverTel().split(",");
		String receiverTel = "";
		for(String receiverTel_ : receiverTels) {
			receiverTel += receiverTel_ ;
		}
		
		//ordervo.setReceiverTel(receiverTel);
		
		productservice.insertorderinfo(ordervo);
		
		
		List productlist =null;
		int count = 0;
		
		count= productservice.getproductcount();
		if(count>0) {
			productlist = productservice.getproduct();
		}
		System.out.println(productlist);
		
		model.addAttribute("productlist", productlist);
		model.addAttribute("count", count);
		
		
		
		return "product/productList.mn";
	}

}
