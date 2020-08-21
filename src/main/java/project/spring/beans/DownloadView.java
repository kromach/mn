package project.spring.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView{
	public DownloadView() {
		//header Setting
		setContentType("application/download; charset=UTF-8");
	}
	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		
	// 다운을 요청할때 다운시킬 파일 처리
	// 파일 보낼때 파일의 정보를 다 같이 보냄
	// f >> model >> get()	
	File file = (File) model.get("downloadFile"); 
	response.setContentType(getContentType());
	response.setContentLength((int)file.length());
	//UrlEncoder = Java에서 EncodingSetting
	String fileName = URLEncoder.encode(file.getName(),"UTF-8");
	response.setHeader("Content-Disposition", "attachement;filename=\""+fileName+"\";");
											//attachement;filename="fileName";
	response.setHeader("Content-Transfer-Encoding", "binary");
	OutputStream out = response.getOutputStream();
	FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {if(fis!=null) try {fis.close();} catch (Exception e2) {e2.printStackTrace();}
			out.flush();
		}
	}
}
