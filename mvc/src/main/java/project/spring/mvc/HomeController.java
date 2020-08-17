package project.spring.mvc;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private String nativeAppkey;
	@Autowired
	private String restApikey;
	@Autowired
	private String javaScriptkey;
	@Autowired
	private String adminkey;
	@Autowired
	private String callback_URL;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			HttpServletRequest request,HttpServletResponse response) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		System.out.println("nativeAppkey="+nativeAppkey);
		System.out.println("restApikey="+restApikey);
		System.out.println("javaScriptkey="+javaScriptkey);
		System.out.println("adminkey="+adminkey);
		System.out.println("callback_URL="+callback_URL);
		
		request.setAttribute("restApikey",restApikey );
		request.setAttribute("callback_URL", callback_URL);
		return "kakao_auth_form";
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public String callBack(Locale locale, Model model,@RequestParam String code) {
		System.out.println("auth Code="+code);
		return "kakao_auth_result";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)	
	public String deatil(Locale locale, Model model) {
		return "detail";
	}
}
