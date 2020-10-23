package project.spring.event.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.event.dao.EventDAO;
import project.spring.event.dao.EventDAOImpl;
import project.spring.event.vo.EventVO;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventDAO eventDAO = null;

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
	public List eventList(int idx) throws SQLException {
		
		List eventList = eventDAO.eventList(idx);
		return eventList;
		
	}

	@Override
	public EventVO getEvent(String eventCode) throws SQLException {
		EventVO vo = eventDAO.getEvent(eventCode);
		return vo;
	}

	@Override
	public void requestEvent(String eventCode, String id) throws SQLException {
		eventDAO.requestEvent(eventCode, id);
	}

	@Override
	public int checkJoinEvent(String eventCode, String id) throws SQLException {
		System.out.println("check event : ");
		
		int idx = eventDAO.checkJoinEvent(eventCode, id);
		System.out.println(idx);
		return idx;
	}

}
