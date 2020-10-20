package project.spring.product.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import project.spring.article.vo.ArticleDTO;
import project.spring.beans.CommonInterface;
import project.spring.product.vo.OrderVo;
import project.spring.product.vo.ProductVo;

public interface ProductDAO extends CommonInterface {
	
	
	
	// 판매할 상품목록수 불러오기
	public int getproductcount() throws SQLException; 
	// 게시글 가져오기 
	public List getproduct(int idx) throws SQLException;
	// 게시글 한개 정보 가져오기
	public ProductVo getproductinfo() throws SQLException;
	// 주류 코드로 주류 정보가져오기
	public ProductVo getproductinfo(String prcode) throws SQLException;
	// 구매자 물품구매 수 불러오기
	public int myordercount(String id) throws SQLException;
	// 사용자 상품 구매 목록 불러오기
	public List myorderlist(String id, int i) throws SQLException;
	// 구매자 상품목록 디테일 
	public OrderVo myorderinfo(String id, String prcode) throws SQLException;
	// 상품 구매정보 저장
	public void insertorderinfo(OrderVo ordervo) throws SQLException;
	// 구매한 상품 코드로 구매정보 상세 보기
	public OrderVo orderdetail(String orcode) throws SQLException;
	// 판매자 아이디로 등록한 물품 구매한 목록 가져오는 메서드
	public List getorderlist(HashMap map) throws SQLException;
	// 주문들어온 수 카운트 
	public int getordercount (HashMap map) throws SQLException;
	// 판매상품 좋아요
	public int like(String prCode, String memId,String insertId);
	public int unlike(String prCode, String memId,String insertId);
	// 판매리뷰 수 가져오기
	public int getarticlecount(String prcode) throws SQLException; 
	// 판매리뷰 가져오기
	public List getarticle(String prcode) throws SQLException;
	// 재고수량 가져오기
	public int getamount(String prcode) throws SQLException;
	// 리뷰 상세
	public ArticleDTO getarticldetail(int bnIdx) throws SQLException;
	

}
