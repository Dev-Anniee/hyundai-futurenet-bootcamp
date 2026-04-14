package kr.or.kosa;
//새 (공통: 일반, 추상) : 새는 날 수 있다, 새는 빠르다
public class Bird {
	//공통 기능
	public void fly() {
		System.out.println("flying");
	}
	protected void moveFast() { //protected 원인 - public은 재정의할 수 있으나 강제성이 없다
		//강제로 재정의 않쓰면 쓸 수 없게 - 너무 프리한 코드 좋지 않다
		fly();
	}
}

//class Bi extends Bird{
//	public void run() {
//		System.out.println("run");
//	}
//	
//	@Override
//	public void moveFast() {
//		run();
//	}
//}

//설계자 
//새는 빠르다 날 수 있으니까
//타조는? 달려서 빠른거야
