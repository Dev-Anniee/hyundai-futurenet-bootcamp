package kr.or.kosa.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.dto.CustomerUser;
import lombok.extern.slf4j.Slf4j;

//로그인 성공 했을경우 필요한 논리가 있다면
//로그인 성공하면 자동으로 채팅 설정
//로그인 성공하면 부가적인 설정 ...

//성공하면 로그작업 (.....) 

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		log.info("로그인 인증 성공");
		
		CustomerUser user = (CustomerUser)authentication.getPrincipal();
		
		//필요한 작업
		log.info("아이디 : " + user.getUsername());
		log.info("패스워드 : " + user.getPassword());
		log.info("권한 : " + user.getAuthorities());
		
		//로그인 성공 후 직접 이동 경로 지정한 경로 지정
		//  .defaultSuccessUrl(null) 무시 됩니다
		// 로그인 성공시 동작은 전적으로 LoginSuccessHandler 제어 합니다
		// .defaultSuccessUrl("/") 로그인 성공시 이동할 경로 의미가 없다
		
		//response.sendRedirect("/admin"); //원하는데 설정
		
	    if (hasRole(authentication, "ROLE_ADMIN")) {
            response.sendRedirect("/admin");
        } else if (hasRole(authentication, "ROLE_MANAGER")) {
            response.sendRedirect("/manager");
        } else {
            response.sendRedirect("/user");
        }	
		
		 /*
	     LoginSuccessHandler 언제 쓰나요?
	     		사용자 로그인 시 로그 기록 저장
	     		사용자 권한에 따라 리다이렉트 경로 분기
	     		최초 로그인/이후 로그인 구분
	     		사용자별 맞춤 리다이렉션
	     if (hasRole(authentication, "ROLE_ADMIN")) {
	 			response.sendRedirect("/admin");
				} else {
	 			response.sendRedirect("/user/home");
				}		
	     		
	     		
	     */		
		
	}
	  private boolean hasRole(Authentication authentication, String role) {

	        return authentication.getAuthorities()
	                             .stream()
	                             .anyMatch(auth ->
	                              auth.getAuthority().equals(role));
	    }

}
