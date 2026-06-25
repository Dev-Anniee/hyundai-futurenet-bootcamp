package kr.or.kosa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import kr.or.kosa.constants.SecurityConstants;
import kr.or.kosa.domain.AuthenticationRequest;
import kr.or.kosa.prop.JwtProps;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoginController {

	@Autowired
	private JwtProps jwtProps;

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest request){

		//POSTMAN
		//method > post  , localhost:8080/login   raw(JSON) {"username":"hong", "password":"1004"}
		//DB작업에서 회원이라면
		//토큰을 발급
		String username = request.getUsername();
		String password = request.getPassword();

		//DB 연결  select 해서  회원유무 확인 ..... 회원이라면

		//토큰 payload (ID, ROLE)
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_USER");
		roles.add("ROLE_ADMIN");

		//재료
		//1. header
		//2. payload (username, roles , 만료일)
		//3. secretkey 식별값

		//토큰생성

		//1. 서명
		String secretkey = jwtProps.getSecretkey();

		//서명이 사용될 때는 (토큰 byte[] 변환)
		byte[] singingkey = jwtProps.getSecretkey().getBytes(); //실제 토큰에 들어가는 값

		/*
		토큰 만들기
		1. Keys.hmacShaKeyFor(signingKey) 키 제공하기
		2. Jwts.SIG.HS512  알고리즘 설정 정보
		3. SecurityConstants.TOKEN_TYPE >> public static final String TOKEN_TYPE="JWT";
		4. expiration 소멸타임 (memory , file 쿠키) : file 쿠키 (소멸시간) ...session 에 의존하는 memory cookie
		   expiration(new Date( System.currentTimeMillis() + 1000*60*60*24*1))


		설명
		private static final String SECRET = "abcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxy";
		HS512 알고리즘으로 암호화하여 Signature를 만듭니다.

		대표적인 알고리즘은

		알고리즘	의미
		HS256	HMAC SHA256
		HS384	HMAC SHA384
		HS512	HMAC SHA512
		SHA512를 사용하면 SHA256보다 더 긴 해시값이 생성됩니다.

		Header + Payload + Secret Key  조합해서 HS512 알고리즘을 사용해서 암호화 값을 생성
		Signature = HMACSHA512( Header + Payload, SecretKey )

           사용자	    Payload	     Secret Key	      Signature
			hong	uid=hong	  동일	          A123...
            kim	    uid=kim	      동일	          X999...
            lee	    uid=lee	      동일	          K777...

		*/
		String jwt = Jwts.builder()
				.signWith(Keys.hmacShaKeyFor(singingkey),Jwts.SIG.HS512)
				.header().add("typ",SecurityConstants.TOKEN_TYPE).and()
				.expiration(new Date(System.currentTimeMillis() + 1000*60*60*24*1)) //24시간
				.claim("uid", username)
				.claim("rol", roles)
				.compact();
		log.info("[[[  jwt 완성본    ]]]" + jwt);

		return new  ResponseEntity<String>(jwt,HttpStatus.OK);
		/*
		[ 기초 용어 ]

		클레임(Claim) 토큰 기반의 인증
		클레임이란 [[ 사용자 정보나 데이터 속성 ]]등을 의미한다.
		그래서 [[ 클레임 토큰 이라고 하면 토큰 안에 사용자 정보나 데이터 속성들을 담고있는 토큰 ]]이라 생각하면 되고,
		이런 클레임을 기반한 토큰 중 가장 대표적인 것이 JWT가 있다

		Bearer	토큰을 가진 자격이 인증됨을 의미
		Bearer는 "운반자" 또는 "소지자"라는 뜻입니다.
        즉, **"이 토큰을 가진 자(Bearer)는 인증된 사용자로 간주한다"**는 의미

        Authorization 헤더는 여러 인증 방식이 가능하기 때문에,
        Bearer, Basic, Digest 등 어떤 인증 방식을 사용하는지 구분이 필요합니다.


        클라이언트(Client)와 서버(Server)간의 통신을   상태유지(Stateful) 하느냐, 상태유지하지않음(Stateless) 으로 하느냐


		Stateless (무상태)
		무상태는 반대로 클라이언트와 서버 관계에서 [[ 서버가 클라이언트의 상태를 보존하지 않음 ]]을 의미한다.

		Stateless 구조에서
		서버는 단순히 요청이 오면 응답을 보내는 역할만 수행하며, [[상태 관리는 전적으로 클라이언트에게 책임]]이 있는 것이다.
		즉, 클라이언트와 서버간의 통신에 필요한 [[모든 상태 정보들은 클라이언트에서 가지고 있다가]] 서버와 통신할때
		데이터를 실어 보내는 것이 [[무상태 구조이다]]

	*/
	 /*
	  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3ODI0NTUyNTQsInVpZCI6ImhvbmciLCJyb2wiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdfQ.tUc5_Xt0JbMYLKpiPjGlR8NOxP5mNqetCe6y1yUpBE-oJorOyo90a_cosxIIlIgAf4eyAAJiSs1KhEVfshiJyA
	 */


	}
	@GetMapping("/user/info")
	public ResponseEntity<?> userInfo(@RequestHeader(name="Authorization") String header){

		//header => `Bearer ${localStorage.getItem('token')}`
		log.info("header 정보");
		log.info("Authorization : " + header);

		//서명
		String secretkey = jwtProps.getSecretkey();
		//서명이 사용될 때는 (토큰 byte[] 변환)
		byte[] signingkey = jwtProps.getSecretkey().getBytes(); //실제 토큰에 들어가는 값

		//Authorization > Bearer dfaewgtawew121dsfas  전송된 데이터
		//public static final String TOKEN_PREFIX="Bearer ";
		String jwt = header.replace(SecurityConstants.TOKEN_PREFIX, "");

		//토큰 해석
		Jws<Claims> parsedToken = Jwts.parser()
				.verifyWith(Keys.hmacShaKeyFor(signingkey))
				.build()
				.parseSignedClaims(jwt);

		log.info("parsedToken : " + parsedToken);

		String username = parsedToken.getPayload().get("uid").toString();
		log.info("username : " + username);

		Claims claims = parsedToken.getPayload(); //데이터 username , rols

		Object roles = claims.get("rol");
		log.info("roles : " + roles );



		return new ResponseEntity<String>(parsedToken.toString(), HttpStatus.OK);
	}

}
