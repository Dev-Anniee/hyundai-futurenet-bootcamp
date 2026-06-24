package kr.or.kosa.service;

import kr.or.kosa.dto.UserAuth;
import kr.or.kosa.dto.Users;

public interface UserService {
	
	Users login(String username);
	
	int join(Users user) throws Exception;
	
	int insertAuth(UserAuth userAuth) throws Exception;
}
