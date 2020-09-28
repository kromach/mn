package project.spring.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/")
public class ProductController {
	
	@RequestMapping("productList")
		public String productList() {
		
		return "/product/productList";
	}

}
