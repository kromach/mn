package project.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping("/signupPro")
	public String signUpPro(@ModelAttribute MemberDTO dto,MultipartRequest mr) {
		System.out.println(dto);
		memberService.insertItem(dto);
		return null;
	}
}
