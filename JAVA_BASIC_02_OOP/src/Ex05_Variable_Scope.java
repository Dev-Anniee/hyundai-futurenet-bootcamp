/*1. instance variable >>class Car {private String color}
1.1 생성되는 객체마다 다른 값을 가질 수 있다
1.2default 가지고 있다. 초기화 한다면... 생성자...

2. local variable
2.1 public void run() {int speed;}
2.2 초기화 필요

3. block variable 제어문 안에 (제어문이 생성되면 실행 , 끝나면 소멸)

포인트
static variable>> 공유자원 (힙에 생성된 객체간 공유자원); 
1. 객체 생성 이전에 메모리에 올라간다 (클래스 또는 메서드 영역)
2. 설계도 > 구체화 > new
3. static 자원은 new 없이 메모리에 올라간다 ... 메모리에 올라간 자원은 사용이 가능하다
*/

class VariableClass{
	int iv;
	//멤버 필드 ,  객채 변수 (생성되는 객체마다 다른 값을 가질 수 있다
	//iv 언제 사용가능합니까(메모리리에 언제 올라가나요)>heap>>new VariableClass()
	// 목적 : 데이터 자료를 담을 목적
	
	static int cv;
//	1. class Variable (클래스 변수)>> static variable(객체간 공유자원)
//	2. 객체간 공유자원 (모든 객체가 공유하는 자원)
	
	void method() {
		int lv =0;
		
		for(int i=0; i<=100; i++) {

		}
	}
}

public class Ex05_Variable_Scope {
	public static void main(String[] args) {
		VariableClass.cv = 200; //클래스의 이름으로 접근, 클래스이름.자원 (생성된 객체들의 공유 자원)
		
		VariableClass variableClass = new VariableClass(); //메타 데이터 -> cv 처리 -> 스택에서 변수 만들기 -> 객체 올라간다
		System.out.println(variableClass.cv);
		//heap 객체 접근 ...cv read ... 클래스 영역 cv
		
		VariableClass variableClass2 = new VariableClass(); //메타 데이터 -> cv 처리 -> 스택에서 변수 만들기 -> 객체 올라간다
		System.out.println(variableClass2.cv);
		
		variableClass2.cv=555;
		System.out.println(VariableClass.cv);
		
//		VariableClass.cv=100;
//		VariableClass vc =new VariableClass();
//		vc.iv = 200;
	}
}
