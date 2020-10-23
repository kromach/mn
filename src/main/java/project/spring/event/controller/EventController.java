package project.spring.event.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.spring.adminEvent.service.AdminEventService;
import project.spring.adminEvent.service.AdminEventServiceImpl;
import project.spring.event.service.EventService;
import project.spring.event.service.EventServiceImpl;
import project.spring.event.vo.EventVO;

@Controller
@RequestMapping("/event/")
public class EventController {

	@Autowired
	private AdminEventService adminEventService = null;
	@Autowired
	private EventService eventService = null;
	
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
			Collections.shuffle(eventList);

		}else {
			count = 0;
		}

		model.addAttribute("eventList", eventList);
		model.addAttribute("count", count);
		
		return "event/index.mn";
	}
	
	@RequestMapping("detail")
	public String deatil(HttpServletRequest request, Model model)throws SQLException {
		
		if(request.getParameter("eventCode") == null) {
			return "redirect:/event/eventList";
		}
		else {
			String eventCode = request.getParameter("eventCode");
			HttpSession session = request.getSession();
			String memId = null;
			int idx = 0;
			if(session.getAttribute("memId") != null && !session.getAttribute("memId").equals("")) {
				memId = (String)session.getAttribute("memId");
				idx = eventService.checkJoinEvent(eventCode, memId);
			}
			System.out.println("idx : " + idx);
			EventVO vo =  eventService.getEvent(eventCode);
			model.addAttribute("vo", vo);
			model.addAttribute("memId", memId);
			model.addAttribute("idx", idx);
		}
		
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
	
	@RequestMapping("joinEvent")
	@ResponseBody
	public void joinEventSs(@RequestParam String eventCode, HttpServletRequest request)throws SQLException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		System.out.println("eventCode :" + eventCode);
		System.out.println("id : " + id);
		
		eventService.requestEvent(eventCode, id);
		
	}
}
