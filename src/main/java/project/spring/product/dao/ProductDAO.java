package project.spring.product.dao;

import java.sql.SQLException;
import java.util.List;

import project.spring.beans.CommonInterface;

public interface ProductDAO extends CommonInterface {
	
	// 판매할 상품목록수 불러오기
	public int getproductcount() throws SQLException; 
	// 게시글 가져오기 
	public List getproduct() throws SQLException;
	
	

}
