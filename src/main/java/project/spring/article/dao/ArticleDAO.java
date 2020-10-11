package project.spring.article.dao;

import java.util.List;

import project.spring.article.vo.Editor_imageVO;
import project.spring.beans.CommonInterface;
import project.spring.member.vo.MemberDTO;

public interface ArticleDAO extends CommonInterface{
	public List getDrinkSearch(String input);
	public int insertImg(Editor_imageVO editor_imageVO);

}
