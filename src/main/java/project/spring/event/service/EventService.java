package project.spring.event.service;

import java.sql.SQLException;
import java.util.List;

import project.spring.beans.CommonInterface;

public interface EventService extends CommonInterface{
	
	// 이벤트 카운트 가져오기
	public int getEventCount()throws SQLException;
	
	public List eventList(int idx)throws SQLException;

}
