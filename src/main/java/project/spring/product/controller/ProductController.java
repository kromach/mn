package project.spring.product.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import project.spring.article.service.ArticleServiceImpl;
import project.spring.article.vo.ArticleDTO;
import project.spring.beans.PageVO;
import project.spring.beans.Pager;
import project.spring.member.service.MemberServiceImpl;
import project.spring.member.vo.MemberDTO;
import project.spring.product.service.ProductService;
import project.spring.product.service.ProductServiceImpl;
import project.spring.product.vo.OrderVo;
import project.spring.product.vo.ProductVo;
import project.spring.product.vo.ProductboardVo;

@Controller
@EnableWebMvc
@RequestMapping("/product/")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productservice = null;
	@Autowired
	private MemberServiceImpl memberservice = null;
	@Autowired
	private ArticleServiceImpl articleService = null;
	
	
	@RequestMapping("productlist")
		public String productList(Model model ,HttpServletRequest request) throws SQLException {
		List productlist =null;
		int count = 0;
		HashMap map = null;
		
		// 검색 기능
		if(request.getParameter("isSearch")!=null && request.getParameter("isSearch").equals("true")) {
			map = new HashMap();
			
			if(request.getParameter("prPrice")!=null) {
				String prPrice = request.getParameter("prPrice");
				String price[] = prPrice.split(";");
				int strprice =Integer.parseInt(price[0]);
				int endprice =Integer.parseInt(price[1]);
				map.put("strprice", strprice);
				map.put("endprice", endprice);
				model.addAttribute("prPrice", prPrice);
			}
			if(request.getParameter("prAlcohol")!=null) {
				String prAlcohol = request.getParameter("prAlcohol");	
				String alcohol[] = prAlcohol.split(";");
				int stral =Integer.parseInt(alcohol[0]);
				int endal =Integer.parseInt(alcohol[1]);
				map.put("stral", stral);
				map.put("endal", endal);
				model.addAttribute("prAlcohol", prAlcohol);
			}
				
			if(request.getParameter("Skind")!= null && !request.getParameter("Skind").equals("소분류 선택")) {
				String skind = request.getParameter("Skind");
				map.put("skind", skind);
				model.addAttribute("skind", skind);
			}
			
			if(request.getParameter("name")!= null && !request.getParameter("Skind").equals("")) {
				String name = request.getParameter("name");
				map.put("name", name);
				model.addAttribute("name", name);
			}
			count = productservice.getproductcount(map);	
			if(count>0) {
				productlist = productservice.getproduct(map);
			}
		}
		else {
			count= productservice.getproductcount();
			
			if(count>0) {
				productlist = productservice.getproduct(0);
			}
		}
		if(productlist!=null)Collections.shuffle(productlist);
		
		model.addAttribute("productlist", productlist);
		model.addAttribute("count", count);
		return "product/productList.mn";
	}
	
	@RequestMapping("/reload")
	@ResponseBody
	public List reload(@RequestParam(name="index") int index)throws SQLException {
		
		List productlist = productservice.getproduct(index + 1);
		if(productlist!=null&& productlist.size() > 0) {
			Collections.shuffle(productlist);
			return productlist;
		}else {
			return null;
		}
	}
	
	
	
	@RequestMapping("productdetail")
		public String productdetail	(HttpServletRequest request, Model model, HttpSession session) throws SQLException {
		String prcode = request.getParameter("prcode");
		String memId = (String)session.getAttribute("memId");
		List articlelist = null;
		
		ProductVo info =productservice.getproductinfo(prcode);
		
		int count = productservice.getarticlecount(prcode);
		System.out.println(count);
		if(count > 0) {
			articlelist = productservice.getarticle(prcode,0);
			System.out.println("리스트 불러오기");
			System.out.println(articlelist);
			
			model.addAttribute("articlelist", articlelist);
		}
		
		model.addAttribute("memId", memId);
		model.addAttribute("info", info);
		model.addAttribute("prcode", prcode);
		
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
	public String myorderdetailSs (HttpServletRequest request, Model model) throws SQLException{
		String orcode = request.getParameter("orcode");
		//System.out.println(orcode);
		
		OrderVo orderinfo = productservice.orderdetail(orcode);
		
		model.addAttribute("orderinfo",orderinfo);
		
		
		return "product/myorderdetail.mn";
	}
	
	@RequestMapping("insertOrder")
	public String insertOrderSs (Model model, OrderVo ordervo, HttpSession session) throws SQLException{
		String prcode =ordervo.getPrCode();
		int buy = ordervo.getPrCount();
		System.out.println("코드!!!!!!!!!!!!!!!!!!!!");
		System.out.println(prcode);
		String[] receiverTels = ordervo.getReceiverTel().split(",");
		String receiverTel = "";
		for(String receiverTel_ : receiverTels) {
			receiverTel += receiverTel_ ;
		}
		//ordervo.setReceiverTel(receiverTel);
		int count = productservice.getamount(prcode);
		
		if(count < buy)
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
	public String modifyorderSs (Model model, OrderVo ordervo, HttpSession session, HttpServletRequest request) throws SQLException{
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
	public String deleteorderSs(HttpServletRequest request, HttpSession session, Model model) throws SQLException {
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
	public String orderlistSs (HttpSession session, Model model , String pageNum, HttpServletRequest request) throws SQLException {
		String id = (String)session.getAttribute("memId");
		List orderlist = null;
		
		int ordercount = 0;
		if(pageNum ==null)pageNum = "1";
		Pager pager = new Pager();
		PageVO pageVO = null;
		HashMap map =new HashMap();
		map.put("id", id);
		
		if(request.getParameter("isSearch") !=null && request.getParameter("isSearch").equals("true")) {
			
			if(request.getParameter("prName")!= null &&request.getParameter("prName").length() != 0 ) {
				String prName = request.getParameter("prName");
				map.put("prName", prName);
			}
			if(request.getParameter("userId")!= null &&request.getParameter("userId").length() != 0 ) {
				String userId = request.getParameter("userId");
				map.put("userId", userId);
			}	
			if(request.getParameter("orStatus")!= null &&request.getParameter("orStatus").length() != 0 ) {
				String arr[] = request.getParameterValues("orStatus");
				 int[] nums = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
				for(int orStatus : nums) {
				}
				map.put("nums", nums);
			}	
			if(request.getParameter("orStart")!= null &&request.getParameter("orStart").length() != 0 ) {
				String ors = request.getParameter("orStart");
				String orStart = ors.replace("-","");
				map.put("orStart", orStart);	
			}	
			if(request.getParameter("orEnd")!= null &&request.getParameter("orEnd").length() != 0 ) {
				String ore = request.getParameter("orEnd");
				String orEnd = ore.replace("-","");
				map.put("orEnd", orEnd);
			}
			
			ordercount = productservice.getordercount(map);
			System.out.println("검색 카운트 숫자 :"+ordercount);
			pageVO =pager.pager(pageNum, ordercount);
			int startrow = pageVO.getStartRow();
			int endrow = pageVO.getEndRow();
			map.put("startrow", startrow);
			map.put("endrow", endrow);
			if(ordercount >0) { 
				orderlist = productservice.getorderlist(map);
			}
		}
		
		else {
			ordercount = productservice.getordercount(map);
			pageVO =pager.pager(pageNum, ordercount);
			int startrow = pageVO.getStartRow();
			int endrow = pageVO.getEndRow();
			map.put("startrow", startrow);
			map.put("endrow", endrow);
			if(ordercount >0) { 
				orderlist = productservice.getorderlist(map);
			}
		}
		model.addAttribute("orderlist",orderlist);
		model.addAttribute("ordercount",ordercount);
		model.addAttribute("pageVO",pageVO);
		model.addAttribute("pageNum",pageNum);
		return "product/orderlist.mn";
	}
	
	@RequestMapping("orderdetail")
	public String orderdetailSs (HttpServletRequest request , Model model ,OrderVo ordervo) throws SQLException {
		String orcode = ordervo.getOrCode();
		HashMap map = null;
		
		if(request.getParameter("ischang") !=null && request.getParameter("ischang").equals("true")) {
			String orstatus = request.getParameter("orstatus");
			int orsta = Integer.parseInt(orstatus);
			map = new HashMap();
			map.put("orcode", orcode);
			map.put("orsta", orsta);
			productservice.updateItem(map);
		}
		
		if(request.getParameter("insertdel") !=null && request.getParameter("insertdel").equals("true")) {
			productservice.updateItem(ordervo);
		}
		
		
		
		OrderVo orderinfo = productservice.orderdetail(orcode);
		
		model.addAttribute("orderinfo",orderinfo);
		
		return "product/orderdetail.mn";
	}
	
	@RequestMapping("writeform")
	public String writerform (HttpServletRequest request, Model model) throws SQLException{
		String kind = request.getParameter("kind");
		String prcode = request.getParameter("prcode");
		String prname = request.getParameter("prname");
		
		
		model.addAttribute("prname",prname);
		model.addAttribute("kind",kind);
		model.addAttribute("prcode",prcode);
		return "product/writeform.mn";
	}
	
	//insertTag
			@RequestMapping("writePro")
			public String writePro(ArticleDTO dto, HttpServletRequest request)  throws IOException, FileUploadException {
				String kind = request.getParameter("kind");
				String prcode = request.getParameter("prcode");
				String prname = request.getParameter("prname");
				
				
				dto.setKind(kind);
				dto.setDkCode(prcode);
				System.out.println(dto);
				int result = articleService.insertItem(dto);
				
				return "redirect:productdetail?prcode="+prcode;
			}
			
	@RequestMapping("reviewdetail")
	public String reviewdetail (HttpServletRequest request, Model model) throws SQLException{
		String bnIdx_ = request.getParameter("bnIdx");
		int bnIdx = Integer.parseInt(bnIdx_);
		
		ArticleDTO article = productservice.getarticldetail(bnIdx);
			
		
		model.addAttribute("article",article);
		model.addAttribute("bnIdx",bnIdx);
		return "product/reviewdetail.mn";
	}
	
	@RequestMapping("deletereview")
	public String deletereview(HttpServletRequest request) throws SQLException {
		String prcode = request.getParameter("dkCoed");
		String bnIdx_ = request.getParameter("bnIdx");
		int bnIdx =Integer.parseInt(bnIdx_);
		
		int result = productservice.deleteItem(bnIdx);
		
		
		return "redirect:productdetail?prcode="+prcode;
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
	
	//더보기
	@RequestMapping("more2")
	@ResponseBody
	public List morere(@RequestParam(name="num",required = false) Integer num,
			@RequestParam(name="prcode",required = false) String prcode,
			HttpServletRequest request) throws SQLException {
		// 밑에 게시글 뿌리는 메서드
		System.out.println(prcode+":"+num);
		
		List list = productservice.getarticle(prcode,num);
		
		
		return list;
	}
	
	// 좋아요
	@RequestMapping(value = "/like")
	@ResponseBody
	public int likeSs(@RequestParam(name="prCode",required = false) String prCode,
					@RequestParam(name="memId",required = false) String memId,
					@RequestParam(name="insertId",required = false) String insertId
			) {
		
		//기본값 -1
		int result = -1;
		result = productservice.like(prCode,memId,insertId);
		//unlike
		return result;
	}
	
	// 좋아요 취소??
	@RequestMapping(value = "/unlike")
	@ResponseBody
	public int unlikeSs(@RequestParam(name="prCode",required = false) String prCode,
			@RequestParam(name="memId",required = false) String memId,
			@RequestParam(name="insertId",required = false) String insertId
			) {
		
		//기본값 -1
		int result = -1;
		result = productservice.unlike(prCode,memId,insertId);
		//unlike
		return result;
	}
	
	//리뷰 삭제
	@RequestMapping(value = "/deleteRe")
	@ResponseBody
	public int deleteRe(
		@RequestParam(name = "bnIdx") int bnIdx) {
		
		int result = productservice.deleteItem(bnIdx);
		
		return 0;
	}
	

}
