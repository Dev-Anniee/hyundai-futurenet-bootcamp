package DI_02;

public class HelloApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//인터페이스 다형성
		//MessageBean messageBean = new MessageBean_kr();
		
		MessageBean messageBean = new  MessageBean_en();
		messageBean.sayHello("hong");
		
		//위 코드 Spring 쉽게 접근 방법 (interface 활용)
		 
	}

}
