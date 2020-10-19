package project.spring.event.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.spring.adminEvent.service.AdminEventServiceImpl;
import project.spring.event.service.EventServiceImpl;

@Controller
@RequestMapping("/event/")
public class EventController {

	@Autowired
	private AdminEventServiceImpl adminEventService = null;
	@Autowired
	private EventServiceImpl eventService = null;
	
	@RequestMapping("index")
	public String eventIndex(Model model)throws SQLException{
		
		int count = 0;
		List eventList = null;
		
		count = eventService.getEventCount();
		
		if(count > 0) {
			
			// 기간 지나면 자동 변경
			SimpleDateFormat spd = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String today = spd.format(date);
			adminEventService.checkDate(today);
			
			eventList = eventService.eventList(0);
			System.out.println("들어가기 직전!");	
		}
		Collections.shuffle(eventList);

		model.addAttribute("eventList", eventList);
		model.addAttribute("count", count);
		
		return "event/index.mn";
	}
	
	@RequestMapping("detail")
	public String deatil(HttpServletRequest request, Model model) {
		
		String eventCode = request.getParameter("eventCode");
		
		model.addAttribute("eventCode", eventCode);
		
		
		return "event/detail.mn";
	}
	
	@RequestMapping("/reload")
	@ResponseBody
	public List reload(@RequestParam(name="index") int index)throws SQLException {
		
		List eventList = eventService.eventList(index + 1);
		Collections.shuffle(eventList);
		if(eventList.size() > 0) {
			return eventList;
		}else {
			return null;
		}
	}
}
