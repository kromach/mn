package project.spring.article.dao;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		int result = -1;
		if(obj instanceof ArticleDTO) {
			result = sqlSession.update("article.updateArticle", (ArticleDTO)obj);
		}
		return result;
	}
	@Override
	public int deleteItem(Object obj) {
		
		if(obj instanceof HashMap) {
			HashMap map = (HashMap)obj;
			return sqlSession.delete("article.deleteArticle", map);
		}
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
		
		//해당코드로 들어간 이미지가 있는지 보기
		//String IMG_URL = sqlSession.selectOne("article.checkCodeInEditor",checkCode);
		
		//해당코드가 있다면, 한 게시물에서 여러이미지 등록이거나, 
		//전의 쓰던글에서 이미지만 로딩하고 글을 작성완료안한것이됨. 제거필요한지 모르겠음
		//한게시물의 여러이미지 등록 >> 단순 등록처리
		//전의 쓰던글에서 이미지만 로딩 >> 필요하다면 다음 글 작성에서 검사후 제거
		
		//등록로직
		return result;
	}
	@Override
	public List searchArticle(String selectOption,
			String search) {
		HashMap map = new HashMap();
		map.put("selectOption", selectOption);
		map.put("search",search );
		System.out.println(map);
		
		if(selectOption.equals("NICKNAME")) {
			return sqlSession.selectList("article.searchArticlebyNick", map);
		}else{
			return sqlSession.selectList("article.searchArticlebyOthers", map);
		}
	}
	@Override
	public List searchArticle() {
		return sqlSession.selectList("article.searchAll");
	}
	@Override
	public ArticleDTO read(int idx) {
		
		return sqlSession.selectOne("article.read", idx);
	}
	@Override
	public List searchArticleByAdd(int i) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("article.searchArticleByAdd",i);
	}
	@Override
	public void plusOneReadCount(int idx) {
		sqlSession.update("article.plusOneReadCount",idx);
	}
	@Override
	public int like(Integer num,String memNickName,String insertId) {
		HashMap map = new HashMap();
		map.put("memNickName", memNickName);
		map.put("num",num);
		int count = sqlSession.selectOne("article.alreadyLike",map);
		System.out.println("alreadyCountLike"+count);
		if(count == 0) {
			sqlSession.insert("article.like_log",map);
			sqlSession.update("article.like", num);
			sqlSession.update("article.updateMyActGivenHeart",insertId);
			
			return sqlSession.selectOne("article.likeReturn",num); 
		}
		return -1;
	}
	@Override
	public int unlike(Integer num,
			String memNickName, String insertId) {
		HashMap map = new HashMap();
		map.put("memNickName", memNickName);
		map.put("num",num);
		int count = sqlSession.selectOne("article.alreadyLike",map);
		System.out.println("alreadyCountUnLike"+count);
		if(count == 1) {
			sqlSession.delete("article.like_log_undo",map);
			sqlSession.update("article.like_undo", num);
			sqlSession.update("article.updateMyActGivenHeart_undo",insertId);
			
			return sqlSession.selectOne("article.likeReturn",num); 
		}
		return -1;
	}
	@Override
	public int report(Integer num, String insertId, String reportId) {
		int result = -1;
		String isAlreadyReported = "";
		boolean isAlreadyReported_ = false;
		isAlreadyReported = sqlSession.selectOne("article.isAlreadyReported", insertId);
		if(isAlreadyReported!=null) {
			String[] tmp = isAlreadyReported.split(",");
			for(String reported : tmp) {
				String[] tmp_ = reported.split("F");
				if(tmp_[1].equals(reportId)&&tmp_[0].equals(num+"")) {
					isAlreadyReported_ = true;
				}
			}
		}
		System.out.println("isAlreadyReportedBoolean"+isAlreadyReported_);
		if(!isAlreadyReported_) {
			//작성자 reportCount+1
			result += sqlSession.update("article.addReportCount", insertId);
			//reportNumber제작
			String reportString = num + "F" + reportId;
			HashMap map = new HashMap();
			map.put("insertId",insertId);
			map.put("reportString",reportString);
			//String넣기
			result += sqlSession.update("article.addReportNumber",map);
			//완료시 result 1
			System.out.println("ReportResult"+result);
		}
		//-1 >> 이미 신고
		//1  >> 신고
		return result;
	}
	@Override
	public int moveArticle(Integer num) {
		return sqlSession.update("article.moveArticle", num);
	}
	@Override
	public void insertReply(Map map) {
		System.out.println("insertREPLY DAO IMPLE======");
		sqlSession.delete("article.insertReply", map);
	}
	@Override
	public List getReply(int index,int bnIdx) {
		//해당범위 게시글 가져오기
		HashMap map = new HashMap();
		map.put("index", index);
		map.put("bnIdx", bnIdx);
		return sqlSession.selectList("article.getReply",map);
	}
	@Override
	public int getAllReplyCount(int bnIdx) {
		//총게시글수
		return sqlSession.selectOne("article.getCountAllReply",bnIdx);
	}
	@Override
	public int deleteReply(int coIdx, String session) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("coIdx", coIdx);
		map.put("insert_Id", session);
		System.out.println(map);
		return sqlSession.delete("article.deleteReply", map);
	}
}
