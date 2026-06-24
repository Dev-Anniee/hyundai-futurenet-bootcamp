package kr.or.kosa.config;

import javax.sql.DataSource;
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
import kr.or.kosa.security.CustomerAccessDeniedHandler;
import kr.or.kosa.security.CustomerDetailService;
import kr.or.kosa.security.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final DataSource dataSource;
	
	//mybatis 사용자 정의 방식 구현 (자동화 ... service 재정의)
	//UserDetailsService.loadUserByUsername() 호출  내 마음대로 (mybatis) DB작업
	
	// 그럼 이제 여기서 사용자 정의 방식으로 설정된 ... 
	// CustomerDetailService implements UserDetailsService
	
	@Autowired
	private CustomerDetailService customerDetailService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception{
		
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/admin/**").hasRole("ADMIN")  //ROLE_USER, ROLE_ADMIN
                .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .requestMatchers("/css/**" , "/js/**" , "/images/**").permitAll()
                .requestMatchers("/" , "/**").permitAll()
                .anyRequest().permitAll());
		
		http.logout(logout -> logout
                .logoutUrl("/logout")     //로그아웃 요청을 받을 URL
                .logoutSuccessUrl("/")    //로그아웃 성공 후 이동할 URL
                .deleteCookies("JSESSIONID")  //쿠키 삭제
                .invalidateHttpSession(true)); //세션 객체 삭제			
		
		
		 //    /login → 로그인 페이지를 보여주는 Controller는 구현해야 함
		 //    /loginPro → Spring Security가 자동 처리하므로 구현하지 않음
		 
		/*
		  1. action="/loginPro" 
		        <form action="/loginPro" method="post"> 컨트롤로는 만들어 않아요
		        
		  2. UsernamePasswordAuthenticationFilter 가 요청을 감지

          3.  
             String username = request.getParameter("id");
             String password = request.getParameter("pw");

          4.  
             UsernamePasswordAuthenticationToken token =
             new UsernamePasswordAuthenticationToken(username, password);

          5. authenticationManager.authenticate(token);
          
          6. DaoAuthenticationProvider
          
          7. UserDetailsService
             DB작업 
             ...
		
		
		  */
		http.formLogin(form -> form
				                   .loginPage("/login") //커스텀 로그인 페이지 요청 경로
				                   .loginProcessingUrl("/loginPro") //로그인 처리 처리 경로  action=/loginPro
				                   //.defaultSuccessUrl(null)  //로그인 성공시 이동 경로
				                   .usernameParameter("id")  // <input type="text" name="id"
				                   .passwordParameter("pw")
				                   .successHandler(authenticationSuccessHandler())
				                   .permitAll());  //모든 사용자에게 로그인 페이지 접근 허용
		   
		http.userDetailsService(customerDetailService); //사용자 인증 방식으로 전환 (mybatis) 사용하는
		//인증이 실패된 경우 ...
		http.exceptionHandling(exceptions -> exceptions.accessDeniedHandler(accessDeniedHandler()));
		//http.csrf(csrf -> csrf.disable()) 비활성화 
		return http.build(); //Bean 객체 주소를 리턴
		
		
	}
	
	
	//로그인 성공시 객체 반환
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new LoginSuccessHandler();
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomerAccessDeniedHandler();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
