package project.spring.myAct.service;

import java.util.List;

import project.spring.beans.CommonInterface;
import project.spring.myAct.vo.TitleListDTO;

public interface MyActService extends CommonInterface{
	public int myArticleCount(String memId);
	public List getMyArticle(String memId);
	public int likeArticleCount(String memId);
	public List myLikeArticle(String memId);
	public List getMyTitle(String memId);
	public List updateTitle(String memId);
	public List getAllTitle();
	public List myLikeDrink(String memId);
	public List myLikeProduct(String memId);
	public int attendent(String memId);
}