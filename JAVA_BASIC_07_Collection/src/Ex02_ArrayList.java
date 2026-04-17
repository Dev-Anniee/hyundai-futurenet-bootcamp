//Today Point

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Ex02_ArrayList {
	public static void main(String[] args) {
		//List 인터페이스 구현 (순서보장, 중복허용)
		//식당 번호표, 은행 번호표 (동명이인도 상관없다)
		ArrayList arrayList = new ArrayList();
		arrayList.add(100); //[0]
		arrayList.add(200);
		arrayList.add(100);
		arrayList.add(100); //[n]
		
		for(int i=0; i<arrayList.size(); i++) {
			System.out.println(arrayList.get(i));
		}

		// add 함수는 데이터 순차적 추가 (Pointer 위치 정보)
		arrayList.add(0,111); //뒤로 밀린다
		for(int i=0; i<arrayList.size(); i++) {
			System.out.print(arrayList.get(i)+"\t");
		}
		System.out.println(arrayList.toString());
		//[111, 100, 200, 100, 100]
		
		//1. 비순차적인 데이터 추가, 삭제 > 성능 문제 발생 > 이동 발생 > linkedList 노드 개념 (주소값)
		//2. 순차적인 데이터 추가 삭제 (뒤에서 부터) 유리
		
		//클래스 공부 (class 가지는 속성 ... 메서드 학습)
		//ArrayList 제공하는 다양한 함수
		System.out.println(arrayList.contains(200));
		System.out.println("사이즈 : "+arrayList.size()); //5개
		
		arrayList.clear(); //용량은 그재로 , 데이터 삭제
		System.out.println(arrayList.size());
		System.out.println(arrayList.toString());
		
		arrayList.add(101);
		arrayList.add(102);
		arrayList.add(103);
		System.out.println(arrayList.isEmpty());
		System.out.println(arrayList.toString());
		//get 함수, reomve 함수
		
		arrayList.remove(0); //0번째 칸 지우면 앞으로 한 칸 씩 이동 
		System.out.println(arrayList.toString());
		
		//다형성 
		//부모 참조 변수는 자식 객체의 주소를 받을 수 있다 (디커플링한 ...)
		List list = new ArrayList(); // 다형성 코드 -추천
		// ArrayList에서 구현됨
		list.add("가");
		list.add("나");
		list.add("다");
		list.add("라");
		System.out.println(list);
		List list2 = list.subList(0, 2);
		System.out.println(list2);
		//정렬 알고리즘 (bubble sort) - swap
		//JAVA API
		
		ArrayList alist = new ArrayList();
		alist.add(50);
		alist.add(1);
		alist.add(7);
		alist.add(40);
		alist.add(3);
		alist.add(50);
		System.out.println(alist.toString());
		
		//정렬하세요 - 자유 의지 (내장 함수 or 직접 구현)
		Collections.sort(alist);
		System.out.println(alist);
		
		Collections.reverse(alist);
		System.out.println(alist);
	} 
}
