package project.spring.drink.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.drink.dao.DrinkDAO;
import project.spring.drink.vo.DrinkVO;

@Service
public class DrinkServiceImpl implements DrinkService {
	
	private final DrinkDAO drinkDAO;
	
	@Autowired
	DrinkServiceImpl(DrinkDAO drinkDAO) {
		this.drinkDAO = drinkDAO;
	}

	@Override
	public DrinkVO selectBigCategoryList() {
		return null;
	}

	@Override
	public List<HashMap> selectSmallCategoryList(String bigCategoryCode) throws Exception {
		return null;
	}

	@Override
	public DrinkVO selectDrinkServiceInfo(String dkCode) throws SQLException {
		return drinkDAO.selectDrinkServiceInfo(dkCode);
	}

	@Override
	public HashMap selectCommentStarServiceInfo(String dkCode) throws SQLException {
		return drinkDAO.selectCommentStarServiceInfo(dkCode);
	}
	
}
