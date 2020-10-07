package project.spring.admin.dao;

import java.sql.SQLException;
import java.util.List;

import project.spring.beans.CommonInterface;

public interface AdminDAO extends CommonInterface {

	public List memberList(int start, int end) throws SQLException;
	
	public int memberCount() throws SQLException;
}
