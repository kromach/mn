  package project.spring.admin.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List memberList(int start, int end, Map map) throws SQLException {
		
		map.put("start", start);
		map.put("end", end);
		List list = sqlSession.selectList("admin.schMemberList", map);
		return list;
	}

	@Override
	public int memberCount() throws SQLException {
		
		int count = sqlSession.selectOne("admin.memberCount");
		
		return count;
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
		String memberId = (String)obj;
		int i = sqlSession.update("admin.deleteMember", memberId);
		return i;
	}

	@Override
	public int drinkCount() throws SQLException {
		int i = sqlSession.selectOne("admin.drinkCount");
		return i;
	}

	@Override
	public List drinkList(int start, int end) throws SQLException {
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List list = sqlSession.selectList("admin.drinkList", map);
		return list;
	}

	@Override
	public int approveDrink(String dkCode, String check) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("dkCode", dkCode);
		map.put("dkApprove", check);
		int i = sqlSession.update("admin.approveDrink", map);
		return i;
	}

	@Override
	public int memberCount(Map map) throws SQLException {
		int count = sqlSession.selectOne("admin.SchMemberCount", map);
		return count;
	}

	

}
