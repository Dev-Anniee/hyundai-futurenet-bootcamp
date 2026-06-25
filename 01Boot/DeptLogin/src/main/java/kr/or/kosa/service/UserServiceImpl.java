package kr.or.kosa.service;

import java.util.List;
import kr.or.kosa.dto.UserAuth;
import kr.or.kosa.dto.Users;
import kr.or.kosa.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Users login(String username) {
		return userMapper.login(username);
	}

	@Override
	@Transactional
	public int join(Users user) throws Exception {
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		int result = userMapper.join(user);

		if (result > 0) {
			UserAuth userAuth = new UserAuth();
			userAuth.setUserId(user.getUserId());
			userAuth.setAuth("ROLE_USER");
			result += userMapper.insertAuth(userAuth);
		}

		return result;
	}

	@Override
	public int insertAuth(UserAuth userAuth) throws Exception {
		return userMapper.insertAuth(userAuth);
	}

	@Override
	public List<Users> findAllUsers() {
		return userMapper.findAllUsers();
	}

	@Override
	public int grantRole(Long userId, String auth) throws Exception {
		if (!"ROLE_MANAGER".equals(auth) && !"ROLE_ADMIN".equals(auth)) {
			throw new IllegalArgumentException("관리자가 부여할 수 있는 권한은 ROLE_MANAGER 또는 ROLE_ADMIN 입니다.");
		}

		UserAuth userAuth = new UserAuth();
		userAuth.setUserId(userId);
		userAuth.setAuth(auth);
		return userMapper.insertAuth(userAuth);
	}
}
