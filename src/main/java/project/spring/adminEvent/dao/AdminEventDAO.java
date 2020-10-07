package project.spring.adminEvent.dao;

import java.sql.SQLException;
import java.util.List;

import project.spring.beans.CommonInterface;

public interface AdminEventDAO extends CommonInterface {
	
	public int eventCount()throws SQLException;
	
	public List eventList(int startRow, int endRow) throws SQLException;

}
