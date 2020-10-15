package project.spring.event.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import project.spring.event.service.EventServiceImpl;

@Controller
@RequestMapping("/event/")
public class EventController {

	@Autowired
	private EventServiceImpl eventService = null;
	
	@RequestMapping("index")
	public String eventIndex(Model model)throws SQLException{
		
		int count = 0;
		List eventList = null;
		
		count = eventService.getEventCount();
		
		if(count > 0) {
			eventList = eventService.eventList();
			System.out.println("들어가기 직전!");
		}
		model.addAttribute("eventList", eventList);
		model.addAttribute("count", count);
		
		return "event/index.mn";
	}
}
