/*
User (사용자) <> Provider (제공자)

class A{}
class B{}

두 개의 관계에서 A는 B를 사용합니다 
1. 포함 A 라는 클래스가 B자신의 member field 로 사용 class A {B b;}
2. 상속 A extends B
2.1 부분
2.2 전체
두가지를 구분하믄 기준은 라이프 사이클이 동일한가 다르냐로 구분
class B{}
class A{
	int i;
	B b;} //A는 B를 포함합니다
	A(){B = new B();}
>>main()
A a = new A();
B의 자원에 접근하는 방법 a.b.자원명
a= null이면 -> (가비지 컬렉터에게 알려줌) 
A객체 생성시 B 객체 같이 생성 A객체 소멸시 B 같이 소멸 -cpu
B는 독자적 생성 불가능
자동차 엔진, 컴퓨터와 CPU 관계

-------------------------------------------------
class B{}
class A{
	in ti;
	B b;
	A(){}
	//method
	/// void m(B b){
	/// this.b = b;
	/// }
	/// 
	필요에 따라서 B가 필요할 때 
	a.m(b); 부분적인 포함

a= null;
포함인데 2객체의 라이프 사이클은 같지 않다 (부분)
학교 학생, 노트북과 마우스 관계, 핸드폰과 충전기 

참조변수 타입을 가지고 있다는 점은 공통점 전체/ 집합 차이점
}
*/

interface Icall{
	void m(); //public abstract void m();
}

class D implements Icall{
	@Override
	public void m() {
		System.out.println("D 재정의");
	}
}
class F implements Icall{
	@Override
	public void m() {
		System.out.println("F 재정의");
	}
}

//현대적인 프로그래밍 기법은 유연성 ... 대충 대충 만들어서 느슨한 연결
class C{
	//void method(F f){
	
	void method(Icall ic) { //buy(product), product[] cart spring 80% 코드 인터페이스의 다형성 사용)}
		//Icall 구현하고 있는 모든 객체가 들어올 수 있다
		ic.m();
		//인터페이스 타입으로 받아도 Object 모든 자원 사용 가능(Java)
		}
	}


public class Ex05_User_Provider {
	public static void main(String[] args) {
		C c = new C();
		D d = new D();
		F f = new F();
		
		c.method(d);
		c.method(f);
	}
}
