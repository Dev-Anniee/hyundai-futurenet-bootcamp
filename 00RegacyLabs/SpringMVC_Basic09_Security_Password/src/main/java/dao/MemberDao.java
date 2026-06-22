package dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;

import vo.Member;

public interface MemberDao {

	public Member getMember(String userid) throws ClassNotFoundException, SQLException;

	public int insert(Member member) throws ClassNotFoundException, SQLException;

	public int insertRole(@Param("userid") String userid) throws ClassNotFoundException, SQLException;

	public int updateMember(Member member);
}
