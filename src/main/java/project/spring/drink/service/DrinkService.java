package project.spring.drink.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.spring.beans.CommonInterface;
import project.spring.drink.dao.DrinkDAO;
import project.spring.drink.vo.CommentVO;
import project.spring.drink.vo.DrinkVO;

public interface DrinkService {

	// 1차 카테고리 가져오기
	public List<HashMap> selectBigCategoryList() throws SQLException;
	
	// 2차 카테고리 가져오기
	public List<HashMap> selectSmallCategoryList(String bigCategoryCode) throws SQLException;

	// 주류정보 상세
	public DrinkVO selectDrinkServiceInfo(String dkCode) throws SQLException;

	// 주류정보 한줄평 개수 & 평점
	public HashMap selectCommentStarServiceInfo(String dkCode) throws SQLException;

	public List<HashMap> selectTagCloudServiceInfo(String dkCode) throws SQLException;

	public List<CommentVO> selectCommentServiceList(String dkCode) throws SQLException;

	public HashMap selectItemValuesInfo(String bigCategoryCode) throws SQLException;

	public List<String> selectItemValuesList(String bigCategoryCode) throws SQLException;

	public String insertDrink(DrinkVO drinkVo) throws SQLException;

	public void updateDrinkTag(HashMap tagInfo) throws SQLException;

	public String insertDrinkImg(MultipartHttpServletRequest request) throws SQLException;

	// 주류 정보 리스트
	public List<DrinkVO> selectDrinkServiceList(HashMap<String, Object> schMap) throws SQLException;

	public String modifyDrink(DrinkVO drinkVo) throws SQLException;
	
	// 주류 정보 좋아요 여부
	public String selectDrinkLikeInfo(HashMap drinkLikeMap) throws SQLException;
	
	// 주류 정보 좋아요 업데이트
	public String updateDrinkLikeInfo(HashMap drinkLikeMap) throws SQLException;


	
}
