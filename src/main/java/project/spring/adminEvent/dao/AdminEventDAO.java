package project.spring.adminEvent.dao;

import java.sql.SQLException;
import java.util.List;

import project.spring.adminEvent.vo.AdminEventVO;
import project.spring.beans.CommonInterface;

public interface AdminEventDAO extends CommonInterface {
	
	public int eventCount()throws SQLException;
	
	public List eventList(int startRow, int endRow) throws SQLException;

	public List getDrinkSearch(String input) throws SQLException;
	
	public AdminEventVO getEventInfo(String eventCode) throws SQLException;
	
	public void checkDate(String today)throws SQLException;
	
	public void chOpen(String eventCode)throws SQLException;
	
	public List getEventMember(String eventCode)throws SQLException;
	
}
