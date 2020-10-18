package project.spring.main.dao;

import java.util.List;
import java.util.Map;

import project.spring.beans.CommonInterface;
import project.spring.drink.vo.DrinkVO;
import project.spring.event.vo.EventVO;

public interface MainDAO extends CommonInterface {

	public List getProductInitial();
	public List<DrinkVO> getDrinkInitial();
	public List<EventVO> getEventInitial();

}
