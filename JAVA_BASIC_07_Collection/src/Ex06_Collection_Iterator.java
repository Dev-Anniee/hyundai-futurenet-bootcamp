/*
Collection FrameWork

1.다수의 데이터를 다루는 방법 >> 표준화 >> 수 많은 인터페이스 설계 >> 약속한 구현한 클래스 >> ArrayList
2. JAVA API > 인터페이스 > 구현한 클래스 > 활용만해

제어의 논리를 함수로 표준화 
쓰면 누가봐도 알 수 있게 해줄 거야
Iterator 인터페이스 설계
나열된 자원에 대해 순차적으로 접근해서 값의 유무를 판단하고 값을 리턴하는 표준을 정의
누가 구현? -> ArrayList 구현
Iterator 인터페이스 추상함수
Object hasNext()
boolean Next()
remove() 추상 실행 블럭이 없다

이미 arraylist가 구현하고 있다

*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Ex06_Collection_Iterator {
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add(100);
		list.add(200);
		list.add(300);
		
		//약속 표준화
		Iterator iterator = list.iterator(); // ArrayList 만든 사람이 함수를 구현 - 데이터 목록을 순차적으로 나열하고 객체에 담아서 주소 리턴
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		//순차적으로 나열된 자원에 대해서 표준화된 접근 통해 출력 iterator
		
		//8가지 원시 타입에 대해 객체 타입 설계 -> 객체 타입이 필요할 때가 생길 때 - wrapper class
		List<Integer> intlist = new ArrayList<Integer>();
		intlist.add(1);
		intlist.add(2);
		intlist.add(3);
		
		// -> 사용할 수 있어야 한다 : 표준화된 출력을 사용하기 위한
		Iterator<Integer> iterator2 = intlist.iterator(); //iterator 을 구현하고 있는 객체의 주소
		while(iterator2.hasNext()) {
			System.out.println(iterator2.next());
		}

		//역방향 
		System.out.println("*****************************");
		//Iterator() 순방향 갔다가
		//역방향 List Iteator
		
		ListIterator<Integer> iterator3 = intlist.listIterator();
		//순방향 -> 역방향
		while(iterator3.hasNext()) {
			System.out.println(iterator3.next());
		}// 순방향을 가서 역방향으로 가야만 한다
		while(iterator3.hasPrevious()) {
			System.out.println(iterator3.previous());
		}
		
		//참고로
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		System.out.println(numbers); //[1, 2, 3, 4]	
		Iterator<Integer> itr = numbers.iterator();

		//부모
		while(itr.hasNext()) {
			int num = itr.next(); //배열에서 값을 얻어서
			if(num % 2 == 1) {
				itr.remove();
			}
		}
		System.out.println("numbers :" + numbers);
	}
}
