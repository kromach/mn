package project.spring.event.service;

import java.sql.SQLException;
import java.util.List;

import project.spring.beans.CommonInterface;
import project.spring.event.vo.EventVO;

public interface EventService extends CommonInterface{
	
	// 이벤트 카운트 가져오기
	public int getEventCount()throws SQLException;
	
	public List eventList(int idx)throws SQLException;

	public EventVO getEvent(String eventCode)throws SQLException;
	
	public void requestEvent(String eventCode, String id)throws SQLException;
	
	public int checkJoinEvent(String eventCode, String id)throws SQLException;
}
