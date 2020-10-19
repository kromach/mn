package project.spring.main.dao;

import java.util.List;
import java.util.Map;

import project.spring.beans.CommonInterface;
import project.spring.drink.vo.DrinkVO;
import project.spring.event.vo.EventVO;

public interface MainDAO extends CommonInterface {

	public List getProductInitial(int index);
	public List<DrinkVO> getDrinkInitial(int index);
	public List<EventVO> getEventInitial(int index);

}
