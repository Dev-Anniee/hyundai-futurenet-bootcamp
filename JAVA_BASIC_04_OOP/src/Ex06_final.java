/*
변수 <-> 상수
상수 : 한번 값이 초기화되면 변경 불가
상수 : 고유값(주민번호), 수학 PI=3.14, 소프트웨어 출시 버전 V1.1.1
상수는 관용적으로 대문자 약속
자바 :final int NUM =0;
C# : const integer NUM =0;

final 키워드
1. final class Math{} > 상속금지
ex public final class Math extends Object

2. POINT
2.1 public final void print() {} 상속 관계에서 부모 함수의 final이 붙어 있으면 자식 클래스에서 재정의 할 수 없다
*/

class Vcard{
	final String KIND="heart";
	final int NUM=10;

	void method() {
		//JAVA API 제공
		System.out.println(Math.PI); // PI  public static final PI
	}
}

//설계자 입장에서
//카드를 만드는 설계도 {카드마다 고유한 모양과 번호 있음}
//한번 생성되면 변경 불가

class Card{
	final String KIND;
	final int NUM;

	//Card() {}
	Card(String kind, int num){
		//초기화를 보장하는 코드 + 강제성 부여
		this.KIND = kind;
		this.NUM = num;
	}

	@Override
	public String toString() {
		return "Card [KIND=" + KIND + ", NUM=" + NUM + "]";
	}

}

public class Ex06_final {
	public static void main(String[] args) {
		Card card1 = new Card("spade", 1);
		System.out.println(card1);

		Card card2 = new Card("spade", 2);
		System.out.println(card2);

		Card card3 = new Card("spade", 3);
		System.out.println(card3);
	}
}
