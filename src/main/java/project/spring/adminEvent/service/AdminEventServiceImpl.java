package project.spring.adminEvent.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		System.out.println("service 진입 확인");
		int result = adminEventDAO.insertItem(vo);
		return result;
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

		int result = adminEventDAO.updateItem(obj);
		return result;
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
	
	public int eventCount(Map schMap)throws SQLException{
		
		int count = adminEventDAO.eventCount(schMap);
		return count;
	}
	

	// 이벤트 가져오기
	@Override
	public List eventList(int start, int end) throws SQLException {
		List eventList = adminEventDAO.eventList(start, end);
		return eventList;
	}
	
	public List eventList(int start, int end, Map schMap)throws SQLException{
		List eventList = adminEventDAO.eventList(start, end, schMap);
		
		return eventList;
	}

	
	// 검색해서 가져오기
	@Override
	public List getDrinkSearch(String input) throws SQLException {

		List list = null;
		list = adminEventDAO.getDrinkSearch(input);
		return list;
	}

	// 이벤트 하나 가져오기
	@Override
	public AdminEventVO eventInfo(String eventCode) throws SQLException {

		AdminEventVO vo = adminEventDAO.getEventInfo(eventCode);
		return vo;
	}

	@Override
	public void checkDate(String today) throws SQLException {

		adminEventDAO.checkDate(today);
	}


	

}
