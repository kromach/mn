package project.spring.sales.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class SalesDAOImpl implements SalesDAO{

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
	public int salesCount(String memId) {
		int count = 0;
		count = sqlSession.selectOne("sales.salesCount", memId);
		System.out.println(count);
		return count;
	}

	@Override
	public List productorlist(String memId, int start, int end) {
		HashMap map = new HashMap();
		map.put("memId", memId);
		map.put("start", start);
		map.put("end", end);
		List list = sqlSession.selectList("sales.salesList", map);
		System.out.println("list 가져왔나 ? " + list.toString());
		return list;
	}

	@Override
	public String userIdch(String memId) {
		String result = sqlSession.selectOne("sales.userIdCh", memId);
		return result;
	}

}
