package project.spring.myAct.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.myAct.vo.MyActivityDTO;
import project.spring.myAct.vo.TitleListDTO;

@Repository
public class MyActDAOImpl implements MyActDAO{

	@Autowired
	private SqlSessionTemplate sqlSession = null;
	
	@Override
	public int insertItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertItem(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateItem(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteItem(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int myArticleCount(String memId) {
		int count = sqlSession.selectOne("myAct.myArticleCount", memId);
		return count;
	}
	
	@Override
	public List getMyArticles(String memId) {
	
		List list = sqlSession.selectList("myAct.selectAll", memId);
		
		return list;
	}

	@Override
	public int myLikeArticleCount(String memId) {
		int count = 0 ; 
		sqlSession.selectOne("myAct.myLikeArticleCount", memId);
		return count;
	}

	@Override
	public List myLikeArticle(String memId) {
		List list = sqlSession.selectList("myAct.myLikeArticle", memId);
		System.out.println(list.toString());
		return list;
	}

	@Override
	public List getMyTitle(String memId) {
		List list = sqlSession.selectList("myAct.getMyTitle", memId);
		return list;
	}
	
	/*
	1)
		title_list 의 value 값을 꺼내온다 				
		title_list의 key값으로 조건식 만듦
			my_attendent라면 30 - 90 - 180 - 365 중 어디 해당하는지 
		value값과 my_activity의 해당컬럼과 비교
		attendent가 75라면 title_list 의 my_attendent가 30인 값의 index 번호를 가져옴
	-----------------------------------------------------------------------
	2)	가져온 index 번호를 my_title에 insert해줌
		INSERT INTO MY_TITLE values(my_title_seq.nextval, 'testid', 해당인덱스, 'N', sysdate);
	*/
	@Override
	public List updateTitle(String memId) {
		List<TitleListDTO> titleValue =sqlSession.selectList("myAct.getAllTitle", memId);
		List<MyActivityDTO> myActivity = sqlSession.selectList("myAct.myActivity", memId);
		
		for(TitleListDTO dto : titleValue) {
			for(MyActivityDTO source : myActivity) {
				int result =0;
				if(dto.getTitleName().equals("my_attendent")) {
					int value = source.getMyAttendent();	// value 75			30<90<180<365
					System.out.println("value : " +value);
					if( value>=30 && value<90) {
						result = 2;
					}else if(value>=90 && value <180) {
						result = 3;
					}else if(value>= 180 && value <365) {
						result = 4;
					}else if (value >= 365) {
						result = 5;
					}
				}
				System.out.println("result : " +result);
			}
		}
		
		return titleValue;
	}
	
	@Override
	public List getAllTitle() {
		List list = sqlSession.selectList("myAct.getAllTitle");
		return list;
	}

	@Override
	public List getLikeDrink(String memId) {
		List list = sqlSession.selectList("myAct.getLikeDrink", memId);
		return list;
	}
	
	@Override
	public List getLikeProduct(String memId) {
		List list = sqlSession.selectList("myAct.getLikeProduct", memId);
		return list;
	}
	
}