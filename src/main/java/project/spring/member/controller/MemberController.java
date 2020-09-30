package project.spring.member.controller;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.fasterxml.jackson.databind.JsonNode;

import project.spring.beans.kakaoAPI.KakaoLogin;
import project.spring.beans.kakaoAPI.KakaoLogout;
import project.spring.member.service.MemberServiceImpl;
import project.spring.member.vo.MemberDTO;

@Controller
@RequestMapping("/member")
public class MemberController {
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
	@Autowired
	MemberServiceImpl memberService;
	
	@RequestMapping("/signup")
	public String signUp(HttpServletRequest reqest) {
		String returnUrl = "/";
		HttpSession session =  reqest.getSession();
		if(session.getAttribute("memId")==null) {
			returnUrl = "/member/signStatus.mn";
		}else{
			System.out.println("로그인이 된 상태입니다.");
		}
		return returnUrl;
	}
	
	@RequestMapping("/signupUser")
	public String signUpUser(HttpServletRequest reqest) {
		String returnUrl = "/";
		HttpSession session =  reqest.getSession();
		if(session.getAttribute("memId")==null) {
			reqest.setAttribute("status", "user");
			returnUrl = "/member/signupForm.mn";
		}else{
			System.out.println("로그인이 된 상태입니다.");
		}
		return returnUrl;
	}
	@RequestMapping("/signupSales")
	public String signUpSalses(HttpServletRequest reqest) {
		String returnUrl = "/";
		HttpSession session =  reqest.getSession();
		if(session.getAttribute("memId")==null) {
			reqest.setAttribute("status", "salse");
			returnUrl = "/member/signupForm.mn";
		}else{
			System.out.println("로그인이 된 상태입니다.");
		}
		return returnUrl;
	}
	
	@RequestMapping("/signupUserPro")
	public String signupUserPro(
			@ModelAttribute MemberDTO dto,
			Model model
			) {
		
		String[] birth_ = dto.getBirth().split(",");
		String birth = "";
		for( String i:birth_ ) {
			birth += i;
		}
		
		dto.setUserKind("user");
		dto.setBirth(birth);
		System.out.println(birth);
		System.out.println(dto);
		System.out.println("memberInsertReulst="+ memberService.insertItem(dto));
		System.out.println("nickName : "+dto.getNickName());
		model.addAttribute("nickName", dto.getNickName());
		return "/member/singupResult.mn";
	}
	
	@RequestMapping("/signupSalesPro")
	public String signupSalesPro(
			@ModelAttribute MemberDTO dto,
			Model model,
			MultipartHttpServletRequest mpRequest
			) {
		
		String[] birth_ = dto.getBirth().split(",");
		String birth = "";
		for( String i:birth_ ) {
			birth += i;
		}
		//사업자등록증 파일제한 15MB
		int sizeLimit = 1024*1024*15;
		MultipartFile mf = null;
		String path = null;
		System.out.println(mpRequest.toString());
		try {
			mf = mpRequest.getFile("file");
			path = mpRequest.getRealPath("/img/member/license");
			//fileName seq처리
			String orgName = mf.getOriginalFilename();
			String imgName = orgName.substring(0,orgName.lastIndexOf('.'));
			System.out.println(imgName);
			String ext = orgName.substring(orgName.lastIndexOf('.'));
			Long date = System.currentTimeMillis();
			String newName = imgName + date + ext;
			System.out.println("newName="+newName);
			String imPath = path + "\\"	+ newName;
			File copyFile = new File(imPath);
			mf.transferTo(copyFile);
			//dtoImgSetting
			dto.setBirth(birth);
			dto.setLicenseImg(newName);
			dto.setUserKind("sales");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(dto);
		System.out.println("memberInsertReulst="+ memberService.insertItem(dto));
		model.addAttribute("nickName", dto.getNickName());
		return "/member/singupResult.mn";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest reqest,Model model) {
		String returnUrl = "/";
		HttpSession session =  reqest.getSession();
		if(session.getAttribute("memId")==null) {
			reqest.setAttribute("status", "salse");
			returnUrl = "/member/loginForm.mn";
		}else{
			System.out.println("로그인이 된 상태입니다.");
		}
		
		model.addAttribute("restApikey", restApikey);
		model.addAttribute("callback_URL", callback_URL);
		
		return returnUrl;
	}
	
	@RequestMapping(value = "/authResult", produces = "application/json", method = {
			RequestMethod.GET,	RequestMethod.POST })
	public String kakaoLogin(
			@RequestParam("code") String code,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

		//getToken
		JsonNode tokenJson = KakaoLogin.getAccessToken(code);
		String token = tokenJson.path("access_token").toString();
		//useToken getuserInfo
		JsonNode userInfo = KakaoLogin.getKakaoUserInfo(token);
		JsonNode kakao_account = userInfo.get("kakao_account");
		JsonNode properties = userInfo.path("properties");
		
		String id = kakao_account.get("email").asText();
		String gender = kakao_account.get("gender").asText();
		String nickname = properties.path("nickname").toString();	
		
		System.out.println("id"+id);
		System.out.println("gender"+gender);
		System.out.println("nickname"+nickname);
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setNickName(nickname);
		
		HttpSession session = request.getSession();
		request.setAttribute("memberDTO", dto);
		
		//add Db Loginc
		return "redirect:/member/loginResult";
	}
	
	@RequestMapping(value = "/loginResult")
	public String loginResult(HttpServletRequest request) {
		
		return "/member/loginResult.mn";
	}

	//logoutLogic
	@RequestMapping(value = "/logout", 
			produces = "application/json",
			method = {
			RequestMethod.GET,
			RequestMethod.POST })
	public String kakaoLogout(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String token = session.getAttribute("token").toString();
		System.out.println("token="+token);
		JsonNode logoutInfo = KakaoLogout.doLogout(token);
		System.out.println("logoutInfo="+logoutInfo);
		System.out.println("=======API_token_out==========================");
		return null;
	}
	
	
	
}
