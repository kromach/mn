package project.spring.sales.dao;

import project.spring.beans.CommonInterface;

public interface SalesDAO extends CommonInterface{

	public int salesCount(String memId);

}
