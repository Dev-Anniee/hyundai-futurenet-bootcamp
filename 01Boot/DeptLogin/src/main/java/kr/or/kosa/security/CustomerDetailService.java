package kr.or.kosa.security;

import kr.or.kosa.dto.CustomerUser;
import kr.or.kosa.dto.Users;
import kr.or.kosa.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userMapper.login(username);

		if (user == null) {
			throw new UsernameNotFoundException("요청하신 ID가 없습니다: " + username);
		}

		return new CustomerUser(user);
	}
}
