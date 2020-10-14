package project.spring.sales.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import project.spring.sales.service.SalesService;

@Controller
@RequestMapping("/sales")
public class SalesController {

	@Autowired
	SalesService salesService = null;
	
	@RequestMapping(value ="/index")
	public String index(HttpServletRequest request) {
		System.out.println("SalesIndex Controller");
		int count = 0;
		
		HttpSession session = request.getSession();
		String memId = (String)session.getAttribute("memId");
		System.out.println("Session : " + memId);
		count = salesService.salesCount(memId);
		System.out.println("count : " + count);
		
		
		return null;
	}
	
	
	
	
	
	
	
}
