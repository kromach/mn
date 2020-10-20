package project.spring.sales.service;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.spring.product.vo.ProductVo;
import project.spring.sales.dao.SalesDAO;
import project.spring.sales.vo.ProductInfoDTO;

@Service
public class SalesServiceImpl implements SalesService{

	@Autowired
	private SalesDAO salesDAO = null;
	
	@Override
	public int insertItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertItem(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateItem(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteItem(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int salesCount(String memId) {
		int count = 0;
		count = salesDAO.salesCount(memId);
		return count;
	}

	@Override
	public List productorList(String memId, int start, int end) {
		List list = null;
		list = salesDAO.productorlist(memId, start, end); 
		return list;
	}

	@Override
	public String userIdCh(String memId) {
		String result = null;
		result = salesDAO.userIdch(memId);
		return result;
	}

	@Override
	public List getCategory() {
		List list = salesDAO.getCategory();
		return list;
	}

	@Override
	public String makeprCode(ProductVo productDTO) {
		String prCode = salesDAO.makeprCode(productDTO);
		return prCode;
	}
	

	@Override
	public String insertProductImg(MultipartHttpServletRequest request) {
	 	
		// 파일정보 꺼내기
		MultipartFile mf = null;
		String imgPath = null;

		try {
			mf = request.getFile("primage"); // null
			// 이미지 이름 중복처리 
			String orgName = mf.getOriginalFilename();
			// 파일명은 주류 코드로 변경
			String prCode = (String)request.getAttribute("prCode");
			// 파일의 확장자만 추출
			String ext = orgName.substring(orgName.lastIndexOf("."));
			// System.out.println(ext);
			String newName = prCode + ext;  // 주류코드 + 확장자
			System.out.println(newName);
			//파일 기본경로 _ 상세경로
			String path = request.getRealPath("resources/img/product") + File.separator;
	//						System.out.println("req :" + request.getRealPath("resources/img/upload"));
			// System.out.println(path + newName);
			File file = new File(path);
			//디렉토리 존재하지 않을경우 디렉토리 생성
			if(!file.exists()) {
				file.mkdirs();
			}
			imgPath = path + newName;
			File copyFile = new File(path + newName); // 새로운 이미지 경로로 업로드 한 파일 복사 생성
			mf.transferTo(copyFile); // 지정된 경로로 파일 저장
		}catch (Exception e) {
			e.printStackTrace();
		}
		return imgPath;
	}

	@Override
	public int insertProduct(ProductVo productDTO) {
		int count = 0;
		count = salesDAO.insertProduct(productDTO);
		return count;
	}

	@Override
	public ProductVo getDatail(String prCode) {
		System.out.println("왔냐??");
		ProductVo dto = salesDAO.getDatail(prCode);
		return dto;
	}

	@Override
	public int updateProduct(ProductVo dto) {
		int count = 0;
		count = salesDAO.updateProduct(dto);
		return count;
	}


}
