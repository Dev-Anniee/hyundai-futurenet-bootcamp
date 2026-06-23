package kr.or.kosa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	//@Configuration 설정 파일 .... xml 처럼 빈 생성 , 주입
	//method return @Bean
	
	/* 
	<security:http auto-config="true">
		<security:csrf disabled="true" />
		<security:intercept-url pattern="/customer/noticeDetail.do"  access="hasRole('ROLE_USER')"/>
		<security:intercept-url pattern="/customer/noticeReg.do"     access="hasRole('ROLE_ADMIN')"/>
	</security:http>
	<security:authentication-manager>
			<security:authentication-provider>
					<security:user-service>
						<security:user name="hong"  password="1004" authorities="ROLE_USER"/>
						<security:user name="admin" password="1004" authorities="ROLE_USER,ROLE_ADMIN"/>
					</security:user-service>
			</security:authentication-provider>
	</security:authentication-manager>
    */	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception{
		
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/admin/**").hasRole("ADMIN")  //ROLE_USER, ROLE_ADMIN
				                               .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
				                               .requestMatchers("/css/**" , "/js/**" , "/images/**").permitAll()
				                               .requestMatchers("/" , "/**").permitAll()
				                               .anyRequest().authenticated()
				                   ).formLogin(form -> form.permitAll()
        			               ).logout(logout -> logout.permitAll());
		
								; //설정한것 말고 다른 경로는 인증된 사용자 허락
		
		
		
		return http.build(); //Bean 객체 주소를 리턴
		
		//인증과 권한 (in-memory) 방식 (사용자 , 패스워드 고정)
	}
	
	
	/*
	<security:user-service>
						<security:user name="hong"  password="1004" authorities="ROLE_USER"/>
						<security:user name="admin" password="1004" authorities="ROLE_USER,ROLE_ADMIN"/>
	</security:user-service> 
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("1004"))
				.roles("USER")
				.build();
		
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("1007"))
				.roles("USER","ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user,admin);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
