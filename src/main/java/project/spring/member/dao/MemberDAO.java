package project.spring.member.dao;

import java.sql.SQLException;
import java.util.List;

import project.spring.member.vo.MemberDTO;

public interface MemberDAO {
	//회원가입
	public void insertMember(MemberDTO dto) throws SQLException;
	//아이디 비밀번호확인
	public int idPwCheck(MemberDTO dto) throws SQLException;
	//한명의 데이터 조회
	public MemberDTO selectMember(String id) throws SQLException;
	//전체 회원 데이터 조회
	public List selectAll() throws SQLException;
	//회원 데이터 수정
	public void UpdateMember(MemberDTO dto) throws SQLException;
	//회원 데이터 삭제
	public void deleteMember(String id) throws SQLException;
	//아이디 사용가능여부 확인
	public int inAvailCheck(String id) throws SQLException;
}
