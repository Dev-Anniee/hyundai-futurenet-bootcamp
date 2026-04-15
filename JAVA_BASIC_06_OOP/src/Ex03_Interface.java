/*
인터페이스는 소프트웨어 설계 최상위 단계
혹자는 인터페이스는 무에서 유를 창조한다
인터페이스 : 약속, 규칙, 규약, 표준을 만드는 행위
규약 : c타입
ISO표준 : 국가 -> 업무가 안 바뀐다 -> 
초급 개발자 : 인터페이스 개발? -> x -> 사용하는 방법에 집중하자 

인터페이스 약속, 규칙만 있다 -> 구현부는 없다
추상클래스와 인터페이스의 공통점 : 
1. 스스로 객체 생성이 불가능 (new 연산자 사용 불가)
1.1 차이점 : 추상(완성 + 미완성), 인터페이스는 (대부분..) 미완성 자원이다

2. 추상클래스 extends
   인터페이스 implements - 모두 구현해 
   
   class Car extends Abclass{} 상속
   class Car extends Object implemnts Ia {} 구현
   
   둘다 추상자원(실행블럭이 없다) 미완성 자원 >> 강제적 구현이 목적 >> 재정의
   
3. 다른점
다중상속 : 인터페이스 다중 상속 (구현)
약속의 범위는 작게 하는 것이 좋다 (재사용 높게ㅔ)

인터페이스끼리는 서로 상속이 가능
인터페이스는 구현하는 입장에서 보면 다중 구현

Ia, Ib, Ic
class Test extends Object implements Ia, Ib,Ic{
     재정의.....}
     
4. 인터페이스 (상수를 제외한 나머지 추상함수) >JDK8 (default, static)


그래서 초급 개발자 시선
1. 인터페이스 [다형성!!!] 입장으로 접근 - 인터페이스는 부모 타입
2. 서로 연관성ㅇ이 없는 클래스를 하나로 묶어주는 역할 (같은 부모를 가지므로)
3. JAVA API 수많은 인터페이스 활용 가능
4. 인터페이스 해석(~able) :날수있는, 수리할 수 있는, 먹을 수 있는
5. 객체간 연결 고리 (객체간 소통의 역할) : 다형성 원리


interface
1. 실제 구현부를 가지고 있지 않다 > 실행블럭이 없다
2. Interface Iable {void move(int x, int y);}
3. Java API > Collection > List, Set, Map > 이해 -- 기본 중의 기본

생성방법 
*/
/*
interface Ia{
	//상수 구현 
	//public static final int VERSION=1; //public, static
	int VERSION =1; // 위와 같은 타입
	
	//추상함수
	//public abstract void run();
	void run(); //위와 같은 말 = 앞에 public abstract 컴파일러가 자동으로 붙는다
}
*/

interface Ib {
	int AGE =100;
	String GENDER= "남";
	String print(); //재정의
	void message(String str); //재정의
}

//약속 - 빈 인터페이스 만들기
interface Ic{ //부모를 만들어서 구현하는 아이들은 한 번에 사용하겠다
	
}

class Test2 extends Object implements Ib,Ic{

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void message(String str) {
		// TODO Auto-generated method stub
		
	}
	
}

public class Ex03_Interface {
	public static void main(String[] args) {
		Test2 test2 = new Test2();
		Ib ib = test2;
		System.out.println(ib.AGE);
		System.out.println(ib.GENDER);
		System.out.println(ib.toString());
	}
}
/*
 * 원칙 
 * 자바의 규칙 
 * 모든 참조 타입은 Object 메서드를 사용할 수 있도록 허용한다
 * 인터페이스 타입도 자바의 참조타입이기 때문에 -> Object 자원 사용 가능
 */