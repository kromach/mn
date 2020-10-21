package project.spring.main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
		List<MainVO> main = new ArrayList();
		//product에서 10개
			List<ProductVo> product = service.getProductInitial(0);
			for(ProductVo vo : product) {
				String aLinkUri = "/product/productdetail?prcode="+vo.getPrCode();
				MainVO mainVo = new MainVO();
				mainVo.setaLinkUri(aLinkUri);
				mainVo.setImgUri(vo.getPrImg());
				main.add(mainVo);
			}
		//drink에서 10개
			List<DrinkVO> drink = service.getDrinkInitial(0);
			for(DrinkVO vo : drink) {
				MainVO mainVo = new MainVO();
				String aLinkUri = "/drink/detail?dkCode="+vo.getDkCode();
				mainVo.setaLinkUri(aLinkUri);
				mainVo.setImgUri(vo.getDkImg());
				main.add(mainVo);
			}
		//event에서 10개
			List<EventVO> event = service.getEventInitial(0);
			for(EventVO vo : event) {
				MainVO mainVo = new MainVO();
				String aLinkUri = "event/detail?eventCode="+vo.getEventCode();
				mainVo.setaLinkUri(aLinkUri);
				mainVo.setImgUri(vo.getThumImg());
				main.add(mainVo);
			}
		//shuffle
		Collections.shuffle(main);	
		model.addAttribute("main", main);	
		return "/main/main.mn";
	}
	//검색창
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Locale locale, Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(name = "search") String search
			) {
		List<MainVO> main = new ArrayList();
		//검색단어를 포함해서
		//product에서 10개
		List<ProductVo> product = service.getProductInitial(0,search);
		for(ProductVo vo : product) {
			String aLinkUri = "/product/productdetail?prcode="+vo.getPrCode();
			MainVO mainVo = new MainVO();
			mainVo.setaLinkUri(aLinkUri);
			mainVo.setImgUri(vo.getPrImg());
			main.add(mainVo);
		}
		//drink에서 10개
			List<DrinkVO> drink = service.getDrinkInitial(0,search);
			for(DrinkVO vo : drink) {
				MainVO mainVo = new MainVO();
				String aLinkUri = "/drink/detail?dkCode="+vo.getDkCode();
				mainVo.setaLinkUri(aLinkUri);
				mainVo.setImgUri(vo.getDkImg());
				main.add(mainVo);
			}
		//event에서 10개
			List<EventVO> event = service.getEventInitial(0,search);
			for(EventVO vo : event) {
				MainVO mainVo = new MainVO();
				String aLinkUri = "event/detail?eventCode="+vo.getEventCode();
				mainVo.setaLinkUri(aLinkUri);
				mainVo.setImgUri(vo.getThumImg());
				main.add(mainVo);
			}
		//shuffle
		Collections.shuffle(main);
		System.out.println("mainSearch=============");
		for(MainVO vo : main) {
			System.out.println(vo);
		}
		System.out.println("mainSearch=============");
		model.addAttribute("main", main);
		model.addAttribute("search", search);
		return "/main/mainSearch.mn";
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
	//ajax
	@RequestMapping(value = "/reload")
	@ResponseBody
	public List reload(@RequestParam(name = "index") int index) {
		//메인 과 같은 로직
			//메인으로 갈때 그림30개,링크 셋트를 랜덤으로 가지고 가야함 6*5
			/****************************************************************/
			List<MainVO> main = new ArrayList();
			//product에서 10개
				List<ProductVo> product = service.getProductInitial(index+1);
				for(ProductVo vo : product) {
					String aLinkUri = "/product/productdetail?prcode="+vo.getPrCode();
					MainVO mainVo = new MainVO();
					mainVo.setaLinkUri(aLinkUri);
					mainVo.setImgUri(vo.getPrImg());
					main.add(mainVo);
				}
			//drink에서 10개
				List<DrinkVO> drink = service.getDrinkInitial(index+1);
				for(DrinkVO vo : drink) {
					MainVO mainVo = new MainVO();
					String aLinkUri = "/drink/detail?dkCode="+vo.getDkCode();
					mainVo.setaLinkUri(aLinkUri);
					mainVo.setImgUri(vo.getDkImg());
					main.add(mainVo);
				}
			//event에서 10개
				List<EventVO> event = service.getEventInitial(index+1);
				for(EventVO vo : event) {
					MainVO mainVo = new MainVO();
					String aLinkUri = "/event/detail?event="+vo.getEventCode();
					mainVo.setaLinkUri(aLinkUri);
					mainVo.setImgUri("/resources/img/upload/"+vo.getThumImg());
					main.add(mainVo);
				}
			
			//return 갯수가 1보다 커야 리턴
			System.out.println("reload Size========"+main.size());
			if(main.size()>0) {
				//shuffle
				Collections.shuffle(main);	
				return main;
			}else {
				return null;
			}
	}
	//ajax
	@RequestMapping(value = "/reloadSearch")
	@ResponseBody
	public List reloadSearch(@RequestParam(name = "index") int index,
			@RequestParam(name = "search") String search
			) {
		//메인 과 같은 로직
		//메인으로 갈때 그림30개,링크 셋트를 랜덤으로 가지고 가야함 6*5
		/****************************************************************/
		List<MainVO> main = new ArrayList();
		//product에서 10개
		List<ProductVo> product = service.getProductInitial(index+1,search);
		for(ProductVo vo : product) {
			String aLinkUri = "/product/productdetail?prcode="+vo.getPrCode();
			MainVO mainVo = new MainVO();
			mainVo.setaLinkUri(aLinkUri);
			mainVo.setImgUri(vo.getPrImg());
			main.add(mainVo);
		}
		//drink에서 10개
		List<DrinkVO> drink = service.getDrinkInitial(index+1,search);
		for(DrinkVO vo : drink) {
			MainVO mainVo = new MainVO();
			String aLinkUri = "/drink/detail?dkCode="+vo.getDkCode();
			mainVo.setaLinkUri(aLinkUri);
			mainVo.setImgUri(vo.getDkImg());
			main.add(mainVo);
		}
		//event에서 10개
		List<EventVO> event = service.getEventInitial(index+1,search);
		for(EventVO vo : event) {
			MainVO mainVo = new MainVO();
			String aLinkUri = "/event/detail?event="+vo.getEventCode();
			mainVo.setaLinkUri(aLinkUri);
			mainVo.setImgUri("/resources/img/upload/"+vo.getThumImg());
			main.add(mainVo);
		}
		//shuffle
		Collections.shuffle(main);	
		
		//return 갯수가 1보다 커야 리턴
		System.out.println("reload Size========"+main.size());
		if(main.size()>0) {
			return main;
		}else {
			return null;
		}
	}
	
	
}
