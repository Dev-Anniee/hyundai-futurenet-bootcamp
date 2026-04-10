/*자바스크립트 -> this, event 객체(*), array
 * this 이것
 * 1. 객체 자신을 가리키는 this (앞으로 생설될 객체의 주소를 담을 곳이라고 가정하고 사용) -> 편하게
 * >> 인스턴스 자식을 가리키는 변수, 인스턴스의 주소가 저장될 곳
 * 2. this 객체 자신(주소) : 그 주소를 가지고 재호출 가능 (원칙적으로는 생성자는 객체 생성시 한 개만 호출)
 * 단 this를 사용하면 예외적으로 여러개의 생성자를 호출할 수 있다
 * >>생성자를 호출하는 this
 * 
 * */

class Test5{
	int i;
	int j;
//	Test5(int i, int j){
//		i=i; //멤버필드로 가지고 있구나, 초기화시키는 코드이구나 
//		j=j;
//	}
//	
//	Test5(int n, int m){
//		i=n; //지양해야하는 코드 , 유지보수가 어려워진다 
//		j=m;
//	}
	
	Test5(int i, int j){
		this.i=i; //this가 없으면 parameter에서 쓰이는 아이
		this.j=j;
	}
}

class Book{
	String bookNameString;
	Book(String bookName){
		this.bookNameString = bookName;
	}
}

//Today point 
// 생성자를 호출하는 this (중복 코드 감소)

class Socar{
	String color;
	String gearType;
	int door;
	
	//기본 옵션
	Socar(){
		this.color = "red";
		this.gearType = "auto";
		this.door = 2;
		}
	Socar(String color, String gearType, int door){
		this.color = color;
		this.gearType = gearType;
		this.door = door;
	}
	
	void soCarInfo() {
		System.out.println(this.color +","+this.gearType+","+this.door);
	}
}
public class EX14_this {
	public static void main(String[] args) {
		Socar socar = new Socar("gold", "auto", 6);
		socar.soCarInfo();
	}
}
