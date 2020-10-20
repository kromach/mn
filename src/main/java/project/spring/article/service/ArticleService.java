package project.spring.article.service;

import java.sql.SQLException;
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
	public int like(Integer num, String memNickName,String insertId);
	public int unlike(Integer num, String memNickName,
			String insertId);
	public int report(Integer num, String insertId,
			String reportId);
	public int moveArticle(Integer num);
	public int backArticle(int num);
	public void insertReply(Map map);
	public List getReply(int index,int bnIdx);
	public int getAllReplyCount(int bnIdx);
	public int deleteReply(int coIdx, String session);
	public List<ArticleDTO> searchPost(String selectOption, String search);
	public List<ArticleDTO> searchPost();
	
	public int selectDkcodeArticleCount(String dkCode) throws SQLException;

	public List<ArticleDTO> selectDkcodeArticleList(HashMap searchMap) throws SQLException;
	
}
