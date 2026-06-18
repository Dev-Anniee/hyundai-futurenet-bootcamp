package dao;

import java.sql.SQLException;
import org.apache.ibatis.annotations.Mapper;
import vo.Member;

@Mapper
public interface MemberDao {
	public Member getMember(String uid) throws ClassNotFoundException, SQLException;
	public int insert(Member member) throws ClassNotFoundException, SQLException;
}
