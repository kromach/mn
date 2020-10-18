package project.spring.main.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;

import project.spring.aop.MemberAspect;
import project.spring.article.service.ArticleServiceImpl;
import project.spring.beans.kakaoAPI.KakaoLogin;
import project.spring.beans.kakaoAPI.KakaoLogout;
import project.spring.drink.vo.DrinkVO;
import project.spring.event.vo.EventVO;
import project.spring.main.service.MainService;
import project.spring.product.vo.ProductVo;

@Controller
public class MainController {
	
	@Autowired
	private MainService service;
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			HttpServletRequest request,
			HttpServletResponse response) {
	
		//메인 그림 초기화 블럭
		//메인으로 갈때 그림30개,링크 셋트를 랜덤으로 가지고 가야함 6*5
		/****************************************************************/
		//product에서 10개
			List<ProductVo> product = service.getProductInitial();
			/*
			<a href="/product/productdetail?prcode=${product[0].prCode}">
				<img src="${product[0].prImg}" />
			</a>
			*/
		//drink에서 10개
			List<DrinkVO> drink = service.getDrinkInitial();
			/*
			<a href="/drink/detail?dkCode=${drink[0].dkCode}">
				<img src="${drink[0].dkImg}" />
			</a>
		    */
		//event에서 10개
			List<EventVO> event = service.getEventInitial();
			
		model.addAttribute("event", event);	
		model.addAttribute("drink", drink);	
		model.addAttribute("product", product);
		return "/main/main.mn";
	}
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public String editor(Locale locale, Model model,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		return "/main/editor.mn";
	}
	@RequestMapping(value = "/editorData", method = RequestMethod.POST)
	public String editorData(@RequestParam String data) {
		System.out.println("["+data+"]");
		return "/main/editor.mn";
	}
}
