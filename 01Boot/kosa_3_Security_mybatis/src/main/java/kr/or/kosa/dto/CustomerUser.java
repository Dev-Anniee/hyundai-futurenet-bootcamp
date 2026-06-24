package kr.or.kosa.dto;


import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomerUser implements UserDetails {

	//사용자 정보
	private Users user;
	
	public CustomerUser(Users user) {
		this.user = user;
	}
	
	
	//getAuthorities 권한 정보를 보는 함수
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return user.getAuthList()  //권한 ROLE_USER , ROLE_ADMIN , ROLE_BackUpOperator
				   .stream()
				   .map((auth) -> new SimpleGrantedAuthority(auth.getAuth()))
				   .collect(Collectors.toList());
				    //배열 생성 [UserAuth][UserAuth]
					
					//권한 정보 리스트를 만들어서 리턴 (GrantedAuthority 객체형식 타입으로 ...)
					//권한 정보를 수집해서 리턴 .....
		
			        //1.user.getAuthList() >>>   List<UserAuth> authList; 
					//2.map 통해서 UserAuth 객체안에서 권한정보만 추출
					//3. [ROLE_USER][ROLE_ADMIN]
	}

	@Override
	public String getPassword() {
		return user.getUserPw();
	}

	@Override
	public String getUsername() {
		return user.getUserId();
	}
	
	//필요한 함수 추가 구현 가능 ...
	//...
}
