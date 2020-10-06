package project.spring.adminEvent.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import project.spring.admin.service.AdminServiceImpl;
import project.spring.adminEvent.service.AdminEventService;
import project.spring.adminEvent.vo.AdminEventVO;

@Controller
@RequestMapping("/admin/event")
public class EventInsert {

	@Autowired
	private AdminEventService adminEventService = null;
	
	@RequestMapping("/insertEvent")
	public String eventList()throws SQLException{
		
		return "admin/event/insertEvent.mn";
	}
	
	@RequestMapping("/insertEventPro")
	public String insertEvent(AdminEventVO vo)throws SQLException{
		System.out.println("========================");
		System.out.println("이벤트 네임" +  vo.getEventName());
		System.out.println("이벤트코드" + vo.getCode());
		System.out.println("이벤트 콘텐트" + vo.getContent());
		System.out.println("========================");
		
		int result = adminEventService.insertItem(vo);
		
		System.out.println("결과!!!!!" + result);
		
		return "redirect:/admin/memberList";
	}
	
}
