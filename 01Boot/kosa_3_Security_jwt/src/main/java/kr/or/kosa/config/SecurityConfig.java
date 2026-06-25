package kr.or.kosa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/*
		1. in memory
		2. jdbc (설정에서 2개의 쿼리) : id,pwd,enabled  :  auth
		3. mybatis(jpa) : 사용자 정의 UserDetailService (mybatis 사용)
		4. jwt 인증 방식 (Spring 기본 제공 기능 폼 로그인 방식을 사용하지 않는다)
	*/

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception{
		
		//기존
		//http.formLogin().disable(); security 5.x.x 버전에서 
		http.formLogin((login) -> login.disable());  //람다 DSL 표기 6.x.x 강제화
		//formlogin 방식을 사용하지 않을 거야
		http.httpBasic((basic) -> basic.disable());
		//spring security 제공하는 자동화 비활성화
		http.csrf(csrf -> csrf.disable());
		
		//jwt 사용 -> session 통한 정보를 관리하지 않을 거야
		http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		
		// REST API 방식 서버를 구동할거야 
		// POST   /api/login  비동기  REST 방식 > react , vue
		
		/*
		 http.httpBasic(new Customizer<HttpBasicConfigurer<HttpSecurity>>() {
    		@Override
    		public void customize(HttpBasicConfigurer<HttpSecurity> basic) {
        	basic.disable();
    		}
		}); 
		  
		 */

		return http.build();
	}
	
	
	//로그인 성공시 객체 반환
	
}
