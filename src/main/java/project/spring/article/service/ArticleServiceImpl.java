package project.spring.article.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spring.article.dao.ArticleDAO;
import project.spring.article.dao.ArticleDAOImpl;
import project.spring.article.vo.ArticleDTO;
import project.spring.article.vo.Editor_imageVO;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	private ArticleDAO articleDAOimpl;
	
	@Override
	public int insertItem() {
		return 0;
	}
	@Override
	public int updateItem() {
		return 0;
	}
	@Override
	public int deleteItem() {
		return 0;
	}
	@Override
	public int insertItem(Object obj) {
		int result = articleDAOimpl.insertItem(obj);
		return result;
	}
	@Override
	public int updateItem(Object obj) {
		int result = articleDAOimpl.updateItem(obj);
		return result;
	}
	@Override
	public int deleteItem(Object obj) {
		return articleDAOimpl.deleteItem(obj);
	}
	
	@Override
	public List getDrinkSearch(String input) {
		System.out.println("cehck1");
		List list = null;
		list = articleDAOimpl.getDrinkSearch(input);
		return list ; 
	}
	
	@Override
	public int insertImg(Editor_imageVO editor_imageVO) {
		int result = -1;
		result = articleDAOimpl.insertImg(editor_imageVO);
		return result;
	}
	
	@Override
	public List searchArticle(String selectOption,
			String search) {
		
		return articleDAOimpl.searchArticle(selectOption,search);
		
	}
	@Override
	public List searchArticle() {
		
		return articleDAOimpl.searchArticle();
		
	}
	
	@Override
	public ArticleDTO read(int idx) {
		
		return articleDAOimpl.read(idx);
	}
	
	@Override
	public List searchArticleByAdd(int idx) {
		return articleDAOimpl.searchArticleByAdd(idx);
	}
	@Override
	public void plusOneReadCount(int idx) {
		articleDAOimpl.plusOneReadCount(idx);
	}
	@Override
	public int like(Integer num,String memId,String insertId) {
		return articleDAOimpl.like(num,memId,insertId);
	}
	@Override
	public int unlike(Integer num,
			String memNickName, String insertId) {
		return articleDAOimpl.unlike(num,memNickName,insertId);
	}
	@Override
	public int report(Integer num,
			String insertId, String reportId) {
		// TODO Auto-generated method stub
		return articleDAOimpl.report(num,insertId,reportId);
	}
	@Override
	public int moveArticle(Integer num) {
		// TODO Auto-generated method stub
		return articleDAOimpl.moveArticle(num);
	}
	
	@Override
	public void insertReply(Map map) {
		articleDAOimpl.insertReply(map);
	}
	
	@Override
	public List getReply(int index,int bnIdx) {
		// TODO Auto-generated method stub
		return articleDAOimpl.getReply(index,bnIdx);
	}
	@Override
	public int getAllReplyCount(int bnIdx) {
		// TODO Auto-generated method stub
		return articleDAOimpl.getAllReplyCount(bnIdx);
	}
	@Override
	public int deleteReply(int coIdx, String session) {
		// TODO Auto-generated method stub
		return articleDAOimpl.deleteReply(coIdx,session);
	}
}
