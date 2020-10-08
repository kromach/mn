package project.spring.adminEvent.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.spring.adminEvent.service.AdminEventService;
import project.spring.adminEvent.vo.AdminEventVO;
import project.spring.article.service.ArticleServiceImpl;
import project.spring.beans.PageVO;
import project.spring.beans.Pager;

@Controller
@RequestMapping("/admin/event/")
public class AdminEventController {
		@Autowired
		private AdminEventService adminEventService = null;
		@Autowired
		private ArticleServiceImpl articleService = null;
		
		@RequestMapping("insertEvent")
		public String eventList(Model model, Locale locale)throws SQLException{
			
			
			return "admin/event/insertEvent.mn";
		}
		
		// 이벤트 처리 페이지
		@RequestMapping("insertEventPro")
		public String insertEvent(AdminEventVO vo, Model model, HttpServletRequest request, HttpServletResponse response)throws SQLException{
			System.out.println("========================");
			System.out.println("vo뭘까??   : ");
			System.out.println("========================");
			
			
			vo.setEvStart(vo.getEvStart().replace("-", ""));
			vo.setEvEnd(vo.getEvEnd().replace("-", ""));
			
			
			System.out.println("check---------------------------------------------------");

			System.out.println(vo.getEvStart());
			System.out.println("content : " + vo.getContent());
			System.out.println(request.getAttribute("title"));
			
			
			
			
			int result = adminEventService.insertItem(vo);
			
			System.out.println("결과!!!!!" + result);	
			
			
			return "redirect:/admin/memberList.mn";
		}
		
		
		// eventList 보기 페이지
		@RequestMapping("eventList")
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
		
		@RequestMapping("drinkSearch")
		@ResponseBody
		public List drinkSearch(@RequestParam(value = "input", required = false) String input) {
			List list = null;
			System.out.println(input);
			if(input !=  null && !input.equals("")) {
				list = articleService.getDrinkSearch(input);
			}
			return list;
		}

		
		// 에디터 파일 업로드
		@RequestMapping(value="eventImg", method=RequestMethod.POST)
		public void eventImgUpload(MultipartHttpServletRequest request, HttpServletResponse response)throws IOException, FileUploadException {
			System.out.println("확인 체크!!!!");
			
			// 파일종보
			MultipartFile mf = null;
			PrintWriter printWriter = null;
			
			// 인코등
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");		
			
			String imgPath = null;
			try {
				mf = request.getFile("upload");
				
				// 이미지 이름 중복처리 
				String orgName = mf.getOriginalFilename();
				
				// 파일의 이름만 추출
				String imgName = orgName.substring(0, orgName.lastIndexOf("."));
				// System.out.println(imgName);
				
				// 파일의 확장자만 추출
				String ext = orgName.substring(orgName.lastIndexOf("."));
				// System.out.println(ext);
				
				// 파일명 중복을 방지하기 위해 지금 시간을 밀리초로 받아와 파일명에 추가
				long cur = System.currentTimeMillis();
				String newName = imgName + cur + ext;  //원본 이름 + 현재시각(millis) + 확장자
				// System.out.println(newName);

				//파일 기본경로
				String root = request.getContextPath() + "/resources";
							
				//파일 기본경로 _ 상세경로
				String path = request.getRealPath("resources/img/upload") + File.separator;
//				System.out.println("req :" + request.getRealPath("resources/img/upload"));
				// System.out.println(path + newName);
				File file = new File(path);
				
				//디렉토리 존재하지 않을경우 디렉토리 생성
				if(!file.exists()) {
					file.mkdirs();
				}
							
				File copyFile = new File(path + newName); // 새로운 이미지 경로로 업로드 한 파일 복사 생성
				
				mf.transferTo(copyFile); // 지정된 경로로 파일 저장

				// 경로, 파일명 리턴
				//return3 = "&bNewLine=true&sFileName=" + orgName + "&sFileURL=" + root + "/img/upload/" + newName;
				   
				String callback = request.getParameter("CKEditorFuncNum");
				printWriter = response.getWriter();

				// 업로드시 메시지 출력
				printWriter.println("<script type='text/javascript'>"
				     + "window.parent.CKEDITOR.tools.callFunction("
				     + callback+",'"+ root + "/img/upload/" + newName +"','이미지를 업로드하였습니다.')"
				     +"</script>");
				
				printWriter.flush();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(printWriter != null)try {printWriter.close();}catch(Exception e) {e.printStackTrace();}
			}
		
		}
}
