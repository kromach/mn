package project.spring.member.controller;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import project.spring.member.service.MemberServiceImpl;
import project.spring.member.vo.MemberDTO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
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
	public String login(HttpServletRequest reqest) {
		String returnUrl = "/";
		HttpSession session =  reqest.getSession();
		if(session.getAttribute("memId")==null) {
			reqest.setAttribute("status", "salse");
			returnUrl = "/member/loginForm.mn";
		}else{
			System.out.println("로그인이 된 상태입니다.");
		}
		return returnUrl;
	}
	
	
	
}
