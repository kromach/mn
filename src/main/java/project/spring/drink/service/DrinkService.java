package project.spring.drink.service;

import java.util.HashMap;
import java.util.List;

import project.spring.drink.vo.DrinkVO;

public interface DrinkService {

	// 1차 카테고리 가져오기
	public DrinkVO selectBigCategoryList();

	public List<HashMap> selectSmallCategoryList(String bigCategoryCode) throws Exception;
	
	
	
}
