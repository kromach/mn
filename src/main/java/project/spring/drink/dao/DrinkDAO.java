package project.spring.drink.dao;

import java.util.HashMap;
import java.util.List;

public interface DrinkDAO {
	
	List<HashMap> selectSmallCategoryList(String bigCategoryCode) throws Exception;

}
