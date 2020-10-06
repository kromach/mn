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
	public List getMyArticles(String memId) {
	
		List list = sqlSession.selectList("myAct.selectAll", memId);
		
		return list;
	}

	@Override
	public int myLikeArticleCount(String memId) {
		int count = 0 ; 
		sqlSession.selectOne("myAct.myLikeArticleCount", memId);
		return count;
	}

	@Override
	public List myLikeArticle(String memId) {
		List list = sqlSession.selectList("myAct.myLikeArticle", memId);
		System.out.println(list.toString());
		return list;
	}

	@Override
	public int myLikeDrinkCount(String memId) {
		int count = sqlSession.selectOne("myAct.myLikeDrinkCount", memId);
		return count;
	}

	@Override
	public List myLikeDrink(String memId) {
		List list = sqlSession.selectList("myAct.myLikeDrink", memId);
		return list;
	}

	@Override
	public List getMyTitle(String memId) {
		List list = sqlSession.selectList("myAct.getMyTitle", memId);
		return list;
	}
	
	
	
}
