package project.spring.adminEvent.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import project.spring.adminEvent.vo.AdminEventVO;
import project.spring.beans.CommonInterface;

public interface AdminEventService extends CommonInterface {

	public int insertItem(AdminEventVO vo)throws SQLException;
	
	public int eventCount() throws SQLException;
	
	public int eventCount(Map<String, String> schMap) throws SQLException;

	public List eventList(int start, int end)throws SQLException;
	
	public List eventList(int start, int end, Map<String, String> schMap)throws SQLException;
	
	public List getDrinkSearch(String input) throws SQLException;
	
	public AdminEventVO eventInfo(String eventCode)throws SQLException;
	
	public void checkDate(String today)throws SQLException;
	
	public void chOpen(String eventCode)throws SQLException;
	
	public List getEventMember(String eventCode)throws SQLException;
}
