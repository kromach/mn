package project.spring.member.dao;

import project.spring.beans.CommonInterface;

public interface MemberDAO extends CommonInterface{

	int readItem(Object obj);
	
}
