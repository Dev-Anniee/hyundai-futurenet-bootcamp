
/* 자바 문법
 * 변수 > 타입 > 제어문 > 객체지향(쿨래스, 상속, 캡슐화, 다형성, 추상클래스, 인터페이스) 다수의 클래스 (상속, 포함)
 * JAVA API (고슬링 아저씨가 우리가 필요한 클래스를 만들어 놓았으니 활용해)
 * [다수의 데이터]를 다루는 방법 - [표준화된 인터페이스] :구현할 수 있는 클래스 제공 ex) ArrayList, HashMap
 * 한 번 익히면 표준화되어있어서 이용에 용이하다
 * 
 * Collection 인터페이스 >> 상속 List >> 구현 >> Vector, ArrayList - 인터페이스가 구현한 추상함수를 모두 구현했기 때문에 똑같은 함수를 가지고 있다.
 * Collection 인터페이스 >> 상속 Set >> 구현 >> HashSet //중복 데이터 허용 X -> 체크 로직 -> 로또
 * Map 인터페이스 (key,value)쌍의 배열 >>구현 >> HashMap
 * 내가 다수의 데이터를 사용하게 되면 - 고유번호
 * 
 * 문법의 핵심 : 다형성 덩어리 (느슨한 조건) 타입 - 부모타입을 받아서 사용해라 .... 
 * List 인터페이스는 ArrayList의 부모 타입이다
 * ex)
 * ArrayList list = new ArrayList();
 * 실무 ) List list = new ArrayList() 자식 객체의 주소 
 * list = new Vector(); - 사용을 지향하자 , 유연성을 위하여 
 * 
 * 1. List 줄을 서시오 - 순서가 있는 데이터의 집합 - 번호표 (순서(0), 중복(0)- 주소로 관리하므로 번호를 매겨 관리하겠다)
 * >> 팁 : 데이터는 Array 관리 [홍길동][홍길동][홍길동] >>index[0] , index[1]
 * 정적배열 -> 메모리 늘리는 것 아님 -> 배로 큰 길이의 배열을 새로 할당
 * 순서가 있어 중복을 허용한다
 *
 * List 구현하는 대표적인 클래스
 * 1.1 Vector 구버전 >동기화 보장 > 공유자원 (Vector) - Lock default -> 화장실 -> 보안 (0) 성능 (X)
 * 1.2 ArrayList 신버전 > 동기화 보장 X - Lock option -> 성능 (0) 보안 (X)
 * 
 * 우리는 지금까지 단일 쓰레드 (하나의 스택) - 싱글 쓰레드 >>동기화 의미가 없다
 * 전제 조건 ) Multi thread (다중 쓰레드)에서 의미가 있다 <-> 직렬화 
 * 
 * 현대는 성능 ArrayList, 동기화는 필요하면 락을 걸면 된다
 * Vector 유지보수 , 학습용 비교
 *
 * ---
 * 다수의 군집의 데이터를 다루는 방법 - Array - Collcetion
 * 정적 배열 (Array) : 크기가 정해지면 변경 불가 
 * int[] arr = new int[10]
 * int[] arr = new int[] {1,2,3,4,5}
 * int[] arr = {1,2,3,4,5}
 * 
 * 방이 더 필요하다면 새로운 배열을 만들자
 * int[] arr = new int[100]; >>데이터 이동 >> 개발자가 작업을 직접하는데 Collection은 직접하지 않는다
 * 
 * 그런데...?
 * List 인터페이스를 구현하고 있는 ArrayList, Vector
 * 1. 배열의 크기는 동적으로 확장 or 축소 > 개발자가 직접 or 컴파일러가 알아서 이사 
 */

import java.util.Vector;

public class Ex01_Vector {
	public static void main(String[] args) {
		Vector vector = new Vector();
		System.out.println("초기용량 : "+vector.capacity()); //10개의 방을 가진 정적 배열
		System.out.println("size 값의 개수 : " +vector.size());

		vector.add("AA"); // [0]
		vector.add("BB"); // [1]
		vector.add(10);
		// 불편 
		System.out.println("size 값의 개수 : " +vector.size());
		System.out.println(vector.toString()); //[AA, BB, 10]
		
		for(int i=0; i<vector.size(); i++) {
			System.out.print(vector.get(i)+" "); //array arr[index] - 함수를 통해 접근
			//length 함수 단점 - 개수가 어떻든 다 돌아야 해서 비효율
		}
		
		System.out.println();
		//개선된 for -> ? 어떻게 타입 다 다른데 
		for(Object object :vector) {
			System.out.print(object+" ");
		} // 타입을 강제하는 방법 (한 타입만 사용) -> 제너릭

		System.out.println();
		Vector <String> v2 = new Vector<String>();
		System.out.println("초기용량 : "+v2.capacity());
		v2.add("A");
		v2.add("A");
		v2.add("A");
		v2.add("A");
		v2.add("A");
		v2.add("A");
		v2.add("A");
		v2.add("A");
		v2.add("A");
		v2.add("A");
		v2.add("A");
		System.out.println("초기용량 : "+v2.capacity());
		System.out.println(v2.toString());
		//정리 - Collection 사용할 때 공간은 신경쓰지 말자, 데이터만 집중하자
		
	}
}
