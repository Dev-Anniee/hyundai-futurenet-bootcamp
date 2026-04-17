/*
Set 인터페이스 
(원 안에 들어가세요) - 순서 보장 (X) 중복을 허락하지 않는 데이터 집합
Set 인터페이스 약속 : 구현, HashSet, TreeSet
*/

import java.util.HashSet;
import java.util.Set;

public class Ex07_Seat {
	public static void main(String[] args) {
		// 어떤 데이터 집합 (자료구조) 효율적으로 데이터를 다를 수 있을까
		//중복 데이터, 중복 데이터 없는 자료 
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(1);
		hs.add(100);
		boolean bo = hs.add(50);
		System.out.println(bo);
		
		bo = hs.add(1);
		System.out.println("결과 : " + bo);
		System.out.println(hs.toString());
		
		//순서를 보장(x) , 중복을 허락하지 않아요  > 만족하는 데이터 집합
		//로또 , 차량번호 , 주민번호 , 회원ID 
		
		HashSet<String> hs2 = new HashSet<String>();
		hs2.add("B");
		hs2.add("A");
		hs2.add("F");
		hs2.add("C");
		hs2.add("Z");
		hs2.add("A");
		hs2.add("C");
		hs2.add("F");
		hs2.add("K");
		hs2.add("Z");
		hs2.add("P");
		
		System.out.println(hs2.toString());
		// [P, A, b, C, F, Z, K]
		// 순서도 없고 중복 데이터도 없다
		
		String[] strObj = {"A","B","C","D","A","B"};
		HashSet<String> hs3 = new HashSet<String>();
		for(int i=0; i<strObj.length; i++) {
			hs3.add(strObj[i]);
		}System.out.println(hs3.toString());
		
		/*
		   for(int i = 0 ; i < 6 ; i++) {
					lotto[i] = (int)(Math.random()*45 + 1);
					for(int j = 0 ; j < i ; j++) { //j < i (채워진 개수 만큼 비교)
						if(lotto[i] == lotto[j]) {
							i--;
							break;
							
						}
					}
				} 
		 */
		
		//HashSet 을 사용해서 로또 만드세요
		Set<Integer> lotto = new HashSet<Integer>();
		while(lotto.size()<6) {
			lotto.add((int)(Math.random()*45+1));
		}System.out.println(lotto.toString());
	}
}
