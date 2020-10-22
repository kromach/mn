package project.spring.admin.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import project.spring.beans.CommonInterface;

public interface AdminDAO extends CommonInterface {

	public List memberList(int start, int end) throws SQLException;
	
	public List memberList(int start, int end, Map map) throws SQLException;
	
	public int memberCount() throws SQLException;
	
	public int memberCount(Map map)throws SQLException;
	
	public int drinkCount()throws SQLException;
	
	public List drinkList(int start, int end)throws SQLException;
	
	public int approveDrink(String dkCode, String check)throws SQLException;
	
}
