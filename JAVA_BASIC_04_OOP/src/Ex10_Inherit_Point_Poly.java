/*
시나리오(요구사항)
저희는 가전제품 매장 솔루션을 개발하는 사업팀입니다
A라 전자제품 매장이 오픈되면

[클라이언트(매장사장) 요구사항]
가전제품은 제품의 가격 , 제품의 포인트 정보를 모든 제품이 가지고 있습니다
각각의 가전제품은 제품별 고유한 이름정보를 가지고 있다
ex)
각각의 전자제품은 이름을 가지고 있다 (ex) Tv , Audio , Computer
각각의 전자제품은 다른 가격정보를 가지고 있다( Tv:5000 , Audio : 6000)
제품의 포인트는 가격의 10%적용한다

시뮬레이션 시나리오
구매자: 제품을 구매하기 위한 금액정보 , 포인트 정보를 가지고 있다
ex) 10만원 , 포인트 0
구매자는 제품을 구매할 수 있다 , 구매행위를 하게 되면 가지고 있는 돈은 감소하고 (가격) 포인트는 증가한다
구매자는 처음 초기 금액을 가질 수 있다
*/

//부모타입 (전자제품의 공통사항)
//가격, 보너스 포인트

class Product{ //공통 분모
	int price;
	int point;
	
	//Product(){}
	
	public Product(int price) {
		this.price = price;
		this.point = (int)(this.price/10.0);
	}
}

//3개의 전자제품
class KtTv extends Product{
	KtTv(){
		super(500);
	}

	@Override
	public String toString() {
		return "KtTv";
	}
}

class Audio extends Product{
	Audio(){
		super(100);
	}
	
	@Override
	public String toString() {
		return "Audio";
	}
}

class NoteBook extends Product{
	NoteBook(){
		super(150);
	}
	
	@Override
	public String toString() {
		return "NoteBook";
	}
}

//구매자
class Buyer{
	int money = 1500;
	int point;
	
	// ktTv, NoteBook, Audio 구매 가능
	
//	void ktTvBuy(KtTv n) { // 주소를 ktTv 정보 - 가격, 이름
//		if(this.money <n.price) {
//			System.out.println("고객님 잔액이 부족합니다..."+this.money);
//			return; //함수의 강제 종료
//		}
//		//실 구매행위
//		this.money -=n.price;
//		this.point+=n.point;
//		System.out.println("현재 잔액은 : "+this.money);
//		System.out.println("구매할 물건의 이름은 : "+n.toString());
//	} 
//	void AudioBuy(Audio n) {
//		if(this.money <n.price) {
//			System.out.println("고객님 잔액이 부족합니다..."+this.money);
//			return; //함수의 강제 종료
//		}
//		//실 구매행위
//		this.money -=n.price;
//		this.point+=n.point;
//		System.out.println("현재 잔액은 : "+this.money);
//		System.out.println("구매할 물건의 이름은 : "+n.toString());
//	}
//	void NoteBookBuy(NoteBook n) {
//		if(this.money <n.price) {
//			System.out.println("고객님 잔액이 부족합니다..."+this.money);
//			return; //함수의 강제 종료
//		}
//		//실 구매행위
//		this.money -=n.price;
//		this.point+=n.point;
//		System.out.println("현재 잔액은 : "+this.money);
//		System.out.println("구매할 물건의 이름은 : "+n.toString());
//	}
//	
	void Buy(Product n) { //단 다형성이므로 무조건 Product를 상속받아야 한다
		if(this.money <n.price) {
			System.out.println("고객님 잔액이 부족합니다..."+this.money);
			return; //함수의 강제 종료
		}
		//실 구매행위
		this.money -=n.price;
		this.point+=n.point;
		System.out.println("현재 잔액은 : "+this.money);
		System.out.println("구매할 물건의 이름은 : "+n.toString());
	}
}

public class Ex10_Inherit_Point_Poly {
	public static void main(String[] args) {
		//매장 오픈. 가오픈. 매장 물건 전시
		KtTv ktTv = new KtTv();
		Audio audio = new Audio();
		NoteBook noteBook = new NoteBook();
		
//		Buyer buyer = new Buyer();
//		buyer.AudioBuy(audio);
//		buyer.AudioBuy(audio);
//		buyer.ktTvBuy(ktTv);
//		buyer.NoteBookBuy(noteBook);
//		buyer.NoteBookBuy(noteBook);
//		buyer.NoteBookBuy(noteBook);
//		buyer.NoteBookBuy(noteBook);
		
		Buyer buyer = new Buyer();
		buyer.Buy(audio); //overloading + 다형성
		buyer.Buy(audio);
		buyer.Buy(ktTv);
		buyer.Buy(noteBook);
		buyer.Buy(noteBook);
		buyer.Buy(noteBook);
		buyer.Buy(noteBook);
	}
}
/*
 	1차 오픈
 	사장님 : 하와이 휴가 보내줘서 개발자 보냄
 	매장 오픈 ... 장사가 잘돼서 2틀 뒤에 
 	물건 1000개 구매 
 	1000개 매장 전시 
 	장사가 잘 돼서 고객들이 난리
 	구매함수 - 3개 밖에 없다  
 	사장님/ 하와이/ PC방/ 전산망/ 구매물건/ 구매함수  ...함수 4일동안 
 	
 	문제 : 개발자가 즐겁게 휴가를 보낼 수 있도록 아래 코드를 수정해라 (제품이 늘아나도 구매가 가능하도록)
 	조금만 더 이해하겠다
 	- buy를 하나만 만들고 
 	
 */