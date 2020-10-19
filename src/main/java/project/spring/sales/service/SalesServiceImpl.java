package project.spring.sales.service;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	public List productModify(String pageNum) {
		List list = null;
		list = salesDAO.productModify(pageNum);
				
		return list;
	}

	@Override
	public List getCategory() {
		List list = salesDAO.getCategory();
		return list;
	}

	@Override
	public String makeprCode(ProductInfoDTO productDTO) {
		String prCode = salesDAO.makeprCode(productDTO);
		return prCode;
	}
	

	@Override
	public String insertProductImg(MultipartHttpServletRequest request) {
	 	
		// 파일정보 꺼내기
		MultipartFile mf = null;
				
		String imgPath = null;

		try {
			mf = request.getFile("primage");
			imgPath = request.getRealPath("/img/product");
			System.out.println("path : " + imgPath);
			
			String orgName = mf.getOriginalFilename();
			String imgName = orgName.substring(0,orgName.lastIndexOf('.'));
			
			String ext = orgName.substring(orgName.lastIndexOf('.'));
			Long date = System.currentTimeMillis();
			String newName = imgName + date + ext;
			System.out.println("newName  :       " + newName);
			String newImgPath = imgPath + "\\" + newName;
			File copyFile = new File(newImgPath);
			mf.transferTo(copyFile);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		return imgPath;
	}

	@Override
	public int insertProduct(ProductInfoDTO productDTO) {
		int count = 0;
		count = salesDAO.intsertProduct(productDTO);
		return count;
	}


}
