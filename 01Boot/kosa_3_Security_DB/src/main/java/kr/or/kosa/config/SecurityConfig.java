package kr.or.kosa.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final DataSource dataSource;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception{

		http.authorizeHttpRequests(auth -> auth.requestMatchers("/admin", "/admin/**").hasRole("ADMIN")  //ROLE_USER, ROLE_ADMIN
				.requestMatchers("/user", "/user/**").hasAnyRole("USER","ADMIN")
				.requestMatchers("/css/**" , "/js/**" , "/images/**").permitAll()
				.requestMatchers("/", "/join").permitAll()
				.anyRequest().authenticated()
		).formLogin(form -> form.permitAll()
		).logout(logout -> logout.permitAll());

		; //설정한것 말고 다른 경로는 인증된 사용자 허락



		return http.build(); //Bean 객체 주소를 리턴

		//인증과 권한 (in-memory) 방식 (사용자 , 패스워드 고정)
	}

	@Bean
	public UserDetailsService userDetailsService() {
		//DB연결 - 회원 정보
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

		String userquery = "SELECT user_id AS username, user_pw AS password, enabled FROM users WHERE user_id = ?";
		String rolequery = "select user_id, auth from user_auth where user_id=?";

		manager.setUsersByUsernameQuery(userquery);
		manager.setAuthoritiesByUsernameQuery(rolequery);
		return manager;
	}
	@Bean
	public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
