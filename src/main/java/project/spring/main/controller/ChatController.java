package project.spring.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import project.spring.kakao.vo.MemberDTO;

@Controller
@RequestMapping(value = "/chat", method = RequestMethod.GET)
public class ChatController {
	
	public ModelAndView chat(ModelAndView mv,HttpSession session) {
		
		mv.setViewName("/chat/chattingView");
	
		return mv;
	}

}
