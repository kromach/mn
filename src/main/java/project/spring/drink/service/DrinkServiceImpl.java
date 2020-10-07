package project.spring.drink.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.drink.dao.DrinkDAO;
import project.spring.drink.vo.CommentVO;
import project.spring.drink.vo.DrinkVO;

@Service
public class DrinkServiceImpl implements DrinkService {
	
	private final DrinkDAO drinkDAO;
	
	@Autowired
	DrinkServiceImpl(DrinkDAO drinkDAO) {
		this.drinkDAO = drinkDAO;
	}

	@Override
	public List<HashMap> selectBigCategoryList() throws SQLException {
		return drinkDAO.selectBigCategoryList();
	}

	@Override
	public List<HashMap> selectSmallCategoryList(String bigCategoryCode) throws SQLException {
		return drinkDAO.selectSmallCategoryList(bigCategoryCode);
	}

	@Override
	public HashMap selectItemValuesInfo(String bigCategoryCode) throws SQLException {
		return drinkDAO.selectItemValuesInfo(bigCategoryCode);
	}

	@Override
	public DrinkVO selectDrinkServiceInfo(String dkCode) throws SQLException {
		return drinkDAO.selectDrinkServiceInfo(dkCode);
	}

	@Override
	public HashMap selectCommentStarServiceInfo(String dkCode) throws SQLException {
		return drinkDAO.selectCommentStarServiceInfo(dkCode);
	}

	@Override
	public List<HashMap> selectTagCloudServiceInfo(String dkCode) throws SQLException {
		return drinkDAO.selectTagCloudServiceInfo(dkCode);
	}

	@Override
	public List<CommentVO> selectCommentServiceList(String dkCode) throws SQLException {
		return drinkDAO.selectCommentServiceList(dkCode);
	}
	
}
