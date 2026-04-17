/*
Generic 필수 
C#, JAVA

1. 타입을 처음부터 강제
2. 타입 안정성 (타입 강제)
3. 강제 형변환이 필요 없다 ex) (Emp)Object, (Car)Object 더이상 X
*/
//설계도 만들 때

import java.util.ArrayList;
import java.util.List;

class MyGeneric<T>{ //타입을 사용
	T obj;
	void add(T obj) {
		this.obj = obj;
	}
	T get() {
		return this.obj;
	}
}

class Person{
	int age=100;
}

public class Ex04_Generic {
	public static void main(String[] args) {
		MyGeneric <String> myGeneric = new MyGeneric<>();
		myGeneric.add("문자열로 타입 변환");
		String result = myGeneric.get();
		System.out.println(result); //트렌디한 코드
		
		// 다행이도 Generic 클래스 설계할 일 거의 없다 -> 이미 구현됨
		//Generic이 없다면
		List list = new ArrayList();
		list.add(10);
		list.add("홍길동");
		list.add(new Person());
		
		//개선된 for문을 사용해서 Person 객체의 age를 출력하고 나머지는 값 출력 -> 다운캐스팅
		for(Object object : list) {
			if(object instanceof Person)
				System.out.println(((Person) object).age);
			else {
				System.out.println(object);
			}
		}
		
		List<Person> pList = new ArrayList<Person>();
		pList.add(new Person());
		pList.add(new Person());
		
		for(Person person : pList) {
			System.out.println(person.age);
		}
	}
}
