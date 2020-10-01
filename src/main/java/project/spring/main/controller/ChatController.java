package project.spring.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import project.spring.kakao.vo.MemberDTO;

@Controller 
public class ChatController {
	
	@RequestMapping(value = "/chattingView", method = RequestMethod.GET)
	public ModelAndView chat(ModelAndView mv,HttpSession session) {
		System.out.println("chatController");
		mv.setViewName("/chat/chattingView.mn");
		mv.addObject("userId", session.getAttribute("memNickName"));
		return mv;
	}
}
