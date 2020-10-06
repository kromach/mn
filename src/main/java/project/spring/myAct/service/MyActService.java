package project.spring.myAct.service;

import java.util.List;

import project.spring.beans.CommonInterface;

public interface MyActService extends CommonInterface{

	public int myArticleCount(String memId);
	public List getMyArticle(String memId);
	public int likeArticleCount(String memId);
	public List myLikeArticle(String memId);
	public int likeDrinkCount(String memId);
	public List myLikeDrink(String memId);
	public List getMyTitle(String memId);
	public List getAllTitle();
}