package kr.or.kosa.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import kr.or.kosa.dto.CustomerUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		CustomerUser user = (CustomerUser) authentication.getPrincipal();

		log.info("login success username={}", user.getUsername());
		log.info("authorities={}", user.getAuthorities());

		if (hasRole(authentication, "ROLE_ADMIN")) {
			response.sendRedirect("/admin");
		} else if (hasRole(authentication, "ROLE_MANAGER")) {
			response.sendRedirect("/manager");
		} else {
			response.sendRedirect("/user");
		}
	}

	private boolean hasRole(Authentication authentication, String role) {
		return authentication.getAuthorities()
				.stream()
				.anyMatch(auth -> auth.getAuthority().equals(role));
	}
}
