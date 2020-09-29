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
	public List searchItem() {
		// TODO Auto-generated method stub
		return null;
	}

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
	public int myArticleCount(String memId) {
		int count = myActDAO.myArticleCount(memId);
		
		return count;
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

}
