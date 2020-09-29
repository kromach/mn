package project.spring.product.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import project.spring.product.service.ProductService;

@Controller
@EnableWebMvc
@RequestMapping("/product/")
public class ProductController {
	
	@Autowired
	private ProductService productservice = null;
	
	@RequestMapping("productList")
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
		return "/product/productList.mn";
	}
	
	
	
	@RequestMapping("productdetail")
		public String productdetail	() {
		
		return "/product/productdetail.mn";
	}

}
