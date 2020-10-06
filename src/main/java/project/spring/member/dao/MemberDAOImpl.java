package project.spring.member.dao;

import static org.hamcrest.CoreMatchers.instanceOf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
	public MemberDTO readItem() {		
	String id = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes())
						.getRequest().getSession()
						.getAttribute("memId")
						.toString();
	System.out.println(id);
	return sqlSession.selectOne("member.getMember", id);
	}
	
	@Override
	public int insertItem(Object obj) {
		int result = 0;
		if (obj instanceof MemberDTO) {
			result = sqlSession.insert("member.insertMember", obj);
		}
		System.out.println("daoImple_reulst="+result);
		return result;
	}
	@Override
	public int updateItem(Object obj) {
		int result = 0;
		if (obj instanceof MemberDTO) {
			System.out.println("updateOBJ"+(MemberDTO)obj);
			result = sqlSession.update("member.updateMember", obj);
		}
		return result;
	}
	@Override
	public int deleteItem(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int readItem(Object obj) {
		int result = -1;
		if (obj instanceof MemberDTO) {
			MemberDTO dto = sqlSession.selectOne("member.isExistId", obj);
			MemberDTO dto_origin = (MemberDTO)obj;
			//결과값없음
			if(dto==null) {
				System.out.println(result);
				return result;
			}
			
			//id,pw일치
			if(dto.getId().equals(dto_origin.getId())&&
				dto.getPw().equals(dto_origin.getPw())
					) {
				result = 1;
				HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				req.getSession().setAttribute("memId", dto.getId());
				req.getSession().setAttribute("memNickName", dto.getNickName());
			}
			//id만 일치
			else if(dto.getId().equals(dto_origin.getId())) {
				result = 0;
			}
			System.out.println("DAOImpleResult="+result);
		} 
		return result;
	}
	
	@Override
	public MemberDTO findId(MemberDTO dto) {
		MemberDTO result = sqlSession.selectOne("member.findId", dto);
		return result;
	}
	@Override
	public MemberDTO findPw(MemberDTO dto) {
		MemberDTO result = sqlSession.selectOne("member.findPw", dto);
		return result;
	}
}
