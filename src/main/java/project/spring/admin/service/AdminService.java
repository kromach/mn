package project.spring.admin.service;

import java.sql.SQLException;
import java.util.List;

import project.spring.beans.CommonInterface;

public interface AdminService extends CommonInterface{

	public List memberLIst(int start, int end) throws SQLException;
	
	public int memberCount() throws SQLException;
	
	public int drinkCount()throws SQLException;
	
	public List drinkList(int start, int end)throws SQLException;
	
	public int approveDrink(String dkCode, String check)throws SQLException;
	
}
