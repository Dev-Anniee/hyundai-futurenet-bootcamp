package AOP_Basic_02_Java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

/*
보조업무 (공통업무) 구현하는 클래스 

invoke : 다른 함수를 대리해서 실제 함수처럼 사용 (대리함수)

invoke 함수 하나가 여러개의 실제 함수를 대리한다

리플렉션 : java 에서 런타임에서 클래스, 메서드 , 필드 등의 정보를 조사항고 조작할 수 있게 해주는 기능
즉 컴파일할 때가 아니라 프로그램이 실행되고 있을 때 해당 객체의 클래스를 보고 메서드를 호출하거나ㅣ 필드를 수정할 수 있는 능력

*/
public class LogPrintHandler implements InvocationHandler {

	//실 객체의 주소
	private Object target;
	public LogPrintHandler(Object target) {
		System.out.println("LogPrintHandler 생성자 호출 : " + target);
		this.target = target;
	}
	
	//invoke 함수 대리 (ADD(x,y) , MUL , SUB)
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("invoke 함수 호출");
		System.out.println("Method method : " + method);
		System.out.println("Method parameter : " +  Arrays.toString(args));
		
		//보조업무
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("[타이머 시작]");
		
		//주업무(실제 함수 호출)
		int result =  (int)method.invoke(this.target, args);
		
		
		//보조업무
		sw.stop();
		log.info("타이머종료");
		log.info("Time log method :  ADD");
		log.info("Time log method :" + sw.getTotalTimeMillis());
		
		return result;
	}

}
