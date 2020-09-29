package project.spring.member.dao;

import static org.hamcrest.CoreMatchers.instanceOf;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.member.vo.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public int insertItem() {
		return 0;
	}
	@Override
	public int updateItem() {
		return 0;
	}
	@Override
	public int deleteItem() {
		return 0;
	}
	@Override
	public int insertItem(Object obj) {
		int result = 0;
		if (obj instanceof MemberDTO) {
			result = sqlSession.selectOne("member.memberCount", obj);
		}
		return result;
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
