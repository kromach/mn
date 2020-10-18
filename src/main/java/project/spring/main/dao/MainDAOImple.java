package project.spring.main.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.drink.vo.DrinkVO;
import project.spring.event.vo.EventVO;
import project.spring.product.vo.ProductVo;

@Repository
public class MainDAOImple implements MainDAO{
	@Autowired
	private SqlSessionTemplate sqlSession = null;
	
	@Override
	public int insertItem() {
		return 0;
	}
	@Override
	public int updateItem() {
		return 0;
	}
	@Override
	public int deleteItem() {
		return 0;
	}
	@Override
	public int insertItem(Object obj) {
		return 0;
	}
	@Override
	public int updateItem(Object obj) {
		return 0;
	}
	@Override
	public int deleteItem(Object obj) {
		return 0;
	}
	@Override
	public List getProductInitial() {
		List<ProductVo> list = sqlSession.selectList("main.getProductInitial");
		return list;
	}
	@Override
	public List<DrinkVO> getDrinkInitial() {
		List<DrinkVO> list = sqlSession.selectList("main.getDrinkInitial");
		return list;
	}
	@Override
	public List<EventVO> getEventInitial() {
		List<EventVO> list = sqlSession.selectList("main.getEventInitial");
		return list;
	}
}
