package project.spring.event.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import project.spring.event.service.EventServiceImpl;

@Controller
@RequestMapping("/event/")
public class EventController {

	@Autowired
	private EventServiceImpl eventService = null;
	
	@RequestMapping("index")
	public String eventIndex()throws SQLException{
		
		return "event/index.mn";
	}
}
