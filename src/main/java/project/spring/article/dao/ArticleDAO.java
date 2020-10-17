package project.spring.article.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.spring.article.vo.ArticleDTO;
import project.spring.article.vo.Editor_imageVO;
import project.spring.beans.CommonInterface;
import project.spring.member.vo.MemberDTO;

public interface ArticleDAO extends CommonInterface{
	public List getDrinkSearch(String input);
	public int insertImg(Editor_imageVO editor_imageVO);
	public List searchArticle(String selectOption,
			String search);
	public List searchArticle();
	public ArticleDTO read(int idx);
	public List searchArticleByAdd(int i);
	public void plusOneReadCount(int idx);
	public int like(Integer num, String memNickName,String insertId);
	public int unlike(Integer num,
			String memNickName, String insertId);
	public int report(Integer num,
			String insertId, String reportId);
	public int moveArticle(Integer num);
	public void insertReply(Map map);
	public List getReply(int index,int bnIdx);
	public int getAllReplyCount();
}
