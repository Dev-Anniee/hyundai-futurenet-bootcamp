/*
private
default (같은 폴더(package) 예시)
public
상속 관계에서 protected 중요!!! 어떨 때는 default, 어떨 때는 public의 역할

상속 없는 클래스 -> member field protected >> default
상속 관계에서 ...> public

*** protected 설계자 쓰는 이유 : 당신이 내 클래스를 상속했을 때 protected 함수를 사용하고 싶다면 재정의 강제성

*/

import kr.or.kosa.Pclass;

class Dclass{
	private int p;
	int d;
	protected int pr;
	public int pu;
}

//설계도에 해당 함수 protected 상속에서 재정의 해라 (강제성 부여)
class Child2 extends Pclass{

	@Override
	protected void m() { //protected 목적 : 당신이 쓰고 싶으면 재정의 
		// 개발자 마음
		super.m(); 
	}
	//상속 관계에서 protected는 상속 클래스 안에서 public
	
	void method() {
		this.pr= 100; //상속 관계에서 protected 는 public이므로 쓸 수 있다
	}
}

public class Ex07_Inherit_Protected {
	public static void main(String[] args) {
		//같은 package 안에서 Dclass 존재
		Dclass dclass = new Dclass();
		// dclass.p 뺴고 볼 수 있다
		Pclass pclass = new Pclass();
		//pclass.pu 다른 패키지에서 public 만 볼 수있다
		//protedcted 접근자 객체 입장 접근 불가 (default)
		// **상속!!! protected 강제성을 가져서 상속해서 구현해라
		
	}
}
