package project.spring.myAct.dao;

import java.util.List;

import project.spring.beans.CommonInterface;

public interface MyActDAO extends CommonInterface{
	
	public int myArticleCount(String memId);
	public List getMyArticles(int startRow, int endRow, String memId);

}
