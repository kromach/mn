package project.spring.product.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.product.dao.ProductDAOImpl;
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
		System.out.println("service count");
		System.out.println(count);
		return count;
	}

	@Override
	public List getproduct() throws SQLException {
		List productList = productDAO.getproduct();
		
		
		return productList;
	}

	@Override
	public ProductVo getproductinfo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
