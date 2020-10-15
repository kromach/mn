package project.spring.event.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.event.dao.EventDAOImpl;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventDAOImpl eventDAO = null;

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
	public int getEventCount() throws SQLException {
		int count = eventDAO.getEventCount();
		return count;
	}

	@Override
	public List eventList() throws SQLException {
		
		List eventList = eventDAO.eventList();
		return eventList;
		
	}

}
