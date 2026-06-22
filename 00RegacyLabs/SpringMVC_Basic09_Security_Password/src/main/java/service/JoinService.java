package service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDao;
import vo.Member;

@Service
public class JoinService {

	@Autowired
	private SqlSession sqlSession;

	public int insertMember(Member member) throws ClassNotFoundException, SQLException {
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		int result = dao.insert(member);
		if (result > 0) {
			dao.insertRole(member.getUserid());
		}
		return result;
	}
}
