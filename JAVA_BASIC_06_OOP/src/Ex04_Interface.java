//인터페이스
// ~ 할 수 있는 

interface Irepairable{ //수리할 수 있는
	
}

class Unit2{
	int hitPoint; //기본 에너지
	final int MAXHP; //최대 에너지
	
	public Unit2(int hp) {
		this.MAXHP = hp;
	}
}

// 지상 유닛, 공유유닛, 건물
class GroundUnit extends Unit2{
	public GroundUnit(int hp) {
		super(hp);
	}
}

class AirUnit extends Unit2{
	public AirUnit(int hp) {
		super(hp);
	}
}

class CommanCenter implements Irepairable { //Unit은 아니다
	
}

class Tank2 extends GroundUnit implements Irepairable{
	public Tank2() {
		super(50);
		this.hitPoint = this.MAXHP;
	}

	@Override
	public String toString() {
		return "Tank2";
	}
}

class Marine2 extends GroundUnit{
	public Marine2() {
		super(50);
		this.hitPoint = this.MAXHP;
	}
	
	@Override
	public String toString() {
		return "Marine2";
	}
}
// Svc 자원을 모아서 돈을 버는 유닛
class Scv extends GroundUnit implements Irepairable{
	public Scv() {
		super(50);
		this.hitPoint = this.MAXHP;
	}

	@Override
	public String toString() {
		return "Scv";
	}
	// Scv 구체화, 특수화된 기능 구현하고 싶다 -> 공통 기능 x
	// 수리 repair
	//Tank2, scv, CommandCenter repair
	//Marine 을 repair 안돼요 
	
	/*
	 * void repair(Tank2 tank) {
	 * 
	 * } void repair (Scv scv) {
	 * 
	 * } - repair 함수를 unit의 개수 만큼!!!
	 * 
	 * 전자제품 buy(냉장고), buy(Tv), buy(Product n)
	 * GroundUnit >> Tank2, Marine2, Scv
	 * void repair (Unit2 unit) {} >> 문제 : AirUnit, Marine2 repair 대상이 아니다
	 * 예외처리 instanceof 하겠다? -> 타입비교 복잡
	 * void repair(Grounded unit) >>Tank2, Marine(x), Scv
	 * 
	 * sol) interface Irepairable{
	 *의미없는 애들을 묶고 싶은 아이들끼리 interface 처리
	 다형성 ... 활용 ... 같은 부모를 가지게 하자 그 안에 구현은 안 해도 돼
	 class Tank2 extends GroundUnit implements Irepairable - 같은 소속을 만들어 준다 
	 class Scv extends GroundUnit implements Irepairable - 같은 소속을 만들어 준다 
     class CommandCenter implements Irepairable
     
     	 //수리 방법이 다를 수 있다 - 고민 : 수리하는 방법 차이
     	 // Irepairable 자원에 대한 타입 판단의 근거 -> Instance of
	 */ 
	void repair(Irepairable repair) {
		//Tank2, SCV, CommandCenter - implements 했지만  수리 방법이 다를 수 있다
		//repair가 다르다
		if(repair instanceof Unit2) {
			//충전
			//인터페이스와 Object 동등관게
			Unit2 unit2 = (Unit2) repair;
			if(unit2.hitPoint != unit2.MAXHP)
				unit2.hitPoint = unit2.MAXHP;
		}else {
			//건물
			System.out.println("다른 방식으로 건물을 repair합니다");
		}
	} 
}

public class Ex04_Interface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tank2 tank2 =  new Tank2();
		Marine2 marine2 = new Marine2();
		Scv scv = new Scv();
		CommanCenter center = new CommanCenter();
		
		tank2.hitPoint-=20;
		System.out.println("탱크 : "+tank2.hitPoint);
		System.out.println("Scv 수리요청");
		scv.repair(tank2);
		scv.repair(center);
		
		System.out.println("탱크 : "+tank2.hitPoint);
	}
}
//인터페이스는 상관없는 클래스를 묶어 그룹을 만들 수 있음/ 속성 부여 할 수 있는 -> 부모를 만들어서 해결
