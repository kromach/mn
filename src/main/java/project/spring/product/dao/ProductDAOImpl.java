package project.spring.product.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		System.out.println("dao count");
		System.out.println(count);
		return count;
	}

	@Override
	public List getproduct() throws SQLException {
		List productlist = sqlSession.selectList("product.getproduct");
		
		return productlist;
	}

	@Override
	public ProductVo getproductinfo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
