package service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDao;
import vo.Member;

@Service
public class MemberService {
	private SqlSession sqlsession;

	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}

	public Member getMember(String userid) {
		Member member = null;
		try {
			MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
			member = memberdao.getMember(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	public void updateMember(Member member) {
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		int result = dao.updateMember(member);
		if (result > 0) {
			System.out.println("update success");
		} else {
			System.out.println("update failed");
		}
	}
}
