package project.spring.drink.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.drink.vo.CommentVO;
import project.spring.drink.vo.DrinkVO;

@Repository
public class DrinkDAOImpl implements DrinkDAO {

	private final SqlSessionTemplate sqlSession;

	@Autowired
	DrinkDAOImpl(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<DrinkVO> selectDrinkServiceList(HashMap<String, Object> schMap) throws SQLException {
		return sqlSession.selectList("drink.selectDrinkServiceList", schMap);
	}
	
	@Override
	public List<HashMap> selectBigCategoryList() throws SQLException {
		return sqlSession.selectList("drink.selectBigCategoryList");
	}
	
	@Override
	public List<HashMap> selectSmallCategoryList(String bigCategoryCode) throws SQLException {
		return sqlSession.selectList("drink.selectSmallCategoryList", bigCategoryCode);
	}

	@Override
	public HashMap selectItemValuesInfo(String bigCategoryCode) throws SQLException {
		return sqlSession.selectOne("drink.selectItemValuesInfo", bigCategoryCode);
	}

	@Override
	public DrinkVO selectDrinkServiceInfo(String dkCode) throws SQLException {

		return sqlSession.selectOne("drink.selectDrinkServiceInfo", dkCode);
	}


	@Override
	public HashMap selectCommentStarServiceInfo(String dkCode) throws SQLException {
		return sqlSession.selectOne("drink.selectCommentStarServiceInfo", dkCode);
	}


	@Override
	public List<HashMap> selectTagCloudServiceInfo(String dkCode) throws SQLException {
		return sqlSession.selectList("drink.selectTagCloudServiceInfo", dkCode);
	}


	@Override
	public List<CommentVO> selectCommentServiceList(String dkCode) throws SQLException {
		return sqlSession.selectList("drink.selectCommentServiceList", dkCode);
	}

	@Override
	public List<String> selectItemValuesList(String bigCategoryCode) throws SQLException {
		return sqlSession.selectList("drink.selectItemValuesList", bigCategoryCode);
	}

	@Override
	public String insertDrink(DrinkVO drinkVo) throws SQLException {
		sqlSession.insert("drink.insertDrink", drinkVo);
		
		//System.out.println(drinkVo.getDkCode());
		return drinkVo.getDkCode(); 
	}

	@Override
	public void updateDrinkTag(HashMap tagInfo) throws SQLException {
		sqlSession.update("drink.updateDrinkTag", tagInfo);
		//System.out.println(res);
	}

	@Override
	public String modifyDrink(DrinkVO drinkVo) throws SQLException {
		int result = sqlSession.update("drink.modifyDrink", drinkVo);
		
		String str = (result == 1 ? "주류 정보가 수정되었습니다." : "수정이 실패하였습니다.");
		
		return str;
	}

	@Override
	public String selectDrinkLikeInfo(HashMap drinkLikeMap) throws SQLException {
		
		return sqlSession.selectOne("drink.selectDrinkLikeInfo", drinkLikeMap);
	}

	@Override
	public String updateDrinkLikeInfo(HashMap drinkLikeMap) throws SQLException {
		
		String likeYn = (String)drinkLikeMap.get("likeYn"); // 변경 전 상태
		String result = ""; // DB 업데이트 후 상태
		int res = 0;
		System.out.println("dao 변경 전 " + likeYn);
		if (likeYn.equals("Y")) {
			res = sqlSession.delete("drink.deleteDrinkLikeInfo", drinkLikeMap);
		} else {
			res = sqlSession.insert("drink.insertDrinkLikeInfo", drinkLikeMap);
		}
		
		if(res == 1 && likeYn.equals("Y")) {
			result = "N";
		}
		
		if(res == 1 && likeYn.equals("N")) {
			result = "Y";
		}
		
		return result;
	}

	@Override
	public void updateDrinkLikeCount(HashMap drinkLikeMap) throws SQLException {
		int res = sqlSession.update("drink.updateDrinkLikeCount", drinkLikeMap);
		
		System.out.println("like 업데이트 개수 " + res );
	}

	@Override
	public String insertComment(CommentVO commentVo) throws SQLException {
		int res = sqlSession.insert("drink.insertComment", commentVo);
		String result = "등록이 실패 하였습니다. 다시 시도해주세요.";
		if(res == 1) {
			result = "등록이 완료 되었습니다.";
		}
		
		return result;
	}

	/*
	@Override
	public void insertAticle(BoardDTO dto) throws SQLException {
		
		// ref에 미리 시퀀스 번호 추적해서 다은 번호로 받아 추가하고 insert
		int number = 0;
		String numb = sqlSession.selectOne("board.maxNum");
		
		//System.out.println("numb" + numb);
		
		if(numb != null) {
			number = Integer.parseInt(numb);
			//number = Integer.parseInt(numb) + 1;
		} else {
			number = 1;
		}
		
		if(dto.getNum() != null && dto.getNum() > 0) { // 답글 일때
			// 이전 답글 step 미리 올리기
			HashMap map = new HashMap();
			map.put("ref", dto.getRef());
			map.put("re_step", dto.getRe_step());
			
			sqlSession.update("board.updateReStep", map);
			
			dto.setRe_step(dto.getRe_step() + 1);
			dto.setRe_level(dto.getRe_level() + 1);
		} else { // 새글일때
		
			dto.setRef(number);
			dto.setRe_level(0);
			dto.setRe_step(0);
			
		}
		
		dto.setNum(number); //num에 새 글 번호 삽입
		
		// 글 저장
		sqlSession.insert("board.insertAticle", dto);
	}

	@Override
	public int selectAticleCount() throws SQLException {
		int count = sqlSession.selectOne("board.selectAticleCount");
		
		return count;
	}

	@Override
	public int selectAticleCount(String schKey, String schValue) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("schKey", schKey);
		map.put("schValue", schValue);
		
		int count = sqlSession.selectOne("board.selectSearchAticleCount", map);
		
		return count;
	}

	@Override
	public List selectAticleList(int start, int end) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List list = sqlSession.selectList("board.selectAticleList", map);
		
		return list;
	}

	@Override
	public List selectAticleList(String schKey, String schValue, int start, int end) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("schKey", schKey);
		map.put("schValue", schValue);
		
		List list = sqlSession.selectList("board.selectSearchAticleList", map);
		
		return list;
	}

	@Override
	public BoardDTO selectAticleInfo(int num, String readCountUp) throws SQLException {
		
		if(readCountUp == "Y") {
			// readCount 1 추가
			sqlSession.update("board.updateReadCount", num);
		}
		
		BoardDTO aticle = sqlSession.selectOne("board.selectAticleInfo", num);
		
		return aticle;
	}

	@Override
	public int updateAticle(BoardDTO dto) throws SQLException {
		
		int result = -1;
		String dbpw = sqlSession.selectOne("board.selectPw", dto.getNum());
		
		if(dbpw != null) {
			if(dbpw.equals(dto.getPw())) {
				sqlSession.update("board.updateAticle", dto);
				result = 1;
			}
			else {
				result = 0;
			}
		}
		
		return result;
	}

	@Override
	public int deleteAticle(int num, String pw) throws SQLException {
		
		int result = -1;
		String dbpw = sqlSession.selectOne("board.selectPw", num);
		
		if(dbpw != null) {
			if(dbpw.equals(pw)) {
				sqlSession.delete("board.deleteAticle", num);
				result = 1;
			}
			else {
				result = 0;
			}
		}
		
		return result;
	}
	 */

}
