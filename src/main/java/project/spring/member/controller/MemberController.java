package project.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	
	@RequestMapping("/signup")
	public String signUpMember(HttpServletRequest reqest) {
		String returnUrl = "main";
		HttpSession session =  reqest.getSession();
		if(session.getAttribute("memId")==null) {
			returnUrl = "/member/signupForm.mn";
		}else{
			returnUrl = "/";
			System.out.println("로그인이 된 상태입니다.");
		}
		return returnUrl;
	}
	@RequestMapping("/signupPro")
	public String signUpPro() {
		
		return null;
	}
	
}
