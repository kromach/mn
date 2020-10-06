package project.spring.beans;

import project.spring.beans.CommonInterface;
import project.spring.member.vo.MemberDTO;

public interface memberInterface extends CommonInterface{
	public int insertItem(MemberDTO dto);	
	public MemberDTO readItem();
	public MemberDTO findId(MemberDTO dto);
	int readItem(Object obj);
}
