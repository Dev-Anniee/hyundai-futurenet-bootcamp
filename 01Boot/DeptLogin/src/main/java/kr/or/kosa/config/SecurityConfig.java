package kr.or.kosa.config;

import kr.or.kosa.security.CustomerAccessDeniedHandler;
import kr.or.kosa.security.CustomerDetailService;
import kr.or.kosa.security.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	@Autowired
	private CustomerDetailService customerDetailService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/admin", "/admin/**").hasRole("ADMIN")
				.requestMatchers("/manager", "/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/user", "/user/**").hasAnyRole("USER", "MANAGER", "ADMIN")
				.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
				.requestMatchers("/", "/guest", "/join", "/login", "/exception").permitAll()
				.anyRequest().permitAll());

		http.formLogin(form -> form
				.loginPage("/login")
				.loginProcessingUrl("/loginPro")
				.usernameParameter("username")
				.passwordParameter("pw")
				.successHandler(authenticationSuccessHandler())
				.permitAll());

		http.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true));

		http.userDetailsService(customerDetailService);
		http.exceptionHandling(exceptions -> exceptions.accessDeniedHandler(accessDeniedHandler()));

		return http.build();
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new LoginSuccessHandler();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomerAccessDeniedHandler();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
