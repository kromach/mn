  package project.spring.admin.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAOImpl implements AdminDAO{
	
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
	public List memberList(int start, int end) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List list = sqlSession.selectList("admin.memberList", map);
		return list;
	}

	@Override
	public int memberCount() throws SQLException {
		
		int count = sqlSession.selectOne("admin.memberCount");
		
		return count;
	}

}
