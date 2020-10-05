package project.spring.product.service;

import java.sql.SQLException;
import java.util.List;

import project.spring.beans.CommonInterface;
import project.spring.product.vo.OrderVo;
import project.spring.product.vo.ProductVo;

public interface ProductService extends CommonInterface {
	
	// 판매할 상품목록수 불러오기
	public int getproductcount() throws SQLException; 
	// 게시글 가져오기 
	public List getproduct() throws SQLException;
	// 게시글 한개 정보 가져오기
	public ProductVo getproductinfo() throws SQLException;
	// 주류 코드로 주류 정보 가져오기
	public ProductVo getproductinfo(String prcode) throws SQLException;
	// 구매자 물품구매 수 불러오기
	public int myordercount(String id) throws SQLException;
	// 사용자 상품 구매 목록 불러오기
	public List myorderlist(String id) throws SQLException;
	// 구매자 상품목록 디테일 
	public OrderVo myorderinfo(String id, String prcode) throws SQLException;

}
