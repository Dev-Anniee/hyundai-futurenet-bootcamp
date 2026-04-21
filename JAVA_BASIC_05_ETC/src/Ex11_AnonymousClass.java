/*
익명 클래스
클래스를 별도로 정의하지 않고 객체를 만드는 방법 -> 재사용 X, 일회용 클래스
이벤트 처리, 스레드 객체(runnable), Lamda, Stream
*/

abstract class Person{ //독자적으로 new 안돼요 누군가 강제로 구현하고 재정의 
	abstract void eat(); //재정의 강제
}

//클래스를 만드는 것 
class Man extends Person{
	@Override
	void eat() {
		System.out.println("Person의 eat 함수 구현");
	}
}

interface Eatable{
	void eat();
}

//class Eat implements Eatable {재정의}

class Test{
	void method(Eatable e) { //Eatable 구현하고 자식 객체 주소 (다형성)
		e.eat();
	}
}

public class Ex11_AnonymousClass {
	public static void main(String[] args) {
		/*
		 * Man man = new Man();
		 * man.eat();
		 * 
		 * Person p =man; 
		 * p.eat();
		 * 
		 * Person p2 = new Man(); 
		 * p2.eat();
		 */
		/*
		 * abstract class Person 추상클래스 ... 
		 * 객체 생성 불가능(미완성) 
		 * 상속하는 클래스 만들고 >> class Man
		 * extends Person 클래스 Man 이름이 담겨있다 
		 * >> Man 한 번 쓰고 굳이 클래스 만들어서 재사용 가능한 코드를 만들 필요가 있을까?
		 * 
		 * 이름이 없는 1회성으로 작업 가능
		 */
		
		Person person = new Person() { //익명 클래스 > 상속하는 클래스 이름이 없다 class Man extends Person
			@Override
			void eat() {
				System.out.println("익명 객체 타입으로 구현");
				
			}
		}; person.eat();
		//
		Test test = new Test();
		test.method(new Eatable() { //인터페이스 강제 구현 일회성으로 가능
			@Override
			public void eat() {
				System.out.println("일회성으로 인터페이스를 직접 구현 가능");
			}
		}); //class Eat implements Eatable {재정의} new Eat();
	}
} //  추상 클래스, 인터페이스, 상속하고 구현한느 클래스 반드시 있어야 되지만
 // 때로는 재사용이 필요 없다면 직접 구현이 가능하다 -> (익명 클래스) : 일회성/ 재사용이 없다