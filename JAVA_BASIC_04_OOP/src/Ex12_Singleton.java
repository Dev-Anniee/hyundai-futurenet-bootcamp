import kr.or.kosa.Singleton;

public class Ex12_Singleton {
	//사무실에서 공유 프린터 (주소 항상 같도록)
	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		Singleton singleton3 = Singleton.getInstance();
		Singleton singleton4 = Singleton.getInstance();
		
		System.out.println(singleton);
		System.out.println(singleton1);
		System.out.println(singleton2);
		System.out.println(singleton3);
		System.out.println(singleton4);
	}
}
