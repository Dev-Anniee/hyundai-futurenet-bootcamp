package kr.or.kosa.service;

import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.kosa.domain.User;
import kr.or.kosa.dto.LoginResponse;
import kr.or.kosa.dto.SignupRequest;
import kr.or.kosa.dto.UserResponse;
import kr.or.kosa.exception.DuplicateUsernameException;
import kr.or.kosa.jwt.JwtProvider;
import kr.or.kosa.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;

// 회원가입 / 로그인 / 내 정보 조회 비즈니스 로직
@Slf4j
@Service
public class AuthService {

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	public AuthService(UserMapper userMapper, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
	}

	// 회원가입: 아이디 중복 체크 → 비밀번호 암호화 → 저장 (기본 권한 ROLE_USER)
	public UserResponse signup(SignupRequest request) {
		if (userMapper.findByUsername(request.username()) != null) {
			throw new DuplicateUsernameException("이미 사용 중인 아이디입니다: " + request.username());
		}

		User user = new User();
		user.setUsername(request.username());
		user.setPassword(passwordEncoder.encode(request.password())); // bcrypt 암호화
		user.setName(request.name());
		user.setRole("ROLE_USER");

		userMapper.saveUser(user);
		log.info("회원가입 완료: {}", request.username());

		// 저장 후 id 포함해서 다시 조회하여 응답
		return UserResponse.from(userMapper.findByUsername(request.username()));
	}

	// 로그인: 아이디/비밀번호 검증 → JWT 발급
	public LoginResponse login(String username, String rawPassword) {
		User user = userMapper.findByUsername(username);

		if (user == null || !passwordEncoder.matches(rawPassword, user.getPassword())) {
			throw new BadCredentialsException("아이디 또는 비밀번호가 올바르지 않습니다.");
		}

		List<String> roles = List.of(user.getRole());
		String token = jwtProvider.createToken(user.getUsername(), roles);
		log.info("로그인 성공: {} (roles={})", username, roles);

		return LoginResponse.of(user, token);
	}

	// 내 정보 조회 (JWT 로 인증된 사용자)
	public UserResponse getByUsername(String username) {
		User user = userMapper.findByUsername(username);
		if (user == null) {
			throw new BadCredentialsException("사용자를 찾을 수 없습니다: " + username);
		}
		return UserResponse.from(user);
	}
}
