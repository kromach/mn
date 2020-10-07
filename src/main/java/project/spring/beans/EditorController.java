package project.spring.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/editor/")
public class EditorController implements ServletContextAware {
	
	private static final Logger logger = LoggerFactory.getLogger(EditorController.class);
	private ServletContext context;
	
	// 예시 입력폼
	@RequestMapping(value = "editor", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "editor/editor.mn";
	}
	
	// 예시 서브밋 결과 페이지
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public ModelAndView write(HttpServletRequest request) throws IOException, FileUploadException {

		String title = request.getParameter("title");
		String tarea = request.getParameter("tarea");

		ModelAndView model = new ModelAndView("editor/write.mn");
		model.addObject("title", title);
		model.addObject("tarea", tarea);
		
		return model;
	}
	
	//context 값을 받아오기 위해 implement 처리 
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}
	
	// 싱글파일 업로드
	@RequestMapping(value="file_uploader", method=RequestMethod.POST)
	public void file_uploader(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
		
		// 파일정보 꺼내기
		MultipartFile mf = null;
		
		String imgPath = null;
		//String returnStr = "";
		
		String return1 = request.getParameter("callback"); 
		String return2 = "?callback_func=" + request.getParameter("callback_func"); 
		String return3 = "";
		
		System.out.println("return1" + return1);
		System.out.println("return2" + return2);
		
		try {
			mf = request.getFile("Filedata");
			
			// 파일 저장 위치 
			//String path = request.getRealPath("save");
			
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
			String defaultPath = context.getRealPath("/resources");
						
			//파일 기본경로 _ 상세경로
			String path = defaultPath + File.separator + "img/upload" + File.separator;
			
			File file = new File(path);
			
			//디렉토리 존재하지 않을경우 디렉토리 생성
			if(!file.exists()) {
				file.mkdirs();
			}
						
			File copyFile = new File(path + newName); // 새로운 이미지 경로로 업로드 한 파일 복사 생성
			
			mf.transferTo(copyFile); // 지정된 경로로 파일 저장		} catch (Exception e) {

			// 경로, 파일명 리턴
			return3 = "&bNewLine=true&sFileName=" + orgName + "&sFileURL=" + root + "/img/upload/" + newName;
			
		} catch (Exception e) {
			e.printStackTrace();
			return3 += "&errstr=error";
		}

		//PrintWriter out = response.getWriter();
		//out.println(returnStr);		
		response.sendRedirect(return1+return2+return3);
	}
	
	// 멀티파일업로드 (구현은 했지만 안씀)
	@RequestMapping(value = "file_uploader_html5", method = RequestMethod.POST)
	public void file_uploader_html5(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String sFileInfo = "";
		
		//파일명 - 싱글파일업로드와 다르게 멀티파일업로드는 HEADER로 넘어옴 
		String name = request.getHeader("file-name");
		
		String ext = name.substring(name.lastIndexOf(".")+1);
		
		//파일 기본경로
		String root = request.getContextPath() + "/resources";
		String defaultPath = context.getRealPath("/resources");
		
		//파일 기본경로 _ 상세경로
		String path = defaultPath + File.separator + "img/upload" + File.separator;
		
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String realname = UUID.randomUUID().toString() + "." + ext;
		
		InputStream is = request.getInputStream();
		
		String storedFileName = path + realname;
		
		OutputStream os = new FileOutputStream(storedFileName);
		
		int numRead;
		
		//파일쓰기
		byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
		
		while((numRead = is.read(b, 0, b.length)) != -1){
			os.write(b,0,numRead);
		}
		
		if(is != null) {
			is.close();
		}
		
		os.flush();
		os.close();

		sFileInfo += "&bNewLine=true&sFileName=" + name + "&sFileURL=" + root + "/img/upload/" + realname;
		PrintWriter out = response.getWriter();
		out.println(sFileInfo);
	}
}