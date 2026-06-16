package DI_04_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//인터페이스 다형성
		//MessageBean messageBean = new MessageBean_kr();
		/*
		MessageBean messageBean = new  MessageBean_en();
		messageBean.sayHello("hong");
		*/
		//위 코드 Spring 쉽게 접근 방법 (interface 활용)
	
		//1. Spring 컨테이너 만들기
		//2. 컨테이너 안에 필용한 객체를 생성하고 주입 (xml)
		//3. 컨테이너 안에서 필용한 객체 얻어내기 (getBean())
		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_04_Spring/DI_04.xml");
		
		//생성된 컨테이너 안에서 객체를 얻기 (getBean())
		//interface 설계
		MessageBean messageBean = context.getBean("m3", MessageBean.class);
		messageBean.sayHello();
		
		
	}

}
