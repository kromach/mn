package project.spring.adminEvent.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.spring.adminEvent.service.AdminEventService;
import project.spring.adminEvent.vo.AdminEventVO;
import project.spring.beans.PageVO;
import project.spring.beans.Pager;

@Controller
@RequestMapping("/admin/event")
public class AdminEventController {
		@Autowired
		private AdminEventService adminEventService = null;
		
		@RequestMapping("/insertEvent")
		public String eventList(Model model, Locale locale)throws SQLException{
			
			
			return "admin/event/insertEvent.mn";
		}
		
		@RequestMapping("/insertEventPro")
		public String insertEvent(AdminEventVO vo, Model model, HttpServletRequest request, HttpServletResponse response)throws SQLException{
			System.out.println("========================");
			System.out.println("이벤트 네임" +  vo.getEventName());
			System.out.println("이벤트코드" + vo.getCode());
			System.out.println("이벤트 콘텐트" + vo.getContent());
			System.out.println("========================");
			
			
			vo.setEvStart(vo.getEvStart().replace("-", ""));
			vo.setEvEnd(vo.getEvEnd().replace("-", ""));
			
			
			System.out.println("check---------------------------------------------------");

			System.out.println(vo.getEvStart());
			System.out.println(request.getAttribute("tarea"));
			System.out.println(request.getAttribute("title"));
			
			
			
			
			int result = adminEventService.insertItem(vo);
			
			System.out.println("결과!!!!!" + result);
			
			// 시작 날짜 보내기
			
			
			
			return "redirect:/admin/memberList.mn";
		}
		
		
		// eventList 보기 페이지
		@RequestMapping("/eventList")
		public String eventView(String pageNum, Model model)throws SQLException{
			
			if(pageNum == null) {
				pageNum = "1";
			}
			
			// 이벤트 글 가져오기
			int count = adminEventService.eventCount();
			
			List eventList = null;
			Pager pager = new Pager();
			PageVO pageVo = pager.pager(pageNum, count);
			
			if(count > 0) {
				eventList = adminEventService.eventList(pageVo.getStartRow(), pageVo.getEndRow());
			}
			
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("eventList", eventList);
			model.addAttribute("count", count);
			model.addAttribute("pageVO", pageVo);
			int number = count-(pageVo.getCurrPage()-1)*pageVo.getPageSize();
			model.addAttribute("number", number);

			System.out.println("===========================");
			System.out.println("number ;           " + number);
			System.out.println("count :              " + count);

			return "admin/event/eventList.mn";
		}
		

}
