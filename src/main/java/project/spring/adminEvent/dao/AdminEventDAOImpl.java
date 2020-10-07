package project.spring.adminEvent.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.adminEvent.vo.AdminEventVO;

@Repository
public class AdminEventDAOImpl implements AdminEventDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession = null;


	@Override
	public int insertItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertItem(AdminEventVO vo) {
		
		sqlSession.insert("adminEvent.insertEvent", vo);
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

	// 이벤트 카운트 가져오기
	@Override
	public int eventCount() throws SQLException {

		int count = sqlSession.selectOne("adminEvent.eventCount");
		System.out.println("count : " + count);
		return count;
	}

	// 이벤트 리스트 가져오기
	@Override
	public List eventList(int startRow, int endRow) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("start", startRow);
		map.put("end", endRow);
		
		
		List eventList = sqlSession.selectList("adminEvent.eventList", map);
		return eventList;
	}

}
