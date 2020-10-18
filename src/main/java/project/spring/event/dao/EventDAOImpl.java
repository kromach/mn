package project.spring.event.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public List eventList() throws SQLException {
		List eventList = sqlSession.selectList("event.eventList");
		return eventList;
	}

}
