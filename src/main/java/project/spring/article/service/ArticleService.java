package project.spring.article.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.spring.article.vo.ArticleDTO;
import project.spring.article.vo.Editor_imageVO;
import project.spring.beans.CommonInterface;

public interface ArticleService extends CommonInterface {
	public List getDrinkSearch(String input);
	public int insertImg(Editor_imageVO editor_imageVO);
	public List searchArticle(String selectOption,String search);
	public List searchArticle();
	public ArticleDTO read(int idx);
	public List searchArticleByAdd(int i);
	public void plusOneReadCount(int idx);
	public int like(Integer num, HashMap map);
}
