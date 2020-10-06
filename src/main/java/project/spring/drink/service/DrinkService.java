package project.spring.drink.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import project.spring.beans.CommonInterface;
import project.spring.drink.dao.DrinkDAO;
import project.spring.drink.vo.CommentVO;
import project.spring.drink.vo.DrinkVO;

public interface DrinkService {

	// 1차 카테고리 가져오기
	public DrinkVO selectBigCategoryList();

	public List<HashMap> selectSmallCategoryList(String bigCategoryCode) throws Exception;

	// 주류정보 상세
	public DrinkVO selectDrinkServiceInfo(String dkCode) throws SQLException;

	// 주류정보 한줄평 개수 & 평점
	public HashMap selectCommentStarServiceInfo(String dkCode) throws SQLException;

	public List<HashMap> selectTagCloudServiceInfo(String dkCode) throws SQLException;

	public List<CommentVO> selectCommentServiceList(String dkCode) throws SQLException;
	
}
