package project.spring.product.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.product.dao.ProductDAOImpl;
import project.spring.product.vo.OrderVo;
import project.spring.product.vo.ProductVo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAOImpl productDAO = null;
	
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
	public int getproductcount() throws SQLException {
		
		int count = productDAO.getproductcount();
		return count;
	}
	
	public int getproductcount(Map map) throws SQLException {
		
		int count = productDAO.getproductcount(map);
		return count;
	}

	@Override
	public List getproduct() throws SQLException {
		List productList = productDAO.getproduct();
		
		
		return productList;
	}
	
	public List getproduct(Map map) throws SQLException {
		List productList = productDAO.getproduct(map);
		
		
		return productList;
	}

	@Override
	public ProductVo getproductinfo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductVo getproductinfo(String prcode) throws SQLException {
		ProductVo vo = productDAO.getproductinfo(prcode);
		return vo;
	}

	@Override
	public int myordercount(String id) throws SQLException {
		int myordercount = productDAO.myordercount(id);
		return myordercount;
	}

	@Override
	public List myorderlist(String id) throws SQLException {
		List myorderlist = productDAO.myorderlist(id);
		return myorderlist;
	}

	@Override
	public OrderVo myorderinfo(String id, String prcode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertorderinfo(OrderVo ordervo) throws SQLException {
		productDAO.insertorderinfo(ordervo);
		
	}

	@Override
	public OrderVo orderdetail(String orcode) throws SQLException {
		OrderVo orinfo = productDAO.orderdetail(orcode);
		return orinfo;
	}

	@Override
	public List getorderlist(String id,int startrow ,int endrow) throws SQLException {
		
		List orderlist = productDAO.getorderlist(id,startrow,endrow);
		
		return orderlist;
	}

	@Override
	public int getordercount(String id) throws SQLException {
		 
		int ordercount = productDAO.getordercount(id);
		
		return ordercount;
	}

}
