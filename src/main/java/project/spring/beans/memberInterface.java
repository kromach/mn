package project.spring.beans;

import project.spring.beans.CommonInterface;
import project.spring.kakao.vo.MemberDTO;

public interface memberInterface extends CommonInterface{
	int insertItem(MemberDTO dto);	
	public MemberDTO readItem();
	int readItem(Object obj);
}
