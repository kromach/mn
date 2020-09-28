package project.spring.beans.kakaoAPI;

import project.spring.beans.CommonInterface;
import project.spring.kakao.vo.MemberDTO;

public interface memberInterface extends CommonInterface{
	public MemberDTO readItem();	
}
