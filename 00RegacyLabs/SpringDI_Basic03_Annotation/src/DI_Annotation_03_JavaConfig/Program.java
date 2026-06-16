package DI_Annotation_03_JavaConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Program {

	public static void main(String[] args) {
		
		//설정 파일이 java 인경우
		ApplicationContext context = new  AnnotationConfigApplicationContext(ConfigContext.class);
		
		User user = context.getBean("user", User.class);
		User2 user2 = context.getBean("user2", User2.class);

		user.userMethod();
		user2.userMethod();
	}

}
