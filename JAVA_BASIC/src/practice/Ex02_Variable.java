package practice;
import kr.or.kosa.Emp;

public class Ex02_Variable {
	public static void main(String[] args) {
		
		//변수 정의
		//타입
		//연산자
		//제어문
		//------------------------------------(기초)
		//객체지향개념

		//main 함수 안에 있는 변수 (지역변수)
		//local variable
		//생성시점 : 함수호출  >> 소멸시점 : 함수종료
		int value;  //지역변수는 선언은 할 수 있지만 사용시에는 반드시 초기화 해야 한다
		//System.out.println(value);
		//The local variable value may not have been initialized
		
		value=100;
		System.out.println(value);
		
		//초기화 : 변수 최초로 값을 가지는 것 (할당을 ....)
		//지역변수는 반드시 초기화가 선행 
		//지역변수는 무조건 초기화
		
		int age = 0;//선언과 할당을 동시에
		
		Emp emp; //emp local variable
		//System.out.println(emp);
		emp = new Emp(); //주소값전달  : 초기화
		
		System.out.println(emp);
		//kr.or.kosa.Emp@73a28541  주소값 의미 (kr.or.kosa.Emp + "@" + 73a28541)
		

		Emp emp2 = new Emp();
		Emp emp3 = new Emp();
		System.out.println(emp2 == emp3); //변수가 가지고 있는 주소 문자열 값은 서로 같니
		
		Emp emp4= emp3; //두 변수는 같은 주소 사용 
		System.out.println(emp3 == emp4);
		
		
		//변수
		//1. 값타입   (자바가 제공하는 값타입 : 숫자(정수(byte,int),실수(float,double) , boolean)
		//종류 : 자바는 기본적으로 8개의 기본타입(원시타입) 값을 저장 .....
		//2. 참조타입  (변수가 주소값을 갖는다) 주소값을 저장 ......
		
		int a,b,c ; //가능  이런 식이라면 ..... >> 배열 int[] arr = new int[10];	
		a = 100; //초기화
		b = 200; 
		c = 200;
		//권장사항 아니다
		
		
		int result = a + b; //초기화 
		
	}

}
