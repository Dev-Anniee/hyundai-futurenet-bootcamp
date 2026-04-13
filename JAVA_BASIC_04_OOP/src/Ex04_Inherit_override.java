//사실 재정의 의미가 ... 실제 사용 되는 곳은 
//인터페이스의 사용 ...

//연습이 되지 않으면 나오지 않는다
//오버 라이딩 : 상속 관계에서 재정의 - 부모 함수 재정의해서 내용을 바꾸는 것
//오버로딩 : 하나의 이름으로 여러개의 기능 제공 - 파라미터와 타입을 달리해서 만드는 방법

import kr.or.kosa.Emp;

class Test2{
	int x =100;
	void print() {
		System.out.println("부모의 함수 print");
	}
}

class Test3 extends Test2{
	int x =300; //C# 부모 무시하기 - 쓰지 말기
}

public class Ex04_Inherit_override {
	public static void main(String[] args) {
		Test3 test3 = new Test3();
		System.out.println(test3.x); //자식 우선
		
		Emp emp = new Emp(2000, "smith");
		System.out.println(emp.toString());
		
//		class Object{
//			public String toString() {
//				return 패키지명 + "@" + 주소값;
//			}
//		}
	}
}
