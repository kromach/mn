package project.spring.beans;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadBean {

	@RequestMapping("uploadForm.git")
	public String uploadForm() {
		return "Spring03/uploadForm";
	}

	@RequestMapping("uploadPro.git")
	public String uploadPro(String writer,
			MultipartHttpServletRequest mpRequest,
			Model model
			) {
		// img Tag >> path Making

		MultipartFile mf = null;
		String path = null;

		try {
			mf = mpRequest.getFile("img");
			path = mpRequest
					.getRealPath("resources");
			//fileName seq처리
			String orgName = mf.getOriginalFilename();
			String imgName = orgName.substring(0,orgName.lastIndexOf('.'));
			System.out.println(imgName);
			String ext = orgName.substring(orgName.lastIndexOf('.'));
			Long date = System.currentTimeMillis();
			String newName = imgName + date + ext;
			System.out.println("newName="+newName);
			String imPath = path + "\\"	+ newName;
			File copyFile = new File(imPath);
			mf.transferTo(copyFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("imgName", mf.getOriginalFilename());
		return "Spring03/uploadPro";
	}
	
	// 다운로드 맵핑
	@RequestMapping("download.git")
	public ModelAndView down() {
		File f = new File("E:\\yskim\\web\\"
				+ ".metadata\\.plugins\\"
				+ "org.eclipse.wst.server.core\\"
				+ "tmp1\\wtpwebapps\\Spring\\"
				+ "resources\\1.jpg");
		ModelAndView mv = new ModelAndView
				("fileDown","downloadFile",f);
				//bean,param,data
		return mv;
	}
	
}
