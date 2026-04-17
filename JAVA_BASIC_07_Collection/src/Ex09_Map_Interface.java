/*
Map 인터페이스 구현한 클래스
(key, value) 쌍으로 가지고 있는 배열
ex) 지역변호 > (02,서울) > key 제공하면 value를 얻는 구조

key 중복 (x) >> Set
value 중복(0) >> List

Generic 지원
Map 인터페이스를 구현하고 있는 클래스
구버전 : HashTable : 동기화 보장 : Lock
신버전 : HashMap : 동기화 보장 X : Lock 옵션

다수의 데이터 (군집 데이터)\
1. Array
2. ArrayList
3. HashMap
+Generic
+CRUD (insert,select, update, delete) > 기능적 요구사항
+ I/O 파일 입출력 처리 (객체 직렬화)
+ 알파 (Stream, 람다, 함수형 프로그램)
*/

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Ex09_Map_Interface {
	public static void main(String[] args) {
		HashMap map = new HashMap();
		map.put("Tiger", "1004");
		map.put("Scott", "1004");
		map.put("Superman", "1007");
		
		System.out.println(map.containsKey("tiger"));
		System.out.println(map.containsKey("Tiger"));
		System.out.println(map.containsValue("1007"));
		
		//key 존재한다면 ...key 제공해서 value 얻고 싶다
		System.out.println(map.get("Tiger"));
		System.out.println(map.get("hong")); //해당하는 key가 없다면 null
		
		System.out.println(map.size());
		
		//만약
		map.put("Tiger", "1008");
		
		//key 같으면 overwrite
		System.out.println(map.get("Tiger"));
		System.out.println(map.toString());
		
		//응용
		Set set = map.keySet();
		//key 데이터 집합 중복된 데이터가 없고 순서를 가지지 않는 데이터 집합
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		/*for (Object o : set) {
			System.out.println(o);
		}*/

		Collection clist = map.values();
		System.out.println(clist.toString());
		
		
		
		}
}
