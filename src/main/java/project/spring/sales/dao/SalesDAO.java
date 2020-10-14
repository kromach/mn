package project.spring.sales.dao;

import java.util.List;

import project.spring.beans.CommonInterface;

public interface SalesDAO extends CommonInterface{

	public int salesCount(String memId);
	public List productorlist(String memId);
	public String userIdch(String memId);

}
