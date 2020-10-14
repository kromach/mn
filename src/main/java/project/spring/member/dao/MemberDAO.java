package project.spring.member.dao;

import project.spring.beans.CommonInterface;
import project.spring.member.vo.MemberDTO;

public interface MemberDAO extends CommonInterface{
	public int readItem(Object obj);
	public MemberDTO readItem();
	public MemberDTO findId(MemberDTO dto);
	public MemberDTO findPw(MemberDTO dto);
	public int insertItemByKakao(MemberDTO dto);
	public boolean overlapCheck(String checkString,
			int i);
	public MemberDTO setSession(String id);
	public void attendent(String id);
}
