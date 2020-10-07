package project.spring.beans;

import project.spring.beans.CommonInterface;
import project.spring.member.vo.MemberDTO;

public interface memberInterface extends CommonInterface{
	public int insertItem();	
	public MemberDTO readItem();
	public MemberDTO findId(MemberDTO dto);
	public int readItem(Object obj);
	public MemberDTO findPw(MemberDTO dto);
	public int insertItemByKakao(MemberDTO dto);
	public boolean overlapCheck(String checkString, int i);
	public MemberDTO setSession(String id);
}
