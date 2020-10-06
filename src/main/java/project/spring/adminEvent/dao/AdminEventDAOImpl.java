package project.spring.adminEvent.dao;

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

}
