import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.Emp;

public class Ex03_ArrayList_Object {
	public static void main(String[] args) {
		// 1. 사원 1명 
		//2. 사원 3명  Emp[] emp ={}; -> 더 이상 쓰지 않아요
		//더 편한 것 Collection
		
		List eList = new ArrayList();
		eList.add(new Emp(100,"김씨", "영업"));
		eList.add(new Emp(200,"박씨", "IT"));
		eList.add(new Emp(300,"이씨", "IT"));
		for (int i = 0; i < eList.size(); i++) {
			System.out.println(eList.get(i).toString());
		}
		
		//toString() 을 사용하지 말고 3명의 사원 번호 이름 직종 출력
		for (int i = 0; i < eList.size(); i++) {
			Object object = eList.get(i);
			//Emp 다운 캐스팅 하여 자원 접근 
			Emp emp = (Emp)object;
			System.out.println(emp.getEmpno() +","+emp.getEnmae()+","+ emp.getJob());
		}
		//위 코드는 우리가 쓰면 안된다 
		//Object > 하나의 타입 통일 > 객체 생성시 > 타입을 정해서 -강제
		//Generic (제너릭)
		
		//아래 코드 지향
		List<Emp> list2 = new ArrayList<Emp>();
		list2.add(new Emp(0,"A","IT"));
		
		for(Emp emp : list2) {
			System.out.println(emp.getEmpno());
		}
	}
}
