package project.spring.adminEvent.service;

import java.sql.SQLException;
import java.util.List;

import project.spring.adminEvent.vo.AdminEventVO;
import project.spring.beans.CommonInterface;

public interface AdminEventService extends CommonInterface {

	public int eventCount() throws SQLException;

	public List eventList(int start, int end)throws SQLException;
	
	public List getDrinkSearch(String input) throws SQLException;
	
	public AdminEventVO eventInfo(String eventCode)throws SQLException;
	
	public void checkDate(String today)throws SQLException;
	
	public AdminEventVO chOpen(String eventCode)throws SQLException;
}
