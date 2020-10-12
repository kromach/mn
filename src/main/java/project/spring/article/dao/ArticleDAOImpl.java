package project.spring.article.dao;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import project.spring.article.vo.ArticleDTO;
import project.spring.article.vo.Editor_imageVO;
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
		int result = -1;
		if(obj instanceof ArticleDTO) {
			//전에글 코드이외의 이미지가있으면 지워주는로직 필요하다면 작성
			System.out.println("daoImpl="+(ArticleDTO)obj);
			result = sqlSession.insert("article.insertTags", (ArticleDTO)obj);
		}
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
		
		System.out.println("check2");
		System.out.println("DAO"+input);
		//우선 카테고리 검사
		List<HashMap<String, String>> list = sqlSession.selectList("article.getDrinkSearch",input);
		if(list.size()!=0) {
			//카테고리에서 일치할시 그 카테고리에 있는 술 전부 ADD해서 리턴
			ArrayList listByCategory = new ArrayList();
			for(int i=0;i<list.size();i++) {
				listByCategory.add(sqlSession.selectList("article.getDrinkSearchByCategories", list.get(i).get("CODE")));
			}
			System.out.println(listByCategory.toString());
			return listByCategory;
		}
		System.out.println("input ================" + input);
		//검색어가 카테고리가 아닐시에 술 이름으로 검색
		ArrayList listByName = new ArrayList();
		listByName.add(sqlSession.selectList("article.getDrinkSearchByName",input));
		return listByName;
	}
	@Override
	public int insertImg(Editor_imageVO editor_imageVO) {
		int result = -1;
		//boardNomal의 글의 +1해서 코드를생성
		int maxBnInx_BoardNomal = sqlSession.selectOne("article.selectMaxBnIDX");
		String checkCode = "F0000"+ maxBnInx_BoardNomal;
		
		//해당코드로 들어간 이미지가 있는지 보기
		String IMG_URL = sqlSession.selectOne("article.checkCodeInEditor",checkCode);
		
		//해당코드가 있다면, 한 게시물에서 여러이미지 등록이거나, 
		//전의 쓰던글에서 이미지만 로딩하고 글을 작성완료안한것이됨. 제거필요한지 모르겠음
		//한게시물의 여러이미지 등록 >> 단순 등록처리
		//전의 쓰던글에서 이미지만 로딩 >> 필요하다면 다음 글 작성에서 검사후 제거
		
		//등록로직
		editor_imageVO.setCode(checkCode);
		System.out.println("ArticleDAOimpl="+editor_imageVO);
		result  =  sqlSession.insert("article.insertImg", editor_imageVO);
		return result;
	}
}
