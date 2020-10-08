package project.spring.article.service;

import java.util.List;

import project.spring.beans.CommonInterface;

public interface ArticleService extends CommonInterface {
	public List getDrinkSearch(String input);
}
