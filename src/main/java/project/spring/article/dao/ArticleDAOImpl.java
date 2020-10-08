package project.spring.article.dao;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import project.spring.member.vo.MemberDTO;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;
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
		return 0;
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
		//우선 카테고리 검사
		List list = sqlSession.selectList("article.getDrinkSearch",input);
		System.out.println("list"+list);
		if(list!=null) {
			//카테고리에서 일치할시 그 카테고리에 있는 술 전부 ADD해서 리턴
			ArrayList listByCategory = new ArrayList();
			for(int i=0;i<list.size();i++) {
				listByCategory.add(sqlSession.selectList("article.getDrinkSearchByCategories", list.get(i)));
			}
			System.out.println(listByCategory.toString());
			return listByCategory;
		}
		//검색어가 카테고리가 아닐시에 술 이름으로 검색
		
		return list;
	}
}
