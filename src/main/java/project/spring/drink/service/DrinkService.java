package project.spring.drink.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import project.spring.beans.CommonInterface;
import project.spring.drink.dao.DrinkDAO;
import project.spring.drink.vo.DrinkVO;

public interface DrinkService {

	// 1차 카테고리 가져오기
	public DrinkVO selectBigCategoryList();

	public List<HashMap> selectSmallCategoryList(String bigCategoryCode) throws Exception;

	public DrinkVO selectDrinkServiceInfo(String dkCode) throws SQLException;
	
	
}
