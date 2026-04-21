
/*
멀티 스레드 환경에서 
공유자원 관리 

동기화 > 공유자원 > 멀티스레드 


동기화 synchronized 
>> Collection (Vector(동기화 보장(0):성능(x)) , ArrayList(동기화 보장(x):성능(0)) 동기화

한강 
화장실(1개) 공유자원
여러명의 사람 (10명) : Thread  >> 동시에 화장실 접근 >> LOCK >> 안전하게 응가 보장

비빕밥 축제 공유자원
여러명의 사람 (10명) : LOCK 처리면 속도는 느려지고 ... 성능 감소 > LOCk을 걸지않고 처리 > 성능(형편성)


화장실 LOCK 동기화 처리 (객체 단위 , 함수단위)
*/

class Wroom {  //공유자원
	synchronized void openDoor(String name) {
		System.out.println(name + "님 화장실 입장 ^^");
		for (int i = 0; i < 100; i++) {
			System.out.println(name + " 사용 중 " + i);
			if( i == 10) {
				System.out.println(name + "님 끙 @@");
			}
		}
		System.out.println("시원하시죠 ^^!");
	}
}

class User extends Thread {
	Wroom wr;
	String who;
	
	User(String name , Wroom wr){
		this.who = name;
		this.wr = wr;
	}
	
	@Override
	public void run() {
		wr.openDoor(this.who);
	}
}

public class Ex06_Sync_Thread {

	public  static void main(String[] args) {
		//여기는 한강 둔치
		//화장실
		Wroom w = new Wroom();
		
		//사람들
		User kim = new User("김씨", w);
		User lee = new User("이씨", w);
		User park = new User("박씨", w);
		
		//급 똥 
		kim.start();
		lee.start();
		park.start();
		
	}

}
