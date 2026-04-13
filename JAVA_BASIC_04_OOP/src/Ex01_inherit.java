//여러 개의 [작은 설계도]를 모아모아서 [큰 설계도]를 만드는 것 

/*문제점 제기 : 각 설계도끼리 관계 설정
설계도 (클래스) : 메인 설계도, 부분 설계도, 별도의 독립적인 설계도, 하위 설계도

1. (is a) 상속과 (has a) 포함
JAVA : child extends Base
C# : child : Base

특징 
1. 다중 상속 불가  (단일 상속 원칙 >> 계층적 상속) - 족보가 꼬이면 안된다
2. 계층적 상속 (여러개의 클래스를 상속 가능)
3. 예외적으로 다중 상속이 가능한 자원 : 인터페이스(표준, 규칙, 규약, 약속, 정의)

상속 
1. 진정한 의미 : 재사용성
2. 단점 : 초기 설계 비용 (부모 설계도 공통) 시간과 검증 필요 >> 커플링 (부모자식) >> 디커플링 (다형성) >> 인터페이스 (짱)
스프링은 상속을 싫어한다 : 커플링
3. 재사용성 의미 .. 사용

class Car{ extends Object가 생략되어 있다
}
*/
class GrandFather{
	public int gmoney = 5000;
	private int pmoney = 10000; //무덤까지 가져갈 돈 , 상속 관계에서 접근 불가
	public GrandFather() {
		System.out.println("GrandFather");
	}
}

class Father extends GrandFather{
	public int fmoney = 5000;
	public Father() {
		System.out.println("Father");
	}
}

class Child extends Father{
	public int cmoney = 5000;
	public Child() {
		System.out.println("Child");
	}
}

//Child child = new Child();
//메모리에 올라가는 순서
// Object -> G -> F -> C

public class Ex01_inherit {

	public static void main(String[] args) {
		Child child = new Child();
		System.out.println(child.cmoney);
		System.out.println(child.fmoney);

	}

}
