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
	public String signUp(HttpServletRequest request) {
		String returnUrl = "redirect:/";
		HttpSession session =  request.getSession();
		if(session.getAttribute("memId")==null) {
			returnUrl = "/member/signStatus.mn";
		}else{
			System.out.println("로그인이 된 상태입니다.");
		}
		return returnUrl;
	}
	
	@RequestMapping("/signupUser")
	public String signUpUser(HttpServletRequest request) {
		String returnUrl = "redirect:/";
		HttpSession session =  request.getSession();
		if(session.getAttribute("memId")==null) {
			request.setAttribute("status", "user");
			returnUrl = "/member/signupForm.mn";
		}else{
			System.out.println("로그인이 된 상태입니다.");
		}
		return returnUrl;
	}
	@RequestMapping("/signupSales")
	public String signUpSalses(HttpServletRequest request) {
		String returnUrl = "redirect:/";
		HttpSession session =  request.getSession();
		if(session.getAttribute("memId")==null) {
			request.setAttribute("status", "sales");
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
		
		//출석+1
		memberService.attendent(dto.getId());	
		//My_Activity 등록
		memberService.insertMyAct(dto.getId());
		
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
		dto.setBirth(birth);
		dto.setUserKind("sales");
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
			dto.setLicenseImg(newName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(dto);
		System.out.println("memberInsertReulst="+ memberService.insertItem(dto));
		model.addAttribute("nickName", dto.getNickName());
		mpRequest.getSession().setAttribute("memId", dto.getId());
		mpRequest.getSession().setAttribute("memNickName", dto.getNickName());
		mpRequest.getSession().setAttribute("userKind", "sales");
		//출석+1
		memberService.attendent(dto.getId());	
		//My_Activity 등록
		memberService.insertMyAct(dto.getId());
		return "/member/signupResult.mn";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,Model model) {
		String returnUrl = "redirect:/";
		HttpSession session =  request.getSession();
		if(session.getAttribute("memId")==null) {
			request.setAttribute("restApikey", restApikey);
			request.setAttribute("callback_URL", callback_URL);
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
		
		String gender = "";
		if (kakao_account.get("gender") != null) {
			gender = kakao_account.get("gender").asText();
		}
		String birth = "";
		if (kakao_account.get("birthday") != null) {
			birth = kakao_account.get("birthday").asText();
		}
		String nickname = "";
		if (properties.path("nickname").toString() != null) {
			nickname = properties.path("nickname").toString().replaceAll("\"","");	
		}
		
		//String gender = kakao_account.get("gender").asText();
		//String birth = kakao_account.get("birthday").asText();
		//String nickname = properties.path("nickname").toString().replaceAll("\"","");	
		// if(gender==null)gender = "";
		// if(birth==null)birth ="";
		// if(nickname==null)nickname="";
		
		MemberDTO dto = new MemberDTO();
		
		dto.setId(id);
		dto.setNickName(nickname);
		dto.setName(nickname);
		
		int isNew = memberService.readItem(dto);
		System.out.println("KakaoIsNew"+isNew);
		int kakaoSignupResult = 0;
		if(isNew !=0) {
			//id가 없어서 첫회원가입이거나,
			//gender가 없거나 birth가없거나,nickname이 없으면 >> 회원가입
			request.setAttribute("kakaoMember", dto);
			return "/member/signupFormByKakao.mn";
		}
		
		//모두 있다면 user로 셋팅해서 Session에 값 넣어주기
		request.getSession().setAttribute("memId", id);
		request.getSession().setAttribute("memNickName", nickname);
		request.getSession().setAttribute("userKind", "user");
		redirectAttributes.addFlashAttribute("memberDTO", dto);
		//id O pw O
		request.setAttribute("result", 1);
		
		//출석+1
		memberService.attendent(dto.getId());	
		//My_Activity 등록
		memberService.insertMyAct(dto.getId());
		
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
		dto.setUserKind("user");
		//카카로 추가입력으로 넘어온 정보로 회원가입시키기 > 카카오는 user로만 가입
		memberService.insertItem(dto);
		request.getSession().setAttribute("memId", dto.getId());
		request.getSession().setAttribute("memNickName", dto.getNickName());
		request.getSession().setAttribute("userKind", "user");
		request.setAttribute("result", 1);
		request.setAttribute("nickName", dto.getNickName());
		//출석+1
		memberService.attendent(dto.getId());
		//My_Activity 등록
		memberService.insertMyAct(dto.getId());
		
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
			//출석+1
			memberService.attendent(model.getId());
			//Sessionsetting
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
		String birth = "";
		for(String birth_ : births) {
			birth += birth_;
		}
		dto.setBirth(birth);
		
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
		String birth = "";
		for(String birth_ : births) {
			birth += birth_;
		}
		dto.setBirth(birth);
		
		MemberDTO result = memberService.findPw(dto);
		model.addAttribute("memberDTO", result);
		model.addAttribute("result","pw");
		
		return "/member/findResult.mn";
	}
	//AJAX 중복검사
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
	//카카오 추가입력창 test
	@RequestMapping(value = "/test")
	public String test(){
		return "/member/signupFormByKakao.mn";
	}
}
