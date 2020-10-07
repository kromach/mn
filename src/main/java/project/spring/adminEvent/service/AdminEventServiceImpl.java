package project.spring.adminEvent.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.adminEvent.dao.AdminEventDAOImpl;
import project.spring.adminEvent.vo.AdminEventVO;

@Service
public class AdminEventServiceImpl implements AdminEventService{

	@Autowired
	private AdminEventDAOImpl adminEventDAO = null;
	
	@Override
	public int insertItem() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int insertItem(AdminEventVO vo) {
		adminEventDAO.insertItem(vo);
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

	// 이벤트 개수 가져오기
	@Override
	public int eventCount() throws SQLException {
		
		int count = adminEventDAO.eventCount();
		
		return count;
	}

	// 이벤트 가져오기
	@Override
	public List eventList(int start, int end) throws SQLException {
		List eventList = adminEventDAO.eventList(start, end);
		return eventList;
	}


	

}
