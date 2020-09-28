package project.spring.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.beans.memberInterface;
import project.spring.kakao.vo.MemberDTO;
import project.spring.member.dao.MemberDAO;

@Service
public class MemberServiceImpl implements memberInterface{
	
	@Autowired
	MemberDAO memberDAO;
	
	@Override
	public int insertItem(MemberDTO dto) {
		
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
		// TODO Auto-generated method stub
		return 0;
	}
}
