package service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aop.ServiceLog;
import dao.MemberDao;
import lombok.RequiredArgsConstructor;
import vo.Member;

@Service
@RequiredArgsConstructor  //롬복
public class MemberService {

	/*
	private SqlSession sqlSession;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	*/
	private final MemberDao MemberMapper;
	
	@ServiceLog
	public Member getMember(String userid) {
		
		//MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		//memberDao.getMember(userid);
		
		Member member = null;
		try {
			   member = MemberMapper.getMember(userid);
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		return member;
	}
	
	@ServiceLog
	public String insert(Member member) {
		try {
			MemberMapper.insert(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/index.do";
	}
}
