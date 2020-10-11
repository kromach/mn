package project.spring.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spring.article.dao.ArticleDAO;

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
		return 0;
	}
	@Override
	public int deleteItem(Object obj) {
		return 0;
	}
	
	@Override
	public List getDrinkSearch(String input) {
		System.out.println("cehck1");
		List list = null;
		list = articleDAOimpl.getDrinkSearch(input);
		return list ; 
	}
	
	@Override
	public int insertImg(String newName) {
		int result = -1;
		
		return result;
		
		
	}
}
