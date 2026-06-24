package kr.or.kosa.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.kosa.dto.UserAuth;
import kr.or.kosa.dto.Users;

@Mapper
public interface UserMapper {
	
	//로그인 사용자 인증
	Users login(String username);
	
	//회원가입
	int join(Users user) throws Exception;
	
	//회원가입 권한등록 (1.JAVA(Transaction) , 2.DB (Trigger): member 사용자가 insert > roll > ROLE_USER
	int insertAuth(UserAuth userAuth) throws Exception;
}
