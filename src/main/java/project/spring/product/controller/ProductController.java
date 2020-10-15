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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import project.spring.beans.PageVO;
import project.spring.beans.Pager;
import project.spring.member.service.MemberServiceImpl;
import project.spring.member.vo.MemberDTO;
import project.spring.product.service.ProductService;
import project.spring.product.service.ProductServiceImpl;
import project.spring.product.vo.OrderVo;
import project.spring.product.vo.ProductVo;

@Controller
@EnableWebMvc
@RequestMapping("/product/")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productservice = null;
	@Autowired
	private MemberServiceImpl memberservice = null;
	
	
	@RequestMapping("productlist")
		public String productList(Model model ,HttpServletRequest request) throws SQLException {
		List productlist =null;
		int count = 0;
		HashMap map = null;
		
		// 검색 기능
		if(request.getParameter("isSearch")!=null && request.getParameter("isSearch").equals("true")) {
			map = new HashMap();
			
			if(!request.getParameter("prPrice").equals("0원 - 0원") && request.getParameter("prPrice")!=null) {
				String prPrice = request.getParameter("prPrice");
				String price[] = prPrice.split("원");
				int strprice =Integer.parseInt(price[0]);
				int endprice =Integer.parseInt(price[1]);
				map.put("strprice", strprice);
				map.put("endprice", endprice);
			}
			if(!request.getParameter("prAlcohol").equals("0도 - 0도") && request.getParameter("prAlcohol")!=null) {
				String prAlcohol = request.getParameter("prAlcohol");	
				String alcohol[] = prAlcohol.split("도");
				int stral =Integer.parseInt(alcohol[0]);
				int endal =Integer.parseInt(alcohol[1]);
				map.put("stral", stral);
				map.put("endal", endal);
				
			}
				
			if(request.getParameter("Skind")!= null && !request.getParameter("Skind").equals("전통주 종류")) {
				String skind = request.getParameter("Skind");
				map.put("skind", skind);
			}
			
			if(request.getParameter("name")!= null) {
				String name = request.getParameter("name");
				System.out.println("name :" +name);
				map.put("name", name);
			}
			count = productservice.getproductcount(map);	
			if(count>0) {
				productlist = productservice.getproduct(map);
			}
		}
		else {
			count= productservice.getproductcount();
			
			if(count>0) {
				productlist = productservice.getproduct();
			}
		}
		
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
		myorderlist = productservice.myorderlist(id,0);
	}
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
		//System.out.println(orcode);
		
		OrderVo orderinfo = productservice.orderdetail(orcode);
		
		model.addAttribute("orderinfo",orderinfo);
		
		
		return "product/myorderdetail.mn";
	}
	
	@RequestMapping("insertOrder")
	public String insertOrder(Model model, OrderVo ordervo, HttpSession session) throws SQLException{
		System.out.println(ordervo.getReceiverTel());
		
		String[] receiverTels = ordervo.getReceiverTel().split(",");
		String receiverTel = "";
		for(String receiverTel_ : receiverTels) {
			receiverTel += receiverTel_ ;
		}
		
		//ordervo.setReceiverTel(receiverTel);
		
		productservice.insertorderinfo(ordervo);
		
		String id = (String)session.getAttribute("memId");
		int myordercount = productservice.myordercount(id);
		List myorderlist = null;
		if(myordercount>0) {
			myorderlist = productservice.myorderlist(id,0);
		}
		model.addAttribute("myordercount",myordercount);
		model.addAttribute("myorderlist",myorderlist);
		
		
		return "product/myorderlist.mn";
	}
	
	@RequestMapping("modifyorder")
	public String modifyorder (Model model, OrderVo ordervo, HttpSession session, HttpServletRequest request) throws SQLException{
		String orCode =request.getParameter("orcode");
		System.out.println(orCode);
		
		
		ordervo.setOrCode(orCode);
		
		System.out.println(ordervo.getReceiverTel());
		System.out.println(ordervo.getOrCode());
		System.out.println(ordervo.getReceiverAddr());
		System.out.println(ordervo.getReceiverName());
		productservice.updateItem(ordervo);
		
		String id = (String)session.getAttribute("memId");
		int myordercount = productservice.myordercount(id);
		List myorderlist = null;
		if(myordercount>0) {
			myorderlist = productservice.myorderlist(id,0);
		}
		model.addAttribute("myordercount",myordercount);
		model.addAttribute("myorderlist",myorderlist);
		
		
		return "product/myorderlist.mn";
	}
	
	@RequestMapping("deleteorder")
	public String deleteorder(HttpServletRequest request, HttpSession session, Model model) throws SQLException {
		String orCode = request.getParameter("orcode");
		productservice.updateItem(orCode);
		
		String id = (String)session.getAttribute("memId");
		int myordercount = productservice.myordercount(id);
		List myorderlist = null;
		if(myordercount>0) {
			myorderlist = productservice.myorderlist(id,0);
		}
		model.addAttribute("myordercount",myordercount);
		model.addAttribute("myorderlist",myorderlist);
		
		
		return "product/myorderlist.mn";
	}
	
	@RequestMapping("orderlist")
	public String orderlist(HttpSession session, Model model , String pageNum, HttpServletRequest request) throws SQLException {
		String id = (String)session.getAttribute("memId");
		List orderlist = null;
		
		int ordercount = 0;
		if(pageNum ==null)pageNum = "1";
		Pager pager = new Pager();
		PageVO pageVO = null;
		HashMap map =null;
		
		if(request.getParameter("isSearch") !=null && request.getParameter("isSearch").equals("true")) {
			map = new HashMap();
			if(request.getParameter("prName")!= null &&request.getParameter("prName").length() != 0 ) {
				String prName = request.getParameter("prName");
				System.out.println("prName :" +prName);
				map.put("prName", prName);
			}
			if(request.getParameter("userId")!= null &&request.getParameter("userId").length() != 0 ) {
				String userId = request.getParameter("userId");
				System.out.println("userId :" +userId);
				map.put("userId", userId);
			}	
			if(request.getParameter("orStatus")!= null &&request.getParameter("orStatus").length() != 0 ) {
				String arr[] = request.getParameterValues("orStatus");
				for(String orStatus : arr) {
					
				}
				
			
				System.out.println(map.get("orStatus"));
			}	
			if(request.getParameter("orStart")!= null &&request.getParameter("orStart").length() != 0 ) {
				String orStart = request.getParameter("orStart");
				System.out.println("orStart :" +orStart);
				map.put("orStart", orStart);	
			}	
			if(request.getParameter("orEnd")!= null &&request.getParameter("orEnd").length() != 0 ) {
				String orEnd = request.getParameter("orEnd");
				System.out.println("orEnd :" +orEnd);
				map.put("orEnd", orEnd);
			}
		}
		
		else {
			ordercount = productservice.getordercount(id);
			pageVO =pager.pager(pageNum, ordercount);
			int startrow = pageVO.getStartRow();
			int endrow = pageVO.getEndRow();
			if(ordercount >0) { 
				orderlist = productservice.getorderlist(id,startrow,endrow);
			}
		}
		model.addAttribute("orderlist",orderlist);
		model.addAttribute("ordercount",ordercount);
		model.addAttribute("pageVO",pageVO);
		model.addAttribute("pageNum",pageNum);
		return "product/orderlist.mn";
	}
	
	@RequestMapping("orderdetail")
	public String orderdetail(HttpServletRequest request , Model model) throws SQLException {
		String orcode = request.getParameter("orcode");
		System.out.println(orcode);
		
		model.addAttribute(orcode);
		
		OrderVo orderinfo = productservice.orderdetail(orcode);
		
		model.addAttribute("orderinfo",orderinfo);
		
		return "product/orderdetail.mn";
	}
	
	
	//////ajax 
	
	//더보기
	
	@RequestMapping("more")
	@ResponseBody
	public List more(@RequestParam(name="num",required = false) Integer num,HttpSession session) throws SQLException {
		// 밑에 게시글 뿌리는 메서드
		String id = (String)session.getAttribute("memId");
		List list = productservice.myorderlist(id, num);
		
		
		return list;
	}
	

}
