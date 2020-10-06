package project.spring.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.beans.memberInterface;
import project.spring.member.dao.MemberDAOImpl;
import project.spring.member.vo.MemberDTO;

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
	//get
	@Override
	public MemberDTO readItem() {
		// TODO Auto-generated method stub
		MemberDTO dto = new MemberDTO();
		dto = memberDAO.readItem();
		
		return dto; 
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
		int result = memberDAO.updateItem(obj);
		System.out.println("memberUpdateResult = "+result);
		return result;
	}
	@Override
	public int deleteItem(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	} 
	//check
	@Override
	public int readItem(Object obj) {
		System.out.println(obj);
		int result = memberDAO.readItem(obj);
		System.out.println("ServiceImpleResult="+result);
		return result;
	}
	//findId
	@Override
	public MemberDTO findId(MemberDTO dto) {
		MemberDTO result = memberDAO.findId(dto);
		return result;
	}
	@Override
	public MemberDTO findPw(MemberDTO dto) {
		MemberDTO result = memberDAO.findPw(dto);
		return result;
	}
	@Override
	public int insertItemByKakao(MemberDTO dto) {
		int result = memberDAO.insertItemByKakao(dto);
		return result;
	}
}
