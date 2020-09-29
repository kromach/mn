package project.spring.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.beans.memberInterface;
import project.spring.kakao.vo.MemberDTO;
import project.spring.member.dao.MemberDAOImpl;

@Service
public class MemberServiceImpl implements memberInterface{
	
	@Autowired
	MemberDAOImpl memberDAO;
	
	@Override
	public int insertItem(MemberDTO dto) {
		System.out.println(memberDAO);
		System.out.println(dto);
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
	public MemberDTO readItem() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int insertItem() {
		return 0;
	}
	@Override
	public int insertItem(Object obj) {
		int result = 0;
		result = memberDAO.insertItem(obj);
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
