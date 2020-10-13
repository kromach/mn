package project.spring.adminEvent.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		System.out.println("check dao");
		// event코드 번호 생성 후 가져오기
		String codeIdx = sqlSession.selectOne("adminEvent.getNumber");
		vo.setEventCode(codeIdx);
		
		
		System.out.println("check---------------------------------------------------2222");

		System.out.println("이벤트 코드 : " + vo.getEventCode());
		System.out.println("evnetName : " + vo.getEventName());
		System.out.println("productCod : " + vo.getProductCode());
		System.out.println("stardDay:" +  vo.getEvStart());
		System.out.println("endDay" + vo.getEvEnd());
		System.out.println("content : " + vo.getContent());
		System.out.println("img : " + vo.getThumImg());
		System.out.println(vo.getInsertId());
		System.out.println(vo.getIsOpen());
		
		
		int result = sqlSession.insert("adminEvent.insertEvent", vo);
		
		System.out.println("들어갓나?");
		
		return result;
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
		int result = sqlSession.update("adminEvent.updateEvent", obj);
		return result;
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
	
	public int eventCount(Map schMap)throws SQLException{
		

		int count = sqlSession.selectOne("adminEvent.schEventCount", schMap);
		System.out.println("부탁합니다" + count);
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
	
	public List eventList(int startRow, int endRow, Map schMap)throws SQLException{
		
		schMap.put("start", startRow);
		schMap.put("end", endRow);
		List eventList = sqlSession.selectList("adminEvent.schEventList", schMap);
		
		return eventList;
	}

	@Override
	public List getDrinkSearch(String input) throws SQLException {

		System.out.println("dao까지 왔니?");
		List  list = sqlSession.selectList("adminEvent.getDrinkSearch", input);
		System.out.println("넌 어디니");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("list !!!!! " + list.get(i));
		}
		
		return list;
	}

	@Override
	public AdminEventVO getEventInfo(String eventCode) throws SQLException {
		
		AdminEventVO vo = sqlSession.selectOne("adminEvent.getEventInfo", eventCode);
		
		return vo;
	}

	@Override
	public void checkDate(String today) throws SQLException {

		sqlSession.update("adminEvent.checkDate", today);
	}

}
