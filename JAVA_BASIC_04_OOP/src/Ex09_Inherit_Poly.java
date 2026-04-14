//상속 관계에서 존재 - 다형성
/*
다형성 : 여러가지 성질(상태)를 가질 수 있는 능력 (사전적 의미)
자바 - 상속 관계에서 하나의 참조변수 (부모타입)가 여러개의 타입 (자식 객체의 주소)를 가질 수 있는 것
하나의 참조변수 >> 상속 관계에서 부모타입
여러개의 타입 >> 상속관계에서 자식객체
*/

class Tv{
	boolean power;
	int ch;
	
	void power() {
		this.power =!this.power; //-> false/true 두 가지 다 가능하게 구현
	}
	
	void chUp() {
		ch++;
	}
	void chDown() {
		ch--;
	}

//	@Override
//	public String toString() {
//		return "Tv [power=" + power + ", ch=" + ch + "]";
//	}
}


//자막 기능이 제공되는 Tv
class CapTv extends Tv{
	//Tv 제공
	//특수화, 구체화
	//자막처리
	String text;
	String captionText(){
		return this.text = "현재 자막 서비스 ... 가정";
	}
}


public class Ex09_Inherit_Poly {
	public static void main(String[] args) {	
		CapTv cTv = new CapTv();
		cTv.power();
		cTv.chUp();
		//System.out.println(cTv.toString());
		System.out.println(cTv.captionText());
		//부모 타입의 참조 변수는 자식 객체의 주소를 가질 수 있다!
		
		Tv tv = cTv;
		//상속 관계
		//Tv 부모
		//cTv 자식 객체
		
		//단 부모는 자식의 자원만 접근 가능
		//단 자식의 부모의 함수를 재정의 하면 볼 수 있다 
		System.out.println(tv);
		System.out.println(cTv);
		
		//CapTv capTv = tv //내부적으로 봤을 때 부모가 가지고 있는 주소를 자식에게 줄 수 없다
		CapTv capTv2 = (CapTv)tv; //자식 타입으로 다운 캐스팅 -> 쓸 일이 없다
	}
}
