package project.spring.product.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.product.vo.OrderVo;
import project.spring.product.vo.ProductVo;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired 
	private SqlSessionTemplate sqlSession = null;

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
	public int updateItem(OrderVo ordervo) {
		int res = 0;
		
		if(ordervo.getDeliveryCompany()!=null && ordervo.getDeliveryNum()!=null) {
			 res =  sqlSession.insert("product.insertdel", ordervo);
		}else {
			
			 res = sqlSession.update("product.updateorder", ordervo);
			System.out.println("일반 구매 정보 저장!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
		
		return res;
	}
	public int updateItem(String orCode) {
		int res = sqlSession.update("product.delectorder", orCode);
		return res;
	}
	public int updateItem(HashMap map) {
		
		
		
		sqlSession.selectOne("product.changsta",map);
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
	public int getproductcount() throws SQLException {
		int count = sqlSession.selectOne("product.countAll");
		return count;
	}
	
	public int getproductcount(Map map) throws SQLException {
		
		
		int count = sqlSession.selectOne("product.schcountAll",map);
		return count;
	}

	@Override
	public List getproduct() throws SQLException {
		List productlist = sqlSession.selectList("product.getproduct");
		
		return productlist;
	}

	public List getproduct(Map map) throws SQLException {
		List productlist = sqlSession.selectList("product.schgetproduct",map);
		return productlist;
	}
	@Override
	public ProductVo getproductinfo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductVo getproductinfo(String prcode) throws SQLException {
		
		ProductVo vo =sqlSession.selectOne("product.getproductinfo",prcode);
		return vo;
	}

	@Override
	public int myordercount(String id) throws SQLException {
		int myordercount = sqlSession.selectOne("product.myordercount",id);
		System.out.println(myordercount);
		return myordercount;
	}

	@Override
	public List myorderlist(String id ,int i) throws SQLException {
		HashMap map =new HashMap();
		map.put("id", id);
		map.put("i", i);
		
		List myorderlist =sqlSession.selectList("product.myorderlist",map);
		return myorderlist;
	}

	@Override
	public OrderVo myorderinfo(String id, String prcode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@Override
	public void insertorderinfo(OrderVo ordervo) throws SQLException {
		
		HashMap hash=sqlSession.selectOne("product.sql");
		
		int i = ((BigDecimal)hash.get("NEXTVAL")).intValue(); 
		String a =(hash.get("'O'||(SELECT TO_CHAR(SYSDATE,'YYYYMMDD') FROM DUAL)|| ORDER_INFO_SEQ")).toString();
		String c = a.substring(a.lastIndexOf("=")+1);
		String b =c.substring(0,c.length()-1);
		
		ordervo.setOrSeq(i);
		ordervo.setOrCode(b);
		/*
		Iterator it = hash.keySet().iterator();  // 키값을 순서대로 담아준다
		while(it.hasNext()) {
			String key = (String)it.next(); //ㅋㅣ
			String value = p.getProperty(key);
		}
		*/
		sqlSession.update("product.updatecount",ordervo);
		sqlSession.update("product.insertorder",ordervo);
		
	}

	@Override
	public OrderVo orderdetail(String orcode) throws SQLException {
		OrderVo orinfo = sqlSession.selectOne("product.orderinfo",orcode);
		return orinfo;
	}

	@Override
	public List getorderlist(HashMap map) throws SQLException {
		List orderlist = sqlSession.selectList("product.orderlist",map);
		
		return orderlist;
	}

	@Override
	public int getordercount(HashMap map) throws SQLException {
		int ordercount = sqlSession.selectOne("product.selcount",map);
		System.out.println("ordercount : "+ ordercount);
		return ordercount;
	}

	@Override
	public int like(String prCode, String memId, String insertId) {
		HashMap map = new HashMap();
		map.put("prCode", prCode);
		map.put("memId", memId);
		int count = sqlSession.selectOne("product.alreadyLike", map);
		System.out.println("alreadyCountLike"+count);
		if(count == 0) {
			sqlSession.insert("product.like_log",map);
			sqlSession.update("product.like", prCode);
			//sqlSession.update("product.updateMyActGivenHeart",insertId);
			//sqlSession.update("product.updateMylikepr",insertId);
			return sqlSession.selectOne("product.likeReturn",prCode); 
		}
		return -1;
	}

	@Override
	public int unlike(String prCode, String memId, String insertId) {
		HashMap map = new HashMap();
		map.put("prCode", prCode);
		map.put("memId", memId);
		int count = sqlSession.selectOne("product.alreadyLike", map);
		if(count == 1) {
			sqlSession.delete("product.like_log_undo",map);
			sqlSession.update("product.like_undo", prCode);
			//sqlSession.update("product.updateMyActGivenHeart_undo",insertId);
			//sqlSession.update("product.updateMylikepr_undo",insertId);
			return sqlSession.selectOne("product.likeReturn",prCode); 
		}
		return -1;
	}



}
