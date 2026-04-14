package kr.or.kosa;

//디자인 패턴 (객체 생성 관련된 이야기) >> new

/*
객체 하나만 만들어서 공유하는 방식
설계도... new 마음대로 사용
내가 원하는 건 class 영역 (method) static 자원을 만들고 공유하고 싶은데 
설계도를 단 하나의 객체만 생성하고 new를 못하게 만들거야
singleton

1.new를 못하게 막아라
1.1 객체 new Singletone() 생성자 함수
1.2 생성자 함수 보이지 않으면 private

객체를 무슨 방법으로... 메모리에 올리자... static 
*/

public class Singleton {
	private static Singleton p; //public을 사용하면 null을 가져와버릴 수 있다 -> 큰 일
	private Singleton() {
		
	}

	public static Singleton getInstance() {
		if(p==null) {
			p=new Singleton(); //private은 클래스 내부에서 상관없다
		}
		return p;
	}
}
