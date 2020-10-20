package project.spring.sales.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.spring.beans.CommonInterface;
import project.spring.product.vo.ProductVo;
import project.spring.sales.vo.ProductInfoDTO;

public interface SalesService extends CommonInterface{

	public int salesCount(String memId);
	public List productorList(String memId, int startRow, int endRow);
	public String userIdCh(String memId);
	public List getCategory();
	public String makeprCode(ProductInfoDTO productDTO);
	public String insertProductImg(MultipartHttpServletRequest request);
	public int insertProduct(ProductInfoDTO productDTO);
	public ProductVo getDatail(String prCode);

}
