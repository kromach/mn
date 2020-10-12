package project.spring.article.service;

import java.util.List;

import project.spring.article.vo.Editor_imageVO;
import project.spring.beans.CommonInterface;

public interface ArticleService extends CommonInterface {
	public List getDrinkSearch(String input);
	public int insertImg(Editor_imageVO editor_imageVO);
	public List searchArticle(String selectOption,String search);
	public List searchArticle();
}
