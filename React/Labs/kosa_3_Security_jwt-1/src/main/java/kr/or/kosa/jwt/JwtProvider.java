package kr.or.kosa.jwt;

import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import kr.or.kosa.constant.SecurityConstants;
import kr.or.kosa.prop.JwtProps;

// JWT 토큰 생성/검증 로직을 한 곳으로 모은 컴포넌트
// (LoginController, AuthController, JwtAuthenticationFilter 가 공통으로 사용)
@Component
public class JwtProvider {

	// 토큰 유효시간: 24시간
	public static final long TOKEN_VALIDITY_MILLIS = 1000L * 60 * 60 * 24;

	private final JwtProps jwtProps;

	public JwtProvider(JwtProps jwtProps) {
		this.jwtProps = jwtProps;
	}

	// 서명키 (application.properties 의 secret-key 사용)
	private SecretKey signingKey() {
		byte[] keyBytes = jwtProps.getSecretkey().getBytes();
		return Keys.hmacShaKeyFor(keyBytes);
	}

	// 토큰 생성: payload 에 사용자ID(uid), 권한목록(rol), 만료시간 담기
	public String createToken(String username, List<String> roles) {
		return Jwts.builder()
				.signWith(signingKey(), Jwts.SIG.HS512)
				.header().add("typ", SecurityConstants.TOKEN_TYPE).and()
				.expiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_MILLIS))
				.claim("uid", username)
				.claim("rol", roles)
				.compact();
	}

	// 토큰 검증 + 파싱 (서명/만료가 잘못되면 예외 발생)
	public Jws<Claims> parse(String jwt) {
		return Jwts.parser()
				.verifyWith(signingKey())
				.build()
				.parseSignedClaims(jwt);
	}

	// 토큰에서 사용자ID(uid) 추출
	public String getUsername(String jwt) {
		return parse(jwt).getPayload().get("uid").toString();
	}
}
