package project.spring.adminEvent.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import project.spring.adminEvent.service.AdminEventService;
import project.spring.adminEvent.vo.AdminEventVO;

@Controller
@RequestMapping("/admin/event")
public class AdminEventController {
		@Autowired
		private AdminEventService adminEventService = null;
		
		@RequestMapping("/insertEvent")
		public String eventList(Model model)throws SQLException{
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String evToday = sdf.format(Calendar.getInstance().getTime());
			model.addAttribute("evToday", evToday);
			
			return "admin/event/insertEvent.mn";
		}
		
		@RequestMapping("/insertEventPro")
		public String insertEvent(AdminEventVO vo, Model model)throws SQLException{
			System.out.println("========================");
			System.out.println("이벤트 네임" +  vo.getEventName());
			System.out.println("이벤트코드" + vo.getCode());
			System.out.println("이벤트 콘텐트" + vo.getContent());
			System.out.println("========================");
			
			int result = adminEventService.insertItem(vo);
			
			System.out.println("결과!!!!!" + result);
			
			// 시작 날짜 보내기
			
			
			
			return "redirect:/admin/memberList";
		}
		
		
		// eventList 보기 페이지
		@RequestMapping("/eventList")
		public String eventView()throws SQLException{
			
			
			
			return "admin/event/eventList";
		}
}
