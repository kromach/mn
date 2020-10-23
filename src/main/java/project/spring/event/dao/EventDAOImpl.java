package project.spring.event.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.event.vo.EventVO;

@Repository
public class EventDAOImpl implements EventDAO {

	@Autowired
	private SqlSessionTemplate sqlSession = null;
	
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
		int count = sqlSession.selectOne("event.eventCount");
		System.out.println("count!!! :" + count);
		return count;
	}

	@Override
	public List eventList(int idx) throws SQLException {
		List eventList = sqlSession.selectList("event.eventList", idx);
		return eventList;
	}

	@Override
	public EventVO getEvent(String eventCode) throws SQLException {
		EventVO vo = sqlSession.selectOne("event.getEvent", eventCode);
		return vo;
	}

	@Override
	public void requestEvent(String eventCode, String id) throws SQLException {
		HashMap map = new HashMap();
		map.put("eventCode", eventCode);
		map.put("id", id);
		sqlSession.insert("event.requestEvent", map);
		
	}

	@Override
	public int checkJoinEvent(String eventCode, String id) throws SQLException {
		HashMap map = new HashMap();
		map.put("eventCode", eventCode);
		System.out.println("eventCode" + eventCode);
		System.out.println("id : " + id);
		map.put("id", id);
		int idx = sqlSession.selectOne("event.checkJoinEvent", map);
		System.out.println("디에이오 idx : " + idx );
		return idx;
	}

}
