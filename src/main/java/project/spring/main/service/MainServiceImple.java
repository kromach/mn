package project.spring.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.drink.vo.DrinkVO;
import project.spring.event.vo.EventVO;
import project.spring.main.dao.MainDAO;

@Service
public class MainServiceImple implements MainService{
	
	@Autowired
	private MainDAO mainDAOimple;

	@Override
	public int insertItem() {
		return 0;
	}
	@Override
	public int updateItem() {
		return 0;
	}
	@Override
	public int deleteItem() {
		return 0;
	}
	@Override
	public int insertItem(Object obj) {
		return 0;
	}
	@Override
	public int updateItem(Object obj) {
		return 0;
	}
	@Override
	public int deleteItem(Object obj) {
		return 0;
	}
	
	@Override
	public List getProductInitial(int index) {
		return mainDAOimple.getProductInitial(index);
	}
	@Override
	public List<DrinkVO> getDrinkInitial(int index) {
		return mainDAOimple.getDrinkInitial(index);
	}
	@Override
	public List<EventVO> getEventInitial(int index) {
		return mainDAOimple.getEventInitial(index);
	}
	@Override
	public List getProductInitial(int index,String search) {
		return mainDAOimple.getProductInitial(index,search);
	}
	@Override
	public List<DrinkVO> getDrinkInitial(int index,String search) {
		return mainDAOimple.getDrinkInitial(index,search);
	}
	@Override
	public List<EventVO> getEventInitial(int index,String search) {
		return mainDAOimple.getEventInitial(index,search);
	}
}
