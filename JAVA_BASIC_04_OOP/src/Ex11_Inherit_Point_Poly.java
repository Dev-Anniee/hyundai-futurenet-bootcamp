/*
요구사항
 >> 매장카트 구현하기 입니다 <<
카트 (Cart) >> member field 
카트 매장에 있는 [모든 전자제품]을 담을 수 있다 
카트의 크기는 고정되어 있다 (10개) : 1개  , 2개 담을 수 있고 최대 10개까지 담을 수 있다
고객이 물건을 구매 하면 ... [카트에 담는다]
---------------------------------------
계산대에 가면 전체 계산
계산기능이 필요
summary() 기능 추가해 주세요
당신이 구매한 물건이름 과 가격정보 나열
총 누적금액 계산 출력

hint) 카트 물건을 담는 행위 (Buy() 함수안에서 cart  담는 것을 구현 )
hint) Buyer ..>> summary()  main 함수에서 계산할때  사용합니다

Buyer >> buy() , summary()  >> 카트에 물건 계산 (어떤물건 , 각 가격 >> 당신은 총 얼마 지급)

구매자는 default 금액을 가지고 있고 초기금액을 설정할 수 도 있다
*/

class Product2{ //공통 분모
	int price;
	int point;
	
	//Product(){}
	
	public Product2(int price) {
		this.price = price;
		this.point = (int)(this.price/10.0);
	}
}

//3개의 전자제품
class KtTv2 extends Product2{
	KtTv2(){
		super(500);
	}

	@Override
	public String toString() {
		return "KtTv";
	}
}

class Audio2 extends Product2{
	Audio2(){
		super(100);
	}
	
	@Override
	public String toString() {
		return "Audio";
	}
}

class NoteBook2 extends Product2{
	NoteBook2(){
		super(150);
	}
	
	@Override
	public String toString() {
		return "NoteBook";
	}
}

//10칸 짜리 카트
//제품이 구매될 때 카트에 담는다
//쇼핑이 끝나고 계산대에서 summary() 기능

class Buyer2{
	int money;
	int point;
	Product2[] Cart;
	int now;
	
	public Buyer2() {
		this(1500);
	}
	public Buyer2(int money) {
		this.money = money;
		this.Cart = new Product2[10];
		now=0;
	}
	
	void Buy(Product2 n) { //단 다형성이므로 무조건 Product를 상속받아야 한다
		
		if(this.money <n.price) {
			System.out.println("고객님 잔액이 부족합니다..."+this.money);
			return; //함수의 강제 종료
		}
		
		if(now==Cart.length) {
			System.out.println("카트가 꽉 찼습니다");
			return; //함수의 강제 종료
		}
		
		//실 구매행위
		this.money -=n.price;
		this.point+=n.point;
		Cart[now++] = n;
		System.out.println("구매한 물건의 이름은 : "+n.toString());
		System.out.println("현재 잔액은 : "+this.money);
	}
	
	void summary() {// 셀프 계산
		int sum=0;
		for(int i=0; i<Cart.length; i++) {
			if(Cart[i] == null) break;// 이렇게 짜는 것 보다 i를 now만큼 도는게 현명한 방법
			System.out.printf("%d번째 담긴 물건 : %s, 가격 : %d\n",i+1,Cart[i].toString(),Cart[i].price);
			sum+=Cart[i].price;
		}
		System.out.println("누적금액 : "+sum);
	}
}

public class Ex11_Inherit_Point_Poly {
	public static void main(String[] args) {
		KtTv2 ktTv2 = new KtTv2();
		Audio2 audio2 = new Audio2();
		NoteBook2 noteBook2 = new NoteBook2();
		
		Buyer2 buyer2 = new Buyer2();
		buyer2.Buy(audio2); //overloading + 다형성
		buyer2.Buy(ktTv2);
		buyer2.Buy(noteBook2);
		buyer2.Buy(noteBook2);

		buyer2.summary();
	}
}
