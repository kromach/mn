package project.spring.myAct.dao;

import java.util.List;

import project.spring.beans.CommonInterface;

public interface MyActDAO extends CommonInterface{
	
	public int myArticleCount(String memId);
	public List getMyArticles(String memId);
	public int myLikeArticleCount(String memId);
	public List myLikeArticle(String memId);
	public int myLikeDrinkCount(String memId);
	public List myLikeDrink(String memId);
	public List getMyTitle(String memId);
	public List getAllTitle();
	public int updateTitle(String memId);

}
