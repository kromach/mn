package project.spring.myAct.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyActDAOImpl implements MyActDAO{

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
	public int myArticleCount(String memId) {
		int count = sqlSession.selectOne("myAct.myArticleCount", memId);
		
		return count;
	}
	
	@Override
	public List getMyArticles(int startRow, int endRow, String memId) {
		HashMap map = new HashMap();
		map.put("start", startRow);
		map.put("end", endRow);
		map.put("memId", memId);
		
		List list = sqlSession.selectList("myAct.selectAll", map);
		
		return list;
	}

	@Override
	public int myLikeArticleCount(String memId) {
		int count = sqlSession.selectOne("myAct.myLikeArticleCount", memId);
		return count;
	}

	@Override
	public List getMyLikeArticles(int startRow, int endRow, String memId) {
		HashMap map = new HashMap();
		map.put("start", startRow);
		map.put("end", endRow);
		map.put("memId", memId);
		
		List list = sqlSession.selectList("myAct.selectLikeArticleAll", map);
		
		return list;
	}
}
