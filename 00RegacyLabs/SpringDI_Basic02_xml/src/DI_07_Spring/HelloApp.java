package DI_07_Spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {
  public static void main(String[] args) {
	
	  /*
	 ProtocolHandler handler = new ProtocolHandler();
	 //JAVA API 제공 Collection 사용
	 
	 List<MyFilter> list = new ArrayList<>(); // new ... JAVA API 제공
	 list.add(new EncFilter());
	 list.add(new ZipFilter());
	 list.add(new HeaderFilter());
	 
	 
	 handler.setFilters(list); //주입
	 System.out.println(handler.filter_Length());
	 */
	  
	  
	  ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_07_Spring/DI_07.xml");
	  
	  ProtocolHandler protocolHandler = context.getBean("protocolHandler", ProtocolHandler.class);
	  System.out.println(protocolHandler.filter_Length());
	  
  }
}
