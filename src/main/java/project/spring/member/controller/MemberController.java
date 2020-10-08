package project.spring.member.controller;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.tiles.autotag.core.runtime.annotation.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		request.getSession().setAttribute("userKind", dto.getUserKind());
		
		return "/member/signupResult.mn";
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
		String[] tels = dto.getTel().split(",");
		String tel = "";
		for(String tel_ : tels) {
			tel += tel_;
		}
		dto.setUserKind("salse");
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
			dto.setTel(tel);
			dto.setBirth(birth);
			dto.setLicenseImg(newName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(dto);
		System.out.println("memberInsertReulst="+ memberService.insertItem(dto));
		model.addAttribute("nickName", dto.getNickName());
		mpRequest.getSession().setAttribute("memId", dto.getId());
		mpRequest.getSession().setAttribute("memNickName", dto.getNickName());
		mpRequest.getSession().setAttribute("userKind", "salse");
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
		String nickname = properties.path("nickname").toString().replaceAll("\"","");	
		System.out.println("id"+id);
		System.out.println("gender"+gender);
		System.out.println("nickname"+nickname);
		MemberDTO dto = new MemberDTO();
		
		dto.setId(id);
		dto.setNickName(nickname);
		dto.setName(nickname);
		
		int isNew = memberService.readItem(dto);
		System.out.println("KakaoIsNew"+isNew);
		int kakaoSignupResult = 0;
		if(isNew !=0) {
			//id가 없음 >> 회원가입
			request.setAttribute("kakaoMember", dto);
			return "/member/signupFormByKakao.mn";
		}
		
		//Session에 값 넣어주기
		request.getSession().setAttribute("memId", id);
		request.getSession().setAttribute("memNickName", nickname);
		request.getSession().setAttribute("userKind", "user");
		redirectAttributes.addFlashAttribute("memberDTO", dto);
		//id O pw O
		request.setAttribute("result", 1);
		
		return "/member/loginResult.mn";
	}
	
	/*=====================================================================================================*/
	@RequestMapping(value = "/signUpKakaoPro")
	public String signUpKakaoPro(HttpServletRequest request,@ModelAttribute MemberDTO dto) {
		System.out.println("Kakao추가정보="+dto);
		
		String[] birth_ = dto.getBirth().split(",");
		String birth = "";
		for( String i:birth_ ) {
			birth += i;
		}
		dto.setBirth(birth);
		String[] tels = dto.getTel().split(",");
		String tel = "";
		for(String tel_ : tels) {
			tel += tel_;
		}
		dto.setTel(tel);
		dto.setUserKind("user");
		System.out.println(dto);
		memberService.insertItem(dto);
		request.getSession().setAttribute("memId", dto.getId());
		request.getSession().setAttribute("memNickName", dto.getNickName());
		request.getSession().setAttribute("userKind", dto.getUserKind());
		request.setAttribute("result", 1);
		return "/member/signupResult.mn";
	}
	
	@RequestMapping(value = "/loginResult")
	public String loginResult(HttpServletRequest request,@ModelAttribute MemberDTO model) {
		System.out.println(model.toString());
		return "/member/loginResult.mn";
	}
	
	@RequestMapping(value = "/loginPro")
	public String loginPro(HttpServletRequest request,HttpSession session,@ModelAttribute MemberDTO model) {
		
		//-1 - id x pw x
		//0 - id o pw x
		//1 - id o pw o
		int result = memberService.readItem(model);
		MemberDTO setSessionDTO= null;
		if(result ==1 ) {
			setSessionDTO = memberService.setSession(model.getId());
			session.setAttribute("userKind", setSessionDTO.getUserKind());
			session.setAttribute("memId", setSessionDTO.getId());
			session.setAttribute("memNickName", setSessionDTO.getNickName());
		}
		request.setAttribute("result", result);
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
	public String modifyMemberSs(HttpSession session,Model model){
		
		String id = session.getAttribute("memId").toString();
		MemberDTO result = memberService.readItem();
		System.out.println(result);
		System.out.println("modify");
		model.addAttribute("memberDTO", result);
		model.addAttribute("status", result.getUserKind());
		return "/member/modifyForm.mn";
	}
	//정보수정 Pro
	@RequestMapping(value = "/modifyUserPro")
	public String modifyUserProSs(MemberDTO dto) {
		
		System.out.println(dto);
		memberService.updateItem(dto);
		return "/member/modifyResult.mn";
	}
	
	@RequestMapping(value = "/modifySalesPro")
	public String modifySalesProSs(MemberDTO dto) {
		System.out.println(dto);
		memberService.updateItem(dto);
		return "/member/modifyResult.mn";
	}
	//id찾기
	@RequestMapping(value = "/findId")
	public String findIdForm() {
		return "/member/findIdForm.mn";
	}
	//pw찾기
	@RequestMapping(value = "/findPw")
	public String findPwForm() {
		return "/member/findPwForm.mn";
	}
	//id찾기 처리
	@RequestMapping(value = "/findIdPro")
	public String findIdPro(MemberDTO dto,Model model) {
		System.out.println(dto);
		String[] births = dto.getBirth().split(",");
		String[] tels = dto.getTel().split(",");
		String birth = "";
		String tel = "";
		for(String birth_ : births) {
			birth += birth_;
		}
		for(String tel_ : tels) {
			tel += tel_;
		}
		System.out.println(tel+":"+birth);
		dto.setBirth(birth);
		dto.setTel(tel);
		
		MemberDTO result = memberService.findId(dto);

		model.addAttribute("memberDTO", result);
		model.addAttribute("result","id");
		
		return "/member/findResult.mn";
	}
	//pw찾기 처리
	@RequestMapping(value = "/findPwPro")
	public String findPwPro(MemberDTO dto,Model model) {
		
		System.out.println(dto);
		String[] births = dto.getBirth().split(",");
		String[] tels = dto.getTel().split(",");
		String birth = "";
		String tel = "";
		for(String birth_ : births) {
			birth += birth_;
		}
		for(String tel_ : tels) {
			tel += tel_;
		}
		System.out.println(tel+":"+birth);
		dto.setBirth(birth);
		dto.setTel(tel);
		
		MemberDTO result = memberService.findPw(dto);
		model.addAttribute("memberDTO", result);
		model.addAttribute("result","pw");
		
		return "/member/findResult.mn";
	}
	
	@RequestMapping(value = "/overlapCheck")
	@ResponseBody
	public Boolean overlapCheck(
			@RequestParam(value = "nickName", required = false) String nickName,
			@RequestParam(value = "id", required = false) String id) {
		
		boolean result = false;
		if(id!=null) {
			result = memberService.overlapCheck(id,0);
		}
		if(nickName!=null) {
			result = memberService.overlapCheck(nickName,1); 
		}
		return result;
	}
	
	@RequestMapping(value = "/test")
	public String test(){
		return "/member/signupFormByKakao.mn";
	}
}
