package project.spring.adminEvent.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/event")
public class EventInsert {

	
	@RequestMapping("/insertEvent")
	public String eventList()throws SQLException{
		
		return "admin/event/insertEvent.mn";
	}
	
}
