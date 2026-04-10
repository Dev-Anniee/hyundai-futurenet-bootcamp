/*OOP 객체 지향 프로그래밍 : 여러개의 작은 조각 (설계도)를 모아 모아서 큰 조각 (설계도)를 만드는 행위
TIP) AOP...?

 객체 지향 언어가 가지는 특징 :
1. 상속 (재사용성)
2. 캡슐화 (간접 할당, private ->setter,getter)
3.  다형성 : 하나의 타입이 여러개의 객체 주소를 가진 것 (상속 관계에서 부모타입) , 상속과 함께 간다
부모 타입의 변수가 자식 타입의 주소를 가지는 것 

method > 어떤 개발자가 기능 ...> 출력(정수, 실수,문자열 출력) > 함수가 너무 많고 이름 복잡
하나의 이름으로 여러가지 기능을 하는 함수
예시 - System.out.println()
함수 parameter 개수, 타입 다름을 인정
printChar(), printString(), printBoolean() (x)
1. overloading 사용은 성능에 영향 x
2. 편하게 사용하려고 -> 개발자가 > 팀원을 배려해서
3. 무조건 사용할 필요는 없다 (java api는 이미 오버로딩이 적용되어 있다)

문법)
1. 함수의 이름은 같고 parameter 개수와 타입이 달라야 한다
1.1 함수 이름 동일
1.2 파라미터 개수와 타입만 다르면 된다
1.3 return type은 오버로딩의 판단 기준이 아니다
1.4 parameter 순서가 다름을 인정 !!! 중요 
ex) m(int i, String s), m(String s, int i)
*/

class Human {
	String name;
	int age;
}

class OverT{
	int add (int i) {
		return 100+i;
	}
	
	int add (int i, int j) {
		return i+j;
	}
	
	String add(String str) {
		return "hello" + str;
	}
	
	//int add(String s) {} (x)
	
	String add(int i, String s) { //순서가 다름을 인정
		return "hello"+s;
	}
	
	String add (String s, int i) { //순서가 다름을 인정
		return "hello"+s;
	}
	
	void add(Human human) { //오버로딩
		human.age = 100;
		human.name = "홍길동";
	}
	void add(Human h, Human h2) {
		System.out.println(h +" : " +h2);
	}
}
public class Ex11_Method_Overloading {
	public static void main(String[] args) {
		OverT overT = new OverT();
		System.out.println(overT.add("A"));
		
		//오버로딩 
		//1번
		Human human = new Human();
		overT.add(human);
		
		//2번
		overT.add(new Human());
		
		//3qjs
		overT.add(new Human(),new Human());
	}
}
