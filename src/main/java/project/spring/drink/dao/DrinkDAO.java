package project.spring.drink.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import project.spring.beans.CommonInterface;
import project.spring.drink.vo.CommentVO;
import project.spring.drink.vo.DrinkVO;

public interface DrinkDAO {
	
	List<HashMap> selectSmallCategoryList(String bigCategoryCode) throws Exception;

	DrinkVO selectDrinkServiceInfo(String dkCode) throws SQLException;

	HashMap selectCommentStarServiceInfo(String dkCode) throws SQLException;

	List<HashMap> selectTagCloudServiceInfo(String dkCode) throws SQLException;

	List<CommentVO> selectCommentServiceList(String dkCode) throws SQLException;
}
