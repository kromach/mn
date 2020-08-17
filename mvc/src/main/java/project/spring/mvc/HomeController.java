package project.spring.mvc;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		request.setAttribute("restApikey",restApikey );
		request.setAttribute("callback_URL", callback_URL);
		return "kakao_auth_form";
	}
	
	//testing
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public String callBack_getToken(
			HttpServletRequest request,
			@RequestParam String code) {
		
		System.out.println("auth Code=" + code);
		System.out.println("url=");
		request.setAttribute("code", code);
		request.setAttribute("callback_URL",
				callback_URL);
		request.setAttribute("restApikey",
				restApikey);
		
		return "kakao_auth_submit";
	}
	
	@RequestMapping(value = "/authResult", method = RequestMethod.GET)
	@ResponseBody
	public String callBack_getAuthCode(
			HttpServletRequest request,
			@RequestParam String code,
			@RequestBody Map<String, Object> auth
		) {

		System.out.println("auth Code=" + code);
		System.out.println("url=");
		request.setAttribute("code", code);
		request.setAttribute("callback_URL",
				callback_URL);
		request.setAttribute("restApikey",
				restApikey);
		
		
		if(auth !=null){ auth.toString(); }
		
		
		return "kakao_auth_submit";
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String callBack_getToken(Locale locale, Model model,@RequestParam String code) {
		
		return "kakao_auth_result";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)	
	public String deatil(Locale locale, Model model) {
		return "detail";
	}
}
