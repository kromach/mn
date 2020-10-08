package project.spring.article.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import project.spring.admin.service.AdminServiceImpl;
import project.spring.admin.vo.AdminVO;
import project.spring.article.service.ArticleServiceImpl;
import project.spring.article.vo.ArticleDTO;
import project.spring.beans.PageVO;
import project.spring.beans.Pager;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
		@Autowired
		private ArticleServiceImpl articleService = null;
	
		@RequestMapping
		public String index(){
			return "article/articleList.mn";
		}
		
		@RequestMapping("/writeForm")
		public String indexTest(){
			return "article/writeForm.mn";
		}	
		
		@RequestMapping("/drinkSearch")
		@ResponseBody
		public List drinkSearch(
				@RequestParam(value = "input", required = false) String input) {
			System.out.println("drink Ajax search Input : "+input);
			List list = null;
			if(input !=null && !input.equals("")) {
				list = articleService.getDrinkSearch(input);
			}
			return list;
		}
		@RequestMapping("/writePro")
		public String writePro(ArticleDTO dto)  throws IOException, FileUploadException {
			
			System.out.println(dto);
			return "article/articleList.mn";
		}
		
		@RequestMapping("/writeuploader")
		public void writeuploader(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
			// 파일정보 꺼내기
			MultipartFile mf = null;
			PrintWriter printWriter = null;
			// 인코딩
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");		
			String imgPath = null;
			//String returnStr = "";
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
				String path = request.getRealPath("resources/img/article") + File.separator;
//				System.out.println("req :" + request.getRealPath("resources/img/upload"));
				System.out.println(path + newName);
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
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(printWriter != null) try { printWriter.close(); } catch(Exception e) { e.printStackTrace();}
			}
		}
	}	
