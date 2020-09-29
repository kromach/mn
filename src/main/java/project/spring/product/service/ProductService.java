package project.spring.product.service;

import java.sql.SQLException;
import java.util.List;

import project.spring.beans.CommonInterface;
import project.spring.product.vo.ProductVo;

public interface ProductService extends CommonInterface {
	
	// 판매할 상품목록수 불러오기
	public int getproductcount() throws SQLException; 
	// 게시글 가져오기 
	public List getproduct() throws SQLException;
	// 게시글 한개 정보 가져오기
	public ProductVo getproductinfo() throws SQLException;

}
