package project.spring.dao;
import java.sql.SQLException;
import project.spring.vo.MemberDTO;

public interface MemberDAO {
	public void insertMember(MemberDTO dto) throws SQLException;
	public int idPwCheck(MemberDTO dto) throws SQLException;
	public MemberDTO selectMember(String id) throws SQLException;
}
