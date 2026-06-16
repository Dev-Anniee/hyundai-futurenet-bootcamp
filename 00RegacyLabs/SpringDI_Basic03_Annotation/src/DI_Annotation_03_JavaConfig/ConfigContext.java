package DI_Annotation_03_JavaConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration     //java 파일은  DI.xml 과 같은 역할 (객체의 생성 과 조립) > Spring IOC 
public class ConfigContext {

	//xml  <bean id="user" class="...User"
	//xml bean 설정은 java 코드에서 함수를 만들고 함수에서 객체를 리턴
	
	@Bean
	public User user() {
		return new User();
	}
	
	//xml <bean id="user2"  class="...User2"
	@Bean
	public User2 user2() {
		return new User2();
	}
}
