package kr.or.kosa.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.kosa.domain.User;
import kr.or.kosa.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;

// 서버 시작 시 테스트용 초기 계정을 자동으로 넣어준다. (이미 있으면 건너뜀)
//  - admin / 12345678  (ROLE_ADMIN)
//  - hong  / 12345678  (ROLE_USER)
// 비밀번호는 bcrypt 로 암호화해서 저장하므로 로그인 시 그대로 검증됨.
@Slf4j
@Configuration
public class DataInitializer {

	@Bean
	public ApplicationRunner initUsers(UserMapper userMapper, PasswordEncoder passwordEncoder) {
		return args -> {
			try {
				seed(userMapper, passwordEncoder, "admin", "12345678", "관리자", "ROLE_ADMIN");
				seed(userMapper, passwordEncoder, "hong", "12345678", "홍길동", "ROLE_USER");
			} catch (Exception e) {
				// user2 테이블이 아직 없으면 여기로 옴
				log.warn("초기 계정 생성 실패 - user2 테이블이 있는지 확인하세요. (원인: {})", e.getMessage());
			}
		};
	}

	private void seed(UserMapper userMapper, PasswordEncoder encoder,
			String username, String rawPassword, String name, String role) {
		if (userMapper.findByUsername(username) != null) {
			return; // 이미 존재
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword(encoder.encode(rawPassword));
		user.setName(name);
		user.setRole(role);
		userMapper.saveUser(user);
		log.info("초기 계정 생성: {} / {} ({})", username, rawPassword, role);
	}
}
