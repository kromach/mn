package project.spring.drink.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DrinkDAOImpl implements DrinkDAO {

	private final SqlSessionTemplate sqlSession;
	
	@Autowired
	DrinkDAOImpl(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	@Override
	public List<HashMap> selectSmallCategoryList(String bigCategoryCode) throws Exception {
		return null;
	}

}
