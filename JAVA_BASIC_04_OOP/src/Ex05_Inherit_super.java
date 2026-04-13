/*객체 한 가지만 가지고 놀면 
this
1. 객체 자신을 가리키는 (this.empno, this.ename)
2. 생성자를 호출하는 this (옵션... 여러개)

상속 관계
부모 자식
자식 입장에서 부모 자원에 접근 : 부모의 주소를 담고 있는 자원 : super
super 부모의 자원 접근
super 부모의 생성자 호출
*/
class Base{
	String baseName;
	
	Base(){ //기본 호출
		System.out.println("부모의 기본 생성자");
	}
	Base(String baseName){
		this.baseName = baseName;
		System.out.println("baseName: "+ this.baseName);
	}
	void method() {
		System.out.println("부모의 method");	
	}
}

class Derived extends Base{
	String dname;
	
	public Derived() {
		System.out.println("자식의 기본 생성자");
	}
	
	Derived(String dname){
		
		super(dname); //부모도 오버로딩된 생성자 호출
		
		this.dname = dname;
		System.out.println("dname: "+ this.dname);
	}

	@Override
	void method() {
		System.out.println("나는 재정의한 자식 method");
		//super.method();
	}
	
	void method2() {
		super.method(); //부모 주소로 직접 접근, 재정의가 되어있다고 해도 부모의 자원을 볼 수 있다
	}
}

public class Ex05_Inherit_super {
	public static void main(String[] args) {
//		부모의 기본 생성자
//		dname: 자식호출
		Derived derived = new Derived("자식호출");
		
		derived.method();
		derived.method2();
		
	}
}
