package project.spring.sales.service;

import java.util.HashMap;
import java.util.List;

import project.spring.beans.CommonInterface;
import project.spring.sales.vo.ProductInfoDTO;

public interface SalesService extends CommonInterface{

	public int salesCount(String memId);
	public List productorList(String memId, int startRow, int endRow);
	public String userIdCh(String memId);
	public List productModify(String pageNum);
	public List getCategory();
	public String makeprCode(ProductInfoDTO productDTO);

}
