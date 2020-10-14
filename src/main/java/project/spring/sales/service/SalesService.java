package project.spring.sales.service;

import java.util.List;

import project.spring.beans.CommonInterface;

public interface SalesService extends CommonInterface{

	public int salesCount(String memId);
	public List productorList(String memId);
	public String userIdCh(String memId);

}
