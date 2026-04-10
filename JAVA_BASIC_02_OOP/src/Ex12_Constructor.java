/*생성자 함수 중요!!! (특수한 목적의 함수)
1. 특수한 목적 (member field 초기화) : static {}, { }
2. 일반 함수와 다른 점
2.1 함수 이름이 고정 (class 이름과 동일)
2.2 return type 이 없어요 (default void) > 생성시점 >new >memberfield > 자동 호출 생성자
2.3 생성자 overloading 함수가 여러개 -> 개발자가 무엇을 강제할 수 있는 기법 : 멋있는 기법 , 상황에 따라 지시
참고 원칙은 객체 생성시 생성자 함수는 하나만 실행 ... 여러개의 생성자 함수를 실행할 수 있다 (this 용법, 생성자 호출 기능) 

public class Car{
	public Car(){}
	public Car(String name){}
	생성자의 접근자로 private를 사용할 수 있다? 
}

public class Car{
	private Car(){}
	가능 싱글톤의 시작 -> 논리 이해 
	생성자의 접근자로 private를 이용할 수 있다 -> new를 막아 놓으면 
	>> 객체 하나만을 만들어서 공유하겠다 (static 접근이 가능)
	
	생성자 함수 왜 만들까요?
	[생성되는 객체] 마다 [강제적]으로 member field 초기화
	
	class  Car {
		String color; //default null
	} 
	>> 설계도를 만든 사람은 자동차 색상은 너가 정해 (안 해도 상관 없음)
	>> 명시적으로 생성자를 만들지 않으면 자동으로 default 생성자 생성
	
	 java Car 엔터
	 컴파일러가 public Car (){} 자동 생성, 기본 생성자 만든다
	 
	 class Car{
	 String color = "blue"; 초기화
	문제 > 자동차의 색상을 정하지 않고 출고 (설게도)
	class Car{
	String color ;
	public Car(){};
	public Car(String color){
		this.color = color;
	}
}
1. new Car() > public Car(){} 자동 호출
	class Car{
	String color ;
	public Car(String color){ //오버로딩한 설계도만 넣는다 -> 강제
		this.color = color;
	}
}

1. new Car(); (x) > new Car("blue") 자동 호출
*/

class Car{
	//내부적으로 default 생성자 > public car(){}
}

class Car2{
	Car2() {
		System.out.println("default");
	} // default 생략
}

class Car3{ // 강제성 x
	Car3() {} 
	Car3(String color) {} 
}

class Car4{ // 강제성 0
	Car4(String color) {} 
}

// 자동차 매장 : 영업사원 (기본 옵션, 여러가지 맞춤 옵션) , 옵션에 없는 건 반영하지 않는다
class Car5{
	String carColor;
	int door;
	
	public Car5() { //기본 사양
		carColor = "blue";
		door = 4;
	}
	//옵션 반영 - 비지니스 로직
	public Car5(int door) { 
		carColor = "blue";
		this.door = door;
	}
	public Car5(String carColor) {
		this.carColor = carColor;
		this.door = 4;
	}
	public Car5(int door,String carColor) {
		this.carColor = carColor;
		this.door = door;
	}
}

public class Ex12_Constructor {
	public static void main(String[] args) {
		Car car = new Car();
		Car4 car4 = new Car4("blue"); //인자를 무조건 넣어주도록 강제성 부여
		Car5 car5 = new Car5(2);
	}
}
