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
			System.out.println("evToday :  " + evToday);
			
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
			
			System.out.println("날짜확인 ::::   " + vo.getEvStart());
			System.out.println(vo.getEvStart().replace("/", ""));
			String day = vo.getEvStart().replace("/", "").substring(0, 2);
			System.out.println("day : " +  day);
			String month = vo.getEvStart().replace("/", "").substring(2, 4);
			System.out.println("month : " + month);
			String year = vo.getEvStart().replace("/", "").substring(4, 8);
			System.out.println("year : " + year);
			vo.setEvStart(year+month+day);
			System.out.println("날짜확인 111  :::         " + vo.getEvStart());
			
			day = vo.getEvEnd().replace("/", "").substring(0,2);
			month = vo.getEvEnd().replace("/", "").substring(2,4);
			year = vo.getEvEnd().replace("/", "").substring(4,8);
			vo.setEvEnd(year+month+day);

			
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
