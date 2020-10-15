package project.spring.event.dao;

import java.sql.SQLException;
import java.util.List;

import project.spring.beans.CommonInterface;

public interface EventDAO extends CommonInterface{
	
	public int getEventCount()throws SQLException;

	public List eventList()throws SQLException;
	
}
