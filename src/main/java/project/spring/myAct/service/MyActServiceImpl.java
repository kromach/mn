package project.spring.myAct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.myAct.dao.MyActDAO;

@Service
public class MyActServiceImpl implements MyActService{
	
	@Autowired
	private MyActDAO myActDAO = null;

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
		int count = myActDAO.myArticleCount(memId);
		
		return count;
	}
	
	@Override
	public List getMyArticle(int startRow, int endRow, String memId) {
		List articleList = myActDAO.getMyArticles(startRow, endRow, memId);
		return articleList;
	}

	@Override
	public int likeArticleCount(String memId) {
		int count = myActDAO.myLikeArticleCount(memId);
		return 0;
	}

	@Override
	public List myLikeArticle(int startRow, int endRow, String memId) {
		List articleList = myActDAO.getMyLikeArticles(startRow, endRow, memId);
		return articleList;
	}

}
