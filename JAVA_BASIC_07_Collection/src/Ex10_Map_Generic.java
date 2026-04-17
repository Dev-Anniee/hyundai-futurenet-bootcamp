/*
HashMap<k,v>

HashMap<String, String>
HashMap<Integer, String>
HashMap<String, Student>

객체 직렬화
파라미터 (MyBatis)
채팅서버 데이터 관리
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ArrayTest{
	ArrayList<String[]> aList = new ArrayList<String[]>();
}

class Student{
	int kor;
	int math;
	int eng;
	String name;
	public Student(int kor, int math, int eng, String name) {
		super();
		this.kor = kor;
		this.math = math;
		this.eng = eng;
		this.name = name;
	}
}

public class Ex10_Map_Generic {
	public static void main(String[] args) {
		HashMap<String, String> sts = new HashMap<String, String>();
		sts.put("A", "AAA");
		System.out.println(sts.get("A"));
		
		//실무에서는 Map을 다루는 방법
		//학생 성적 데이터 
		//key 학번, value (여러건의 데이터) > 타입을 > Array, Collection 정의 가능
		
		HashMap<String, Student> sMap = new HashMap<String, Student>(); //student라는 객체 주소
		sMap.put("hong", new Student(100, 90, 50, "홍길동"));
		sMap.put("kim", new Student(80, 80, 50, "홍길동"));
		
		Student sd = sMap.get("kim");
		System.out.println(sd.kor+" "+sd.eng+" "+sd.math + " "+sd.name);
		
		String[] strarr = {"A","B","C","D"};
		ArrayTest aTest = new ArrayTest();
		aTest.aList.add(strarr);
		
		
		//key와 value 를 동시에 출력하는 방법
		for(Map.Entry m : sMap.entrySet()) {
			System.out.println(m.getKey()+" "+((Student)m.getValue()).name);
		}
		
	}
}
