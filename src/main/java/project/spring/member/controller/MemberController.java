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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		String returnUrl = "redirect:/";
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
		String returnUrl = "redirect:/";
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
		String returnUrl = "redirect:/";
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
			Model model,
			HttpServletRequest request
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
		request.getSession().setAttribute("memId", dto.getId());
		request.getSession().setAttribute("memNickName", dto.getNickName());
		
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
		mpRequest.getSession().setAttribute("memId", dto.getId());
		mpRequest.getSession().setAttribute("memNickName", dto.getNickName());
		return "/member/singupResult.mn";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest reqest,Model model) {
		String returnUrl = "redirect:/";
		HttpSession session =  reqest.getSession();
		if(session.getAttribute("memId")==null) {
			reqest.setAttribute("restApikey", restApikey);
			reqest.setAttribute("callback_URL", callback_URL);
			returnUrl = "/member/loginForm.mn";
		}else{
			System.out.println("로그인이 된 상태입니다.");
		}
		return returnUrl;
	}
	
	@RequestMapping(value = "/authResult", produces = "application/json", method = {
			RequestMethod.GET,	RequestMethod.POST })
	public String kakaoLogin(
			@RequestParam("code") String code,
			HttpServletRequest request,
			HttpServletResponse response,
			RedirectAttributes redirectAttributes)
			throws Exception {

		//getToken
		JsonNode tokenJson = KakaoLogin.getAccessToken(code);
		String token = tokenJson.path("access_token").toString();
		request.getSession().setAttribute("token", token);
		//useToken getuserInfo
		JsonNode userInfo = KakaoLogin.getKakaoUserInfo(token);
		JsonNode kakao_account = userInfo.get("kakao_account");
		JsonNode properties = userInfo.path("properties");
		System.out.println(userInfo);
		System.out.println("=============================");
		String id = kakao_account.get("email").asText();
		String gender = kakao_account.get("gender").asText();
		String birth = kakao_account.get("birthday").asText();
		String nickname = properties.path("nickname").toString();	
		System.out.println("id"+id);
		System.out.println("gender"+gender);
		System.out.println("nickname"+nickname);
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setNickName(nickname);
		dto.setBirth(birth);
		
		/*
		 * 회원가입 안되어있을때 회원가입하는 로직 조회의 이후에 첨부 필요
		 */
		//Session에 값 넣어주기
		request.getSession().setAttribute("memId", id);
		request.getSession().setAttribute("memNickName", nickname);
		redirectAttributes.addFlashAttribute("memberDTO", dto);
		//id O pw O
		request.setAttribute("result", 1);
		
		return "redirect:/member/loginResult";
	}
	
	@RequestMapping(value = "/loginResult")
	public String loginResult(HttpServletRequest request,@ModelAttribute MemberDTO model) {
		System.out.println(model.toString());
		return "/member/loginResult.mn";
	}
	
	@RequestMapping(value = "/loginPro")
	public String loginPro(HttpServletRequest request,@ModelAttribute MemberDTO model) {
		
		//-1 - id x pw x
		//0 - id o pw x
		//1 - id o pw o
		int result = memberService.readItem(model);
		request.setAttribute("result", result);
		if(result==1) {
			//sessionSetting
			System.out.println(model.toString());
		}
		
		return "/member/loginResult.mn";
	}
	
	//Kakao와 일반logOut같이 처리할것
	//logoutLogic
	@RequestMapping(value = "/logout", 
			produces = "application/json",
			method = {
			RequestMethod.GET,
			RequestMethod.POST })
	public String logoutSs(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		/*kakao logout logic*/
		if(session.getAttribute("token")!=null) {
			String token = session.getAttribute("token").toString();
			System.out.println("token="+token);
			JsonNode logoutInfo = KakaoLogout.doLogout(token);
			System.out.println("logoutInfo="+logoutInfo);
			System.out.println("=======API_token_out==========================");
		}
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	//정보수정
	@RequestMapping(value = "/modify")
	public String modifyMemberSs(HttpSession session){
		
		String id = session.getAttribute("memId").toString();
		MemberDTO result = memberService.readItem();
		
		
		System.out.println("modify");
		
		return null;
	} 
	//id찾기
	//pw찾기
	
	
}
