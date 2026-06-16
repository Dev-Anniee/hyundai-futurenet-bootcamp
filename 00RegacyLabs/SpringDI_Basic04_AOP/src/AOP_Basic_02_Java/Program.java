package AOP_Basic_02_Java;

import java.lang.reflect.Proxy;

public class Program {

	public static void main(String[] args) {
		
		//1. 실 객체의 주소
		Calc calc = new NewCalc();
		
		//2. 가짜 (Proxy)
		Calc cal = (Calc)Proxy.newProxyInstance(calc.getClass().getClassLoader(), //실객체의 정보 제공(내부정보) : 메타정보 제공
				                          calc.getClass().getInterfaces(),  //행위정보 제공 (인터페이스)
				                          new LogPrintHandler(calc));       //보조관심 객체 
		
		int result = cal.MUL(1000000, 5555555);
		System.out.println("result : " + result);
		
	

	}

}
