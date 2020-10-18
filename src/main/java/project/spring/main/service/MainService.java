package project.spring.main.service;

import java.util.List;
import java.util.Map;

import project.spring.beans.CommonInterface;
import project.spring.drink.vo.DrinkVO;
import project.spring.event.vo.EventVO;

public interface MainService extends CommonInterface{

	public List getProductInitial(int index);
	public List<DrinkVO> getDrinkInitial(int index);
	public List<EventVO> getEventInitial(int index);

}
