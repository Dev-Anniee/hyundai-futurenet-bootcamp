package kr.or.kosa.security;

import kr.or.kosa.controller.HomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.kosa.dto.CustomerUser;
import kr.or.kosa.dto.Users;
import kr.or.kosa.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

/*
 * 
UserDetailsService 는 사용자 이름을 받아(userid) 사용자 정보를 반환(UserDetails)를 구현하는 객체로 반환(User)
인증에 대한 처리를 개발자가 원하는 대로 UserDetailsService 재정의 : jpa , mybatis 원하는 방법
loadUserByUsername 재정의 사용

    @Override
	public Users login(String username) {
		Users user = userMapper.login(username);
		return user;
	}
사용해서 DB에서 정보를 가져오기

*/
@Service
public class CustomerDetailService implements UserDetailsService {

	
	@Autowired
	private UserMapper userMapper;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//JPA
		//Mybatis > userMapper 사용해서 처리
	 System.out.println("***************** :" + username +"***********************"); 	
	 Users user = userMapper.login(username); // select 자동 매핑
	 
	 if(user == null) {
		 throw new UsernameNotFoundException("요청하신 ID 없습니다" + username);
	 }
	 
	 //UserDetails 인터페이스 > 구현하는 객체를 하나 생성 그 주소를 리턴 
	  CustomerUser customerUser = new CustomerUser(user);
	  //public class CustomerUser implements UserDetails
	  //함수 : 사용자이름, 비번 , 권한 함수를 직접 구현 
	  // getAuthorities() , getUsername()
		return customerUser;
	}

}





