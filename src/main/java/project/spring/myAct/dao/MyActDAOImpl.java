package project.spring.myAct.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.myAct.vo.MyActivityDTO;
import project.spring.myAct.vo.MyTitleDTO;
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
		//달성조건(모든 타이틀 정보 가져옴)
		List<TitleListDTO> titleValue =sqlSession.selectList("myAct.getAllTitle", memId);
		//내가 가지고 있는 현황(내가 가진 타이틀 정보 가져옴)
		MyActivityDTO myActivity = sqlSession.selectOne("myAct.myActivity", memId);
		
		// 내가 칭호를 갖고 있지 않다면 null 리턴
		if(myActivity == null) {
			return null;
		}
		
		Map<String, String> map = new HashMap<String,String>();
		//기준들의 ArrayList
		ArrayList<String> standard = new ArrayList<String>();
		for(TitleListDTO dto : titleValue) {
			standard.add(dto.getTitleKey());
		}
		//중복제거
		HashSet<String> standardHashSet = new HashSet<String>(standard);
		System.out.println("=====================HashSetStandard"+standardHashSet);
		for(TitleListDTO dto : titleValue) {	//titleValue는 모든 title 정보, 기준값 들어있음
			Iterator itStandard =  standardHashSet.iterator();
			while(itStandard.hasNext()) {
				String standardString = (String) itStandard.next();
				//비교
				// map의 (key, value) : ((titleValue:titleKey),titleName)
				if(dto.getTitleKey().equals(standardString)){
					map.put((dto.getTitleValue()+":"+dto.getTitleKey()), dto.getTitleName());
				}
			}
		}
		System.out.println("mapPrint================"+map);
		
		Set<String> key = map.keySet();
		Iterator<String> it = key.iterator();
		
		List returnList = new ArrayList();
		while(it.hasNext()) {
			String keyForMap = it.next();
			String[] keyForMapTmp = keyForMap.split(":");
			System.out.println("keyForMapVal================"+keyForMapTmp[0]);
			System.out.println("keyForMapKey================"+keyForMapTmp[1]);
			
			//titleKey에 따라서 return을 다르게 해줌
			if(keyForMapTmp[1] !=null&&keyForMapTmp[0]!=null) {
				if(keyForMapTmp[1].equals("my_attendent")) {
					if(myActivity.getMyAttendent() >= Integer.parseInt(keyForMapTmp[0])) {
						System.out.println(myActivity.getId()+"님이"+ map.get(keyForMap)+"를 획득하였습니다.");
						returnList.add(map.get(keyForMap));
					}
				}
				if(keyForMapTmp[1].equals("my_like")) {
					if(myActivity.getGivenHeart() >= Integer.parseInt(keyForMapTmp[0])) {
						System.out.println(myActivity.getId()+"님이"+ map.get(keyForMap)+"를 획득하였습니다.");
						returnList.add(map.get(keyForMap));
					}
				}
				if(keyForMapTmp[1].equals("my_article")) {
					if(myActivity.getMyArticleCount() >= Integer.parseInt(keyForMapTmp[0])) {
						System.out.println(myActivity.getId()+"님이"+ map.get(keyForMap)+"를 획득하였습니다.");
						returnList.add(map.get(keyForMap));
					}
				}
				if(keyForMapTmp[1].equals("my_recomment")) {
					if(myActivity.getMyCommentCount() >= Integer.parseInt(keyForMapTmp[0])) {
						System.out.println(myActivity.getId()+"님이"+ map.get(keyForMap)+"를 획득하였습니다.");
						returnList.add(map.get(keyForMap));
					}
				}
			}
		}
		return returnList;
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

	@Override
	public int updateAttend(String memId) {
		int result = 0;
		
		sqlSession.update("myAct.updateAttend", memId);
		
		return result;
	}

	@Override
	public int choose(int idx, String memId) {
		int count = 0;
		int count_ = 0;
		MyTitleDTO dto = new MyTitleDTO();
		dto.setId(memId);
		dto.setTitleIdx(idx);
		System.out.println(dto.getId());
		System.out.println(dto.getTitleIdx());
		
		count_ = sqlSession.update("myAct.choose_", memId);
		count = sqlSession.update("myAct.choose", dto);
		return count;
	}
	
}