/*static member field : 객체간 공유자원
static method : 객체간 공유자원

설계도
class Car{
	static 함수 ...
}

나는 Car 클래스의 자원을 편하게 쓰게 해줄게 new하지 않고도 사용하게 해줄게
그 자원이 많이 사용되니까

많이 사용하니까 편하게 쓰게 해주겠다 -> 성능은 별로
*/
class StaticClass{
	int iv;
	static int cv;
	static void print() {
		cv=500;
		//iv = 666; //생성시점 memory에 없어요 접근 불가
	}
	void printLv() {
		cv = 100; //생성시점... st=666;= 666;atic 자원은 일반 자원보다 먼저 memory 생성
		iv = 200;
	}
}
public class Ex08_Static_Method {
	public static void main(String[] args) {
		
		StaticClass.print();
		
		StaticClass st = new StaticClass();
		st.printLv();
		
		//확인
		System.out.println(st.cv);
		System.out.println(StaticClass.cv);
	}
}
