package project.spring.adminEvent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.adminEvent.dao.AdminEventDAOImpl;

@Service
public class AdminEventServiceImpl implements AdminEventService{

	@Autowired
	private AdminEventDAOImpl adminEventDAO = null;
	
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
	

}
